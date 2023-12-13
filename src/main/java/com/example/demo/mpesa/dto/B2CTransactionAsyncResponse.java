package com.example.demo.mpesa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class B2CTransactionAsyncResponse {

    @JsonProperty("Result")
    private Result result;
}
