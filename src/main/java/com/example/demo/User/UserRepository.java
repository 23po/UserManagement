package com.example.demo.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //User save(User user);

    @Override
    <S extends User> S save(S entity);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


    List<User> findAll();

    void delete(User user);
    
}
