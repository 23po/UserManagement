package com.example.demo.auth;

import java.math.BigDecimal;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.User.Role;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.account.AccountService;
import com.example.demo.config.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository repository;

    private final AccountRepository accountRepository;

    //private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var account = new Account();
        
        var user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            //.role(request.getRole())
            .role(Role.USER)
            .build();
       

        
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);
        account.setUsername(user.getUsername());
        

    
        user.setAccount(account);
        repository.save(user);

        //accountRepository.save(account);


        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = repository.findByUsername(request.getUsername())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    // var refreshToken = jwtService.generateRefreshToken(user);
    // revokeAllUserTokens(user);
    // saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
            // .refreshToken(refreshToken)
        .build();
    }
}
