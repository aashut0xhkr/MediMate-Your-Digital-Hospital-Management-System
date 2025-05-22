package com.Major.Project.Billing.Controller;

import com.Major.Project.Billing.Entity.Bill;
import com.Major.Project.Billing.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS")
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping("/bill")
    public List<Bill> getAllBill(){
        return billService.getAllBill();
    }
    @GetMapping("/bill/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billID){
        Bill bill = billService.getBillById(billID);;
        return bill != null ? ResponseEntity.ok(bill) : ResponseEntity.notFound().build();
    }
    @GetMapping("/Patientsss/{id}")
    public List<Bill> getBillByPatientID(@PathVariable Long patientId){
        return billService.getBillByPatientId(patientId);
    }
    @PostMapping("/bill")
    public Bill CreateBill(@RequestBody Bill bill){
        return billService.createBill(bill);
    }
    @PutMapping("/bill/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long billID,@RequestBody Bill bill){
      Bill updated=  billService.UpdateBill(billID,bill);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/bill/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long billID){
       billService.deleteBill(billID);
        return ResponseEntity.noContent().build();
    }
}
