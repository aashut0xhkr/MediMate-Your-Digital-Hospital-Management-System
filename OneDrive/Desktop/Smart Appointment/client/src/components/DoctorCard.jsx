import React from 'react';

const DoctorCard = ({ doctor, isSelected, onClick }) => {
    return (
        <div
            onClick={() => onClick(doctor)}
            className={`glass fade-in`}
            style={{
                padding: '1.5rem',
                cursor: 'pointer',
                border: isSelected ? '2px solid var(--primary)' : '1px solid var(--glass-border)',
                transform: isSelected ? 'scale(1.02)' : 'scale(1)',
                transition: 'all 0.3s cubic-bezier(0.4, 0, 0.2, 1)'
            }}
        >
            <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                <div style={{
                    width: '50px',
                    height: '50px',
                    borderRadius: '50%',
                    background: 'linear-gradient(135deg, var(--primary), var(--secondary))',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    color: 'white',
                    fontWeight: 'bold',
                    fontSize: '1.2rem'
                }}>
                    {doctor.name.charAt(4)}
                </div>
                <div>
                    <h3 style={{ marginBottom: '0.2rem' }}>{doctor.name}</h3>
                    <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem' }}>{doctor.specialty}</p>
                </div>
            </div>
        </div>
    );
};

export default DoctorCard;
