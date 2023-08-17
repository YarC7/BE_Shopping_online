package com.example.salesmanagement.entity.enumtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMINISTRATOR_READ("administrator:read"),
    ADMINISTRATOR_UPDATE("administrator:update"),
    ADMINISTRATOR_CREATE("administrator:create"),
    ADMINISTRATOR_DELETE("administrator:delete"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_DELETE("customer:delete"),
    SELLER_READ("seller:read"),
    SELLER_UPDATE("seller:update"),
    SELLER_CREATE("seller:create"),
    SELLER_DELETE("seller:delete")
    
    ;

    @Getter
    private final String permission;
}
