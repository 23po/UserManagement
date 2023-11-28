package com.example.demo.mpesa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mpesa")
public class MpesaController {

    private final DarajaApi darajaApi;

    public MpesaController (DarajaApi darajaApi) {

        this.darajaApi = darajaApi;

    }

    @GetMapping(path = "/token", produces = "application/json")
    public ResponseEntity<AccessTokenResponse> getAccessToken() {
        return ResponseEntity.ok(darajaApi.getAccessToken());
    }

}
