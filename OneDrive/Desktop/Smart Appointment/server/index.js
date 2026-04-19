const express = require('express');
const cors = require('cors');
const { doctors, appointments } = require('./data');
const { getAvailableSlots } = require('./utils');

const app = express();
const PORT = 3001;

app.use(cors());
app.use(express.json());

// Get all doctors
app.get('/api/doctors', (req, res) => {
    res.json(doctors);
});

// Get available slots for a doctor on a date
app.get('/api/doctors/:id/slots', (req, res) => {
    const doctorId = parseInt(req.params.id);
    const { date } = req.query;

    const doctor = doctors.find(d => d.id === doctorId);
    if (!doctor) {
        return res.status(404).json({ error: "Doctor not found" });
    }

    if (!date) {
        return res.status(400).json({ error: "Date parameter is required" });
    }

    const slots = getAvailableSlots(doctor, date);
    res.json({ slots });
});

// Book an appointment
app.post('/api/appointments', (req, res) => {
    const { doctorId, date, time } = req.body;

    // Basic validation
    if (!doctorId || !date || !time) {
        return res.status(400).json({ error: "Missing fields" });
    }

    // Check if slot is taken
    const isTaken = appointments.some(appt =>
        appt.doctorId === doctorId &&
        appt.date === date &&
        appt.time === time
    );

    if (isTaken) {
        return res.status(409).json({ error: "Slot already booked" });
    }

    const newAppointment = {
        id: appointments.length + 1,
        doctorId,
        date,
        time
    };

    appointments.push(newAppointment);
    res.status(201).json(newAppointment);
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
