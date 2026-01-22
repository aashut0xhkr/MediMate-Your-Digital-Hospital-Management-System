import React, { useState, useEffect } from 'react';
import DoctorCard from './components/DoctorCard';
import SlotPicker from './components/SlotPicker';
// Removing App.css import if it exists to avoid conflicts, or I'll just clear App.css later.

function App() {
  const [doctors, setDoctors] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState(null);
  const [selectedDate, setSelectedDate] = useState(new Date().toISOString().split('T')[0]);
  const [slots, setSlots] = useState([]);
  const [selectedSlot, setSelectedSlot] = useState(null);
  const [loading, setLoading] = useState(false);
  const [bookingStatus, setBookingStatus] = useState(null); // 'success', 'error', null

  useEffect(() => {
    fetch('http://localhost:3001/api/doctors')
      .then(res => res.json())
      .then(data => setDoctors(data))
      .catch(err => console.error("Failed to fetch doctors", err));
  }, []);

  useEffect(() => {
    if (selectedDoctor && selectedDate) {
      setLoading(true);
      fetch(`http://localhost:3001/api/doctors/${selectedDoctor.id}/slots?date=${selectedDate}`)
        .then(res => res.json())
        .then(data => {
          setSlots(data.slots);
          setLoading(false);
        })
        .catch(err => {
          console.error(err);
          setLoading(false);
        });
    }
  }, [selectedDoctor, selectedDate]);

  const handleBook = () => {
    if (!selectedDoctor || !selectedSlot) return;

    fetch('http://localhost:3001/api/appointments', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        doctorId: selectedDoctor.id,
        date: selectedDate,
        time: selectedSlot
      })
    })
      .then(async res => {
        if (res.ok) {
          setBookingStatus('success');
          // Refresh slots
          setSlots(prev => prev.filter(s => s !== selectedSlot));
          setSelectedSlot(null);
          setTimeout(() => setBookingStatus(null), 3000);
        } else {
          setBookingStatus('error');
        }
      });
  };

  return (
    <div style={{ width: '100%', display: 'flex', gap: '2rem', flexWrap: 'wrap', justifyContent: 'center' }}>

      {/* Left Panel: Doctor Selection */}
      <div className="glass" style={{ flex: '1', minWidth: '300px', padding: '2rem' }}>
        <h1 style={{ marginBottom: '0.5rem', fontSize: '1.8rem' }}>Book Appointment</h1>
        <p style={{ color: 'var(--text-muted)', marginBottom: '2rem' }}>Select a specialist to get started</p>

        <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
          {doctors.map(doc => (
            <DoctorCard
              key={doc.id}
              doctor={doc}
              isSelected={selectedDoctor?.id === doc.id}
              onClick={setSelectedDoctor}
            />
          ))}
        </div>
      </div>

      {/* Right Panel: Slot Selection */}
      <div className="glass" style={{ flex: '1.5', minWidth: '300px', padding: '2rem', position: 'relative', overflow: 'hidden' }}>

        {!selectedDoctor ? (
          <div style={{ height: '100%', display: 'flex', alignItems: 'center', justifyContent: 'center', color: 'var(--text-muted)' }}>
            <p>Select a doctor to view availability</p>
          </div>
        ) : (
          <div className="fade-in">
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
              <div>
                <h2 style={{ fontSize: '1.4rem' }}>{selectedDoctor.name}</h2>
                <span style={{ color: 'var(--primary)', fontWeight: '600', fontSize: '0.9rem' }}>Available Slots</span>
              </div>
              <input
                type="date"
                value={selectedDate}
                onChange={(e) => setSelectedDate(e.target.value)}
                style={{
                  padding: '0.5rem 1rem',
                  borderRadius: '10px',
                  border: '1px solid #e2e8f0',
                  fontFamily: 'inherit',
                  outline: 'none'
                }}
              />
            </div>

            <SlotPicker
              slots={slots}
              selectedSlot={selectedSlot}
              onSelect={setSelectedSlot}
              loading={loading}
            />

            <div style={{ marginTop: '3rem', display: 'flex', justifyContent: 'flex-end', alignItems: 'center', gap: '1rem' }}>
              {bookingStatus === 'success' && (
                <span style={{ color: 'green', fontWeight: 'bold' }} className="fade-in">Appointment Confirmed!</span>
              )}
              {bookingStatus === 'error' && (
                <span style={{ color: 'red', fontWeight: 'bold' }} className="fade-in">Booking Failed</span>
              )}
              <button
                className="btn"
                disabled={!selectedSlot}
                onClick={handleBook}
                style={{ width: '150px' }}
              >
                Confirm Booking
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
