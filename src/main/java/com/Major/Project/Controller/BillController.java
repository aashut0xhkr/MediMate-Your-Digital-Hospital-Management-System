package com.Major.Project.Controller;

import com.Major.Project.DTO.BillDTO;
import com.Major.Project.Entity.Bill;
import com.Major.Project.Service.BillService;
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
    public List<BillDTO> getAllBill(){
        return billService.getAllBill();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable Long id){
        BillDTO bill = billService.getBillById(id);
        return bill != null ? ResponseEntity.ok(bill) : ResponseEntity.notFound().build();
    }
    @GetMapping("/Patient/{patientId}")
    public List<BillDTO> getBillByPatientID(@PathVariable Long patientId){
        return billService.getBillByPatient(patientId);
    }
    @PostMapping
    public BillDTO CreateBill(@RequestBody Bill bill){
        return billService.createBill(bill);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BillDTO> updateBill(@PathVariable Long id,@RequestBody Bill bill){
      BillDTO updated=  billService.UpdateBill(id,bill);
       return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
       billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
