package com.example.salesmanagement.entity.auth;

import com.example.salesmanagement.entity.enumtypes.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userEmail;

    private String userName;

    private String userFirstName;

    private String userLastName;

    private String userPassword;

    private String userPhone;

    private String userAddress;

    private String userNationality;

    private String userGender;

    private UserRole userRole;

    
 


}
