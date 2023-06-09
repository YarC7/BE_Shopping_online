package com.example.salesmanagement.entity.enumtypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.example.salesmanagement.entity.enumtypes.Permission.ADMINISTRATOR_CREATE;
import static com.example.salesmanagement.entity.enumtypes.Permission.ADMINISTRATOR_DELETE;
import static com.example.salesmanagement.entity.enumtypes.Permission.ADMINISTRATOR_READ;
import static com.example.salesmanagement.entity.enumtypes.Permission.ADMINISTRATOR_UPDATE;
import static com.example.salesmanagement.entity.enumtypes.Permission.CUSTOMER_CREATE;
import static com.example.salesmanagement.entity.enumtypes.Permission.CUSTOMER_DELETE;
import static com.example.salesmanagement.entity.enumtypes.Permission.CUSTOMER_READ;
import static com.example.salesmanagement.entity.enumtypes.Permission.CUSTOMER_UPDATE;
import static com.example.salesmanagement.entity.enumtypes.Permission.SELLER_CREATE;
import static com.example.salesmanagement.entity.enumtypes.Permission.SELLER_DELETE;
import static com.example.salesmanagement.entity.enumtypes.Permission.SELLER_READ;
import static com.example.salesmanagement.entity.enumtypes.Permission.SELLER_UPDATE;

@RequiredArgsConstructor
public enum UserRole {
    // CUSTOMER,
    // SELLER,
    // ADMINISTRATOR,
    // DELIVERY_PERSONNEL,
    // SUPPORT,
    // AFFILIATE,
    // Additional user types can be added here
    USER(Collections.emptySet()),
    ADMINISTRATOR(
            Set.of(
                    ADMINISTRATOR_READ,
                    ADMINISTRATOR_UPDATE,
                    ADMINISTRATOR_DELETE,
                    ADMINISTRATOR_CREATE
            )
    ),
    CUSTOMER(
            Set.of(
                    CUSTOMER_READ,
                    CUSTOMER_UPDATE,
                    CUSTOMER_DELETE,
                    CUSTOMER_CREATE
            )
    ),
    SELLER(
            Set.of(
                    SELLER_READ,
                    SELLER_UPDATE,
                    SELLER_DELETE,
                    SELLER_CREATE
            )
    )

    ;

        @Getter
        private final Set<Permission> permissions;

        public List<SimpleGrantedAuthority> getAuthorities() {
            var authorities = getPermissions()
                    .stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toList());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
            return authorities;
        }

    
}
