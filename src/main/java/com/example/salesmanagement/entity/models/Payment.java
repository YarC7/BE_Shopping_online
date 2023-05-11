package com.example.salesmanagement.entity.models;

// import java.math.BigDecimal;
import java.time.LocalDateTime;
// import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.salesmanagement.entity.utilities.Time;

// import com.example.salesmanagement.entity.utilities.Time;

import lombok.Data;



@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id",length = 50, nullable = false, updatable = false)
    private String paymentId;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "order_id")
    // private Order order;

    @Column(name = "payment_date")
    private String paymentDate = Time.getDeadCurrentDate();;

    @Column(name = "payment_method")
    private String paymentMethod;

    // @Column(length = 100, nullable = false)
    // private BigDecimal  total_price ;

}
