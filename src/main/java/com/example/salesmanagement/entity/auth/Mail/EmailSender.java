package com.example.salesmanagement.entity.auth.Mail;

public interface EmailSender {
    void send(String to, String email);
    
}
