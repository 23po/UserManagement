package com.example.demo;

import java.util.Objects;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class User {

    private Long id;

    private String username;

    private String email;

    private String password_hash;

    public User(Long id, String username, String email, String password_hash) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        
    } 


    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
    public String getPassword_hash() {
        return password_hash;
    }

    /**
     * @param password_hash the password_hash to set
     */
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

  @Override
  public String toString() {
    return "User{" +
           "id=" + id +
           ", username ='" + username + '\'' +
           ", email=' " + email + '\'' +
           ", password_hash='" + password_hash + '\'' +
           '}';         
  }

  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((id == null) ? 0 : username.hashCode());
    result = prime * result + ((id == null) ? 0 : email.hashCode());
    result = prime * result + ((id == null) ? 0 : password_hash.hashCode());

    return result;
  }

  @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    User otherUser = (User) obj;
    return Objects.equals(id, otherUser.id) &&
           Objects.equals(username, otherUser.username) &&
           Objects.equals(email, otherUser.email) &&
           Objects.equals(password_hash, otherUser.password_hash);
}




}
