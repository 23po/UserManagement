package com.example.demo.mpesa.dto;
import lombok.*;

@Data
public class B2CTransactionErrorResponse implements B2CTransactionSyncResponse {

    
    private String requestId;
    private String errorCode;
    private String errorMessage;
    
}
