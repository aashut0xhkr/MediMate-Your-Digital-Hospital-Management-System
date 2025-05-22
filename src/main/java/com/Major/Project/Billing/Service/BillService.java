package com.Major.Project.Billing.Service;

import com.Major.Project.Billing.Entity.Bill;
import com.Major.Project.Billing.Repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillingRepository billingRepository;

    public List<Bill> getAllBill(){
        return billingRepository.findAll();
    }
    public Bill getBillById(Long billID){
        return billingRepository.findById(billID).orElse(null);
    }
    public List<Bill> getBillByPatientId(Long patientId){
        return billingRepository.findByPatientId(patientId);
    }
    public Bill createBill(Bill bill){
        bill.setBillingDate(java.time.LocalDateTime.now());
        return billingRepository.save(bill);
    }
    public void deleteBill(Long  billID){
         billingRepository.deleteById(billID);
    }
    public Bill UpdateBill(Long  billID,Bill bill){
        Bill billById = getBillById(billID);
        if(billById==null) return null;
        billById.setAmount(bill.getAmount());
        billById.setDescription(bill.getDescription());
        bill.setPaymentStatus(bill.getPaymentStatus());
        return billingRepository.save(bill);
    }
}

