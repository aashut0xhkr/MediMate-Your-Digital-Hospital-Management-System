const doctors = [
  {
    id: 1,
    name: "Dr. Sarah Smith",
    specialty: "Cardiologist",
    workingHours: {
      start: "09:00",
      end: "17:00"
    }
  },
  {
    id: 2,
    name: "Dr. John Doe",
    specialty: "Dermatologist",
    workingHours: {
      start: "10:00",
      end: "16:00"
    }
  }
];

const appointments = [
  // Pre-booked slot for testing
  {
    id: 1,
    doctorId: 1,
    date: "2024-10-25", // Example date
    time: "09:30"
  }
];

module.exports = { doctors, appointments };
