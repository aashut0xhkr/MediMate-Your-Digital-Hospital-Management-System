package com.Major.Project.Service;

import com.Major.Project.DTO.BillDTO;
import com.Major.Project.Entity.Bill;
import com.Major.Project.Entity.Patient;
import com.Major.Project.Repository.BillingRepository;
import com.Major.Project.Repository.PatientRepository;
import com.Major.Project.Configuration.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<BillDTO> getAllBill() {
        List<Bill> bills = billingRepository.findAll();
        return bills.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public BillDTO getBillById(Long billId) {
        Bill bill = billingRepository.findById(billId)
                .orElseThrow(() -> new CustomException("Bill Not Found"));
        return convertToDto(bill);
    }

    public List<BillDTO> getBillByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("id not found"));
        List<Bill> bills = billingRepository.findByPatient(patient);
        return bills.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public BillDTO createBill(Bill bill) {
        Long patientId = bill.getPatient().getPatientId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient ID not found"));
        bill.setPatient(patient);
        bill.setBillingDate(java.time.LocalDateTime.now());
        Bill savedBill = billingRepository.save(bill);
        return convertToDto(savedBill);
    }
    public void deleteBill(Long  billID){
         billingRepository.deleteById(billID);
    }
    public BillDTO UpdateBill(Long billID, Bill bill) {
        Bill existing = billingRepository.findById(billID)
                .orElseThrow(() -> new CustomException("Bill Not Found"));

        existing.setAmount(bill.getAmount());
        existing.setDescription(bill.getDescription());
        existing.setPaymentStatus(bill.getPaymentStatus());

        if (bill.getPatient() != null && bill.getPatient().getPatientId() != null) {
            Patient patient = patientRepository.findById(bill.getPatient().getPatientId())
                    .orElseThrow(() -> new CustomException("Patient ID not found"));
            existing.setPatient(patient);
        }

        Bill updatedBill = billingRepository.save(existing);
        return convertToDto(updatedBill);
    }


    private BillDTO convertToDto(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setBillID(bill.getBillId());
        billDTO.setAmount(bill.getAmount());
        billDTO.setDescription(bill.getDescription());
        billDTO.setBillingDate(bill.getBillingDate());
        billDTO.setPatientID(bill.getPatient().getPatientId());
        billDTO.setStaffId(bill.getHandledBy().getId());

        return billDTO;


    }

}

