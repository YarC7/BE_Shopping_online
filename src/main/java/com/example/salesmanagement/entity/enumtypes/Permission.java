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
    SELLER_DELETE("seller:delete"),
    DELIVERY_PERSONNEL_READ("delivery_personnel:read"),
    DELIVERY_PERSONNEL_UPDATE("delivery_personnel:update"),
    DELIVERY_PERSONNEL_CREATE("delivery_personnel:create"),
    DELIVERY_PERSONNEL_DELETE("delivery_personnel:delete"),
    SUPPORT_READ("support:read"),
    SUPPORT_UPDATE("support:update"),
    SUPPORT_CREATE("support:create"),
    SUPPORT_DELETE("support:delete"),
    AFFILIATE_READ("affiliate:read"),
    AFFILIATE_UPDATE("affiliate:update"),
    AFFILIATE_CREATE("affiliate:create"),
    AFFILIATE_DELETE("affiliate:delete")
    ;

    @Getter
    private final String permission;
}
