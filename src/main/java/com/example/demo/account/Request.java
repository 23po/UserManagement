package com.example.demo.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    //there should be one user account created when one registers, no need for someone to have to register then create an account
   public String username;

}
