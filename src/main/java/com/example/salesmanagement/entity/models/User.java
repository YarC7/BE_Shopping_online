package com.example.salesmanagement.entity.models;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.salesmanagement.entity.enumtypes.UserRole;
import com.example.salesmanagement.entity.token.Token;
import com.example.salesmanagement.entity.utilities.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Builder.Default
    @Column(name = "user_id",length = 50, nullable = false, updatable = false)
    private String userId = "US-" + UUID.randomUUID().toString();

    @Column(length = 100, nullable = true , unique = true , updatable = false)
    private String userEmail;

    //if user are seller , userName become name of shop.
    @Column(length = 100, nullable = true , unique = true)
    private String userName;

    @Column(length = 100, nullable = true)
    private String userFirstName;

    @Column(length = 100, nullable = true)
    private String userLastName;
    
    @Column(length = 100 , nullable = false)
    private String userPassword;

    @Column(length = 11, nullable = true)
    private String userPhone;

    @Column(length = 300, nullable = true)
    private String userAddress;

    @Column(length = 300, nullable = true)
    private String userNationality;

    @Column(length = 1, nullable = true)
    private String userGender;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Builder.Default
    @Column(name = "is_activated")
    private boolean isActivated = false;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Address> userShippingAddress = new ArrayList<>();
    
    @Builder.Default
    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Builder.Default
    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }   

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserName() {
        return userName;
    }

    
}




