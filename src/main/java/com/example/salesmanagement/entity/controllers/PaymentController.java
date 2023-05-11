package com.example.salesmanagement.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesmanagement.entity.models.Payment;
import com.example.salesmanagement.entity.services.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;


    @GetMapping("")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    
    @GetMapping("/{id}/show")
    public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }





    @PostMapping("/store")
    public  ResponseEntity<?> store(@RequestBody Payment payment){

        paymentService.createPayment(payment);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}/update")
    public void updatePayment(@PathVariable("id") String id, @RequestBody Payment payment) {
        paymentService.updatePayment(id, payment);
        System.out.println(payment);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePayment(@PathVariable(value = "id") String id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
