package com.Major.Project.Billing.Controller;

import com.Major.Project.Billing.Entity.Bill;
import com.Major.Project.Billing.Service.BillService;
import com.Major.Project.Patient.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HMS/Bill")
public class BillController {
    @Autowired
    private BillService billService;
    @GetMapping
    public List<Bill> getAllBill(){
        return billService.getAllBill();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id){
        Bill bill = billService.getBillById(id);;
        return bill != null ? ResponseEntity.ok(bill) : ResponseEntity.notFound().build();
    }
    @GetMapping("/{patientId}")
    public List<Bill> getBillByPatientID(@PathVariable Patient patientId){
        return billService.getBillByPatient(patientId);
    }
    @PostMapping
    public Bill CreateBill(@RequestBody Bill bill){
        return billService.createBill(bill);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id,@RequestBody Bill bill){
      Bill updated=  billService.UpdateBill(id,bill);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
       billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
