package com.example.demo.User;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.example.demo.account.Account;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    //  //@JoinColumn(name="userId")
   private Account account;

    public BigDecimal balance;



    /**
     * @return Long return the id
     */
    public Long getId() {
        return userId;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.userId = id;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password_hash
     */
    // public String getPassword() {
    //     return password;
    // }

    /**
     * @param password_hash the password_hash to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

  @Override
  public String toString() {
    return "User{" +
           "id=" + userId +
           ", username ='" + username + '\'' +
           ", email=' " + email + '\'' +
           ", password_hash='" + password + '\'' +
           '}';         
  }

  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    result = prime * result + ((userId == null) ? 0 : username.hashCode());
    result = prime * result + ((userId == null) ? 0 : email.hashCode());
    result = prime * result + ((userId == null) ? 0 : password.hashCode());

    return result;
  }

  @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    User otherUser = (User) obj;
    return Objects.equals(userId, otherUser.userId) &&
           Objects.equals(username, otherUser.username) &&
           Objects.equals(email, otherUser.email) &&
           Objects.equals(password, otherUser.password);
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    return List.of(new SimpleGrantedAuthority(role.name()));
}

@Override
public String getPassword() {
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    return password;
}

@Override
public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
   // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
return true;
}

@Override
public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    return true;
}

@Override
public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    return true;
}

@Override
public boolean isEnabled() {
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    return true;
}




}
