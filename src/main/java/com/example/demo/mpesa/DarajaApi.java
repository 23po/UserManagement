package com.example.demo.mpesa;

import com.example.demo.config.MpesaConfig;
import static com.example.demo.mpesa.Constants.*;



import com.example.demo.mpesa.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class DarajaApi {

    private final MpesaConfig mpesaConfig;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    public DarajaApi(MpesaConfig mpesaConfig, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.mpesaConfig = mpesaConfig;
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }

    /**
     * @return Returns Daraja API Access Token Response
     */

    //@Override
    public AccessTokenResponse getAccessToken() {

        // get the Base64 rep of consumerKey + ":" + consumerSecret
        String encodedCredentials = HelperUtility.toBase64String(String.format("%s:%s", mpesaConfig.getConsumerKey(), mpesaConfig.getConsumerSecret()));
    
        // OkHttpClient client = new OkHttpClient().newBuilder().build();
        // Request request = new Request.Builder()
        //   .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
        //   .method("GET", null)
        //   .addHeader("Authorization", "Basic cFJZcjZ6anEwaThMMXp6d1FETUxwWkIzeVBDa2hNc2M6UmYyMkJmWm9nMHFRR2xWOQ==")
        //   .build();
        // Response response = client.newCall(request).execute();
       
       
        Request request = new Request.Builder()
                .url(String.format("%s?grant_type=%s", mpesaConfig.getOauthEndpoint(), mpesaConfig.getGrantType()))
                .get()
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BASIC_AUTH_STRING, encodedCredentials))
                .addHeader(CACHE_CONTROL_HEADER, CACHE_CONTROL_HEADER_VALUE)
                .build();

                // Log the request details
                System.out.println(encodedCredentials);
                System.out.println("Request URL: " + request.url());
                System.out.println("Request Headers: " + request.headers());
                System.out.println("Request Method: " + request.method());
                if (request.body() != null) {
                        System.out.println("Request Body: " + request.body());
                }

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            // use Jackson to Decode the ResponseBody...
            return objectMapper.readValue(response.body().string(), AccessTokenResponse.class);
        } catch (IOException e) {
            log.error(String.format("Could not get access token. -> %s", e.getLocalizedMessage()));
            return null;
        }
        }

    public B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest) {
        AccessTokenResponse accessTokenResponse = getAccessToken();
        log.info(String.format("Access Token: %s", accessTokenResponse.getAccessToken()));

        B2CTransactionRequest b2CTransactionRequest = new B2CTransactionRequest();

        b2CTransactionRequest.setCommandID(internalB2CTransactionRequest.getCommandID());
        b2CTransactionRequest.setAmount(internalB2CTransactionRequest.getAmount());
        b2CTransactionRequest.setPartyB(internalB2CTransactionRequest.getPartyB());
        b2CTransactionRequest.setRemarks(internalB2CTransactionRequest.getRemarks());
        b2CTransactionRequest.setOccassion(internalB2CTransactionRequest.getOccassion());

        b2CTransactionRequest.setOriginatorConversationID(HelperUtility.generateRandomString());

        // get the security credentials ...
        b2CTransactionRequest.setSecurityCredential(HelperUtility.getSecurityCredentials(mpesaConfig.getB2cInitiatorPassword()));

        log.info(String.format("Security Creds: %s", b2CTransactionRequest.getSecurityCredential()));

        // set the result url ...
        b2CTransactionRequest.setResultURL(mpesaConfig.getB2cResultUrl());
        b2CTransactionRequest.setQueueTimeOutURL(mpesaConfig.getB2cQueueTimeoutUrl());
        b2CTransactionRequest.setInitiatorName(mpesaConfig.getB2cInitiatorName());
        b2CTransactionRequest.setPartyA(mpesaConfig.getShortCode());

        RequestBody body = RequestBody.create(
                Objects.requireNonNull(HelperUtility.toJson(b2CTransactionRequest)), JSON_MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(mpesaConfig.getB2cTransactionEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
                .build();

                //TODO: handle error just for fun, read jackson
        try {
            Response response = okHttpClient.newCall(request).execute();

            assert response.body() != null;

           

            // objectMapper.readValue(response.body().string(), );

            // if (response.body().string().contains("errorCode")) {
            //  return objectMapper.readValue(response.body().string(), B2CTransactionErrorResponse.class);   
            // } else {

            return objectMapper.readValue(response.body().string(), B2CTransactionSuccess.class);
          //}
        } catch (IOException e) {
            log.error(String.format("Could not perform B2C transaction ->%s", e.getLocalizedMessage()));
             return null;  
        }

    }

    }

