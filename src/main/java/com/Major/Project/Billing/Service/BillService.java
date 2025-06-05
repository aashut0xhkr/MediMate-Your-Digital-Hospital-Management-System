package com.Major.Project.Billing.Service;

import com.Major.Project.Billing.Entity.Bill;
import com.Major.Project.Billing.Repository.BillingRepository;
import com.Major.Project.Configuration.CustomException;
import com.Major.Project.Patient.Entity.Patient;
import com.Major.Project.Patient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Bill> getAllBill(){
        return billingRepository.findAll();
    }

    public Bill getBillById(Long billId){
        return billingRepository.findById(billId).orElseThrow(()->new CustomException("Bill Not Found"));
    }

    public List<Bill> getBillByPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new CustomException("id not found"));
        return billingRepository.findByPatient(patient);
    }
    public Bill createBill(Bill bill){
        Long patientId = bill.getPatient().getPatientId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient ID not found"));
        bill.setPatient(patient);
        bill.setBillingDate(java.time.LocalDateTime.now());
        return billingRepository.save(bill);
    }
    public void deleteBill(Long  billID){
         billingRepository.deleteById(billID);
    }
    public Bill UpdateBill(Long billID, Bill bill){
        Bill existing = getBillById(billID);
        if(existing == null) return null;

        existing.setAmount(bill.getAmount());
        existing.setDescription(bill.getDescription());
        existing.setPaymentStatus(bill.getPaymentStatus());

        if (bill.getPatient() != null && bill.getPatient().getPatientId() != null) {
            Patient patient = patientRepository.findById(bill.getPatient().getPatientId())
                    .orElseThrow(() -> new CustomException("Patient ID not found"));
            existing.setPatient(patient);
        }

        return billingRepository.save(existing);
    }

}

