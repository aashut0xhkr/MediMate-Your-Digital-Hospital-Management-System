package com.Major.Project.Pharmacy.Service;

import com.Major.Project.Pharmacy.Entity.Medicine;
import com.Major.Project.Pharmacy.Repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {

        @Autowired
        private MedicineRepository medicineRepository;

        public List<Medicine> getAll() {
            return medicineRepository.findAll();
        }

        public Medicine getById(Long id) {
            return medicineRepository.findById(id).orElse(null);
        }

        public List<Medicine> getByStatus(String status) {
            return medicineRepository.findByStatus(status);
        }

        public List<Medicine> searchByName(String name) {
            return medicineRepository.findByNameContainingIgnoreCase(name);
        }

        public Medicine create(Medicine medicine) {
            updateStatusBasedOnDate(medicine);
            return medicineRepository.save(medicine);
        }

        public Medicine update(Long id, Medicine updated) {
            Medicine existing = getById(id);
            if (existing == null) return null;
            existing.setName(updated.getName());
            existing.setManufacturer(updated.getManufacturer());
            existing.setQuantity(updated.getQuantity());
            existing.setExpiryDate(updated.getExpiryDate());
            updateStatusBasedOnDate(existing);
            return medicineRepository.save(existing);
        }

        public void delete(Long id) {
            medicineRepository.deleteById(id);
        }

        private void updateStatusBasedOnDate(Medicine medicine) {
            if (medicine.getExpiryDate().isBefore(LocalDate.now())) {
                medicine.setStatus("Expired");
            } else if (medicine.getQuantity() == 0) {
                medicine.setStatus("OutOfStock");
            } else {
                medicine.setStatus("InStock");
            }
        }
    }


