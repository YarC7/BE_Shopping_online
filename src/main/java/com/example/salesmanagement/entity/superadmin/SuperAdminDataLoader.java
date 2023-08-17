package com.example.salesmanagement.entity.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.salesmanagement.entity.configs.JwtService;
import com.example.salesmanagement.entity.enumtypes.UserRole;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.token.Token;
import com.example.salesmanagement.entity.token.TokenRepository;
import com.example.salesmanagement.entity.token.TokenType;

@Component
public class SuperAdminDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;


    // Auto Create a super admin for application .
    // After that the super admin can add a child admin or staff to manage the application
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUserName("superadmin") == null) {
            User user = new User();
            user.setUserName("superadmin");
            // Thực hiện mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
            String encodedPassword = new BCryptPasswordEncoder().encode("superadminpassword");
            user.setUserPassword(encodedPassword);
            user.setUserEmail("admin@example.com");
            user.setUserRole(UserRole.ADMINISTRATOR);
            user.setUserShippingAddress(null);
            user.setActivated(true);
            var savedUser= userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            // var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
    }
}





