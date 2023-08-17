package com.example.salesmanagement.entity.enumtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public enum Status {;
    @RequiredArgsConstructor
    public enum OrderStatus {
        PENDING("pending"),
        CONFIRMED("confirmed"),
        SHIPPING("shipping"),
        COMPLETED("completed"),
        CANCELLED("cancelled");
    
        @Getter
        private final String value;
    

    }
    @RequiredArgsConstructor
    public enum PaymentMethod {
        CASH("cash"),
        COD("cod"),
        VNPAY("vnpay"),
        MOMO("momo"),
        PAYPAL("paypal"),
        ZALO_PAY("zalopay");
    
        @Getter
        private final String value;
    
        
    }
    @RequiredArgsConstructor
    public enum PaymentStatus {
        PENDING("pending"),
        PAID("paid"),
        CANCELLED("cancelled");
    
        @Getter
        private final String value;
    
    }
    @RequiredArgsConstructor
    public enum BrandStatus {
        WAITING_APPROVAL("pending"),
        REJECTED("cancelled"),
        APPROVED("confirmed");


        @Getter
        private final String value;
    }
}
