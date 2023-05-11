package com.example.salesmanagement.entity.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id",length = 50, nullable = false, updatable = false)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; 

    @Column(name = "transaction_Status",length = 50, nullable = false, updatable = false)
    private String transactionStatus;
}
