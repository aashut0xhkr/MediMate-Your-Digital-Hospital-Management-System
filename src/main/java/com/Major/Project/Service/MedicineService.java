package com.Major.Project.Service;

import com.Major.Project.DTO.MedicineDTO;
import com.Major.Project.Entity.Medicine;
import com.Major.Project.Repository.MedicineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicineService {

        @Autowired
        private MedicineRepository medicineRepository;

    public List<MedicineDTO> getAll() {
        return medicineRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    public MedicineDTO getById(Long id) {
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        return medicine == null ? null : convertToDTO(medicine);
    }
    public MedicineDTO create(Medicine medicine) {
        updateStatusBasedOnDate(medicine);
        Medicine saved = medicineRepository.save(medicine);
        return convertToDTO(saved);
    }

    public List<MedicineDTO> getByStatus(String status) {
        return medicineRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<MedicineDTO> searchByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }



    public MedicineDTO update(Long id, Medicine updated) {
        Medicine existing = medicineRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setManufacturer(updated.getManufacturer());
        existing.setQuantity(updated.getQuantity());
        existing.setExpiryDate(updated.getExpiryDate());

        updateStatusBasedOnDate(existing); // expects Medicine

        Medicine saved = medicineRepository.save(existing);
        return convertToDTO(saved);
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

        private MedicineDTO convertToDTO(Medicine medicine){
            MedicineDTO medicineDTO=new MedicineDTO();
            medicineDTO.setId(medicine.getId());
            medicineDTO.setName(medicine.getName());
            medicineDTO.setManufacturer(medicine.getManufacturer());
            medicineDTO.setQuantity(medicine.getQuantity());
            medicineDTO.setExpiryDate(medicine.getExpiryDate());
            medicineDTO.setStatus(medicine.getStatus());
            medicineDTO.setPatientId(medicine.getPatient().getPatientId());
            medicineDTO.setPharmacistId(medicine.getPharmacist().getId());
            return medicineDTO;
        }
    }


