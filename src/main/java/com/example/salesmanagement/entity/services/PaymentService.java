package com.example.salesmanagement.entity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.salesmanagement.entity.models.Payment;
import com.example.salesmanagement.entity.repositories.PaymentRepository;
import com.example.salesmanagement.entity.utilities.Time;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments(){
        List<Payment> payments = new ArrayList<>();
        paymentRepository.findAll().forEach(payments::add);
        return payments;
    }
    public Payment getPaymentById(String id) {
        Optional<Payment> one_Payment = paymentRepository.findById(id);
        return one_Payment.orElse(null);
    }

    public void createPayment(Payment payment) {
        paymentRepository.save(payment);
    }
    public ResponseEntity<Payment> updatePayment(String id, Payment payment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
    
        if (!optionalPayment.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Payment existingPayment = optionalPayment.get();
    

        existingPayment.setPaymentMethod(payment.getPaymentMethod());



        Payment updatedPayment = paymentRepository.save(existingPayment);
        return ResponseEntity.ok(updatedPayment);
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }
}
