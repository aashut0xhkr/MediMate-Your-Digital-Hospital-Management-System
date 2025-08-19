-- Insert into Staff (independent table)
INSERT INTO staff (name, role, department, phone, email, status) VALUES
('Alice Brown', 'NURSE', 'EMERGENCY', '555-0101', 'alice.brown@example.com', 'ACTIVE'),
('Bob Wilson', 'PHARMACIST', 'PHARMACY', '555-0102', 'bob.wilson@example.com', 'ACTIVE'),
('Carol Davis', 'LAB_TECHNICIAN', 'LABORATORY', '555-0103', 'carol.davis@example.com', 'ACTIVE');

-- Insert into Doctor (independent table)
INSERT INTO doctor (name, specialisation, contact, email) VALUES
('Dr. Jane Smith', 'CARDIOLOGY', '555-0201', 'jane.smith@example.com'),
('Dr. Alan Green', 'NEUROLOGY', '555-0202', 'alan.green@example.com');

-- Insert into Patient (independent table)
INSERT INTO patient (name, age, gender, contact) VALUES
('John Doe', 30, 'MALE', '555-0301'),
('Sarah Johnson', 45, 'FEMALE', '555-0302'),
('Mike Lee', 25, 'MALE', '555-0303');

-- Insert into Appointment (depends on Patient and Doctor)
INSERT INTO appointment (appointment_time, status, patient_id, doctor_id) VALUES
('2025-08-14 10:00:00', 'SCHEDULED', 1, 1),
('2025-08-14 11:00:00', 'SCHEDULED', 2, 2),
('2025-08-15 14:00:00', 'CONFIRMED', 3, 1);

-- Insert into Bill (depends on Patient and Staff)
INSERT INTO bill (amount, description, billing_date, payment_status, patient_id, staff_id) VALUES
(150.00, 'Consultation Fee', '2025-08-14 10:30:00', 'PENDING', 1, 1),
(200.00, 'Lab Test Payment', '2025-08-14 11:30:00', 'PAID', 2, 3);

-- Insert into Inventory (depends on Staff)
INSERT INTO inventory (name, category, quantity, supplier, status, staff_id) VALUES
('Syringe', 'MEDICAL_SUPPLIES', 100, 'MediCorp', 'AVAILABLE', 1),
('Bandage', 'MEDICAL_SUPPLIES', 50, 'HealthSupplies', 'AVAILABLE', 2);

-- Insert into LabTest (depends on Patient and Staff)
INSERT INTO lab_test (test_name, test_date, result, status, patient_id, staff_id) VALUES
('Blood Test', '2025-08-14', 'Normal', 'COMPLETED', 1, 3),
('X-Ray', '2025-08-15', 'Pending', 'IN_PROGRESS', 2, 3);

-- Insert into Medicine (depends on Patient and Staff)
INSERT INTO medicine (name, manufacturer, quantity, expiry_date, status, patient_id, staff_id) VALUES
('Paracetamol', 'PharmaInc', 10, '2026-12-31', 'DISPENSED', 1, 2),
('Ibuprofen', 'MediCorp', 5, '2026-06-30', 'DISPENSED', 2, 2);