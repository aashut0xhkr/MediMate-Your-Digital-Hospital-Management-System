const { appointments } = require('./data');

const getAvailableSlots = (doctor, date) => {
    const { start, end } = doctor.workingHours;
    const slots = [];
    let currentTime = start;

    // Helper to add minutes to time string "HH:MM"
    const addMinutes = (time, minutes) => {
        const [h, m] = time.split(':').map(Number);
        const dateObj = new Date();
        dateObj.setHours(h, m, 0, 0);
        dateObj.setMinutes(dateObj.getMinutes() + minutes);
        return dateObj.toTimeString().slice(0, 5);
    };

    // Helper to compare times
    const isBefore = (t1, t2) => {
        const [h1, m1] = t1.split(':').map(Number);
        const [h2, m2] = t2.split(':').map(Number);
        return h1 < h2 || (h1 === h2 && m1 < m2);
    };

    // Generate 30 min slots
    while (isBefore(currentTime, end)) {
        const nextTime = addMinutes(currentTime, 30);
        if (!isBefore(nextTime, end) && nextTime !== end) break; // Don't go past end

        // Check availability
        const isBooked = appointments.some(appt =>
            appt.doctorId === doctor.id &&
            appt.date === date &&
            appt.time === currentTime
        );

        if (!isBooked) {
            slots.push(currentTime);
        }

        currentTime = nextTime;
    }

    return slots;
};

module.exports = { getAvailableSlots };
