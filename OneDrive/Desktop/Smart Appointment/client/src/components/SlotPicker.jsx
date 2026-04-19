import React from 'react';

const SlotPicker = ({ slots, selectedSlot, onSelect, loading }) => {
    if (loading) return <div style={{ padding: '2rem', textAlign: 'center', color: 'var(--text-muted)' }}>Finding best slots...</div>;

    if (slots.length === 0) return <div style={{ padding: '2rem', textAlign: 'center', color: 'var(--text-muted)' }}>No slots available for this date.</div>;

    return (
        <div className="fade-in" style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(100px, 1fr))', gap: '1rem', marginTop: '1.5rem' }}>
            {slots.map((slot) => (
                <button
                    key={slot}
                    onClick={() => onSelect(slot)}
                    style={{
                        background: selectedSlot === slot ? 'var(--primary)' : 'white',
                        color: selectedSlot === slot ? 'white' : 'var(--text-main)',
                        border: selectedSlot === slot ? 'none' : '1px solid #e2e8f0',
                        padding: '0.8rem',
                        borderRadius: '12px',
                        cursor: 'pointer',
                        transition: 'all 0.2s',
                        fontWeight: '500',
                        boxShadow: selectedSlot === slot ? '0 4px 12px rgba(79, 70, 229, 0.3)' : 'none'
                    }}
                >
                    {slot}
                </button>
            ))}
        </div>
    );
};

export default SlotPicker;
