package com.example.demo.mpesa;

import com.example.demo.config.MpesaConfig;
import static com.example.demo.mpesa.Constants.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

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


    }

