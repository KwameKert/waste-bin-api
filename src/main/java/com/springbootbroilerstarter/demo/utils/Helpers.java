package com.springbootbroilerstarter.demo.utils;

import com.springbootbroilerstarter.demo.dtos.*;
import com.springbootbroilerstarter.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class Helpers {

    private static PasswordEncoder encoder;

    @Autowired
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.

        Random rnd = new Random();
        int number = rnd.nextInt(99999);

        // this will convert any number sequence into 6 character.
        return String.format("%05d", number);
    }

    public static String formatPhone(String phone) {
        String res = phone.substring(1);
        return "+233" + res;
    }

    public static HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status) {
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data", data);
        responseData.put("message", message);
        responseData.put("status", status.value());
        return responseData;
    }

    public static Date todayDate() {
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        return today;

    }

    public static String arrToString(String[] arr) {
        String test = "";
        for (int i = 0; i < arr.length; i++) {
            test += arr[i] + ",";
        }
        test = test.substring(0, test.length() - 1);
        return test;
    }


    public static void handleException(Exception e){
        String[] classArr = e.getClass().getCanonicalName().split("\\.");
        String className = classArr[classArr.length -1];
        switch (className){
            case "BadRequestException":
                throw new BadRequestException(e.getMessage());
            case "ResourceNotFoundException":
                throw new ResourceNotFoundException(e.getMessage());
            case "UnauthorizedException":
                throw new UnauthorizedException(e.getMessage());
            case "Forbidden":
                throw new Forbidden(e.getMessage());
            default:
                throw new UnexpectedFailure(e.getMessage());
        }
    }

    public static String hashPassword(String password){
        System.out.println(encoder);
        return encoder.encode(password);
    }

    public static long generateInvoiceNumber(){
        int min = 5000;
        int max = 1000000;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }


    public static String getCheckoutUrl(float amount, String email, MetaData metaData) throws JsonProcessingException {
         RestTemplate restTemplate = new RestTemplate();
        PaystackRequest paystackRequest = new PaystackRequest(amount, email, metaData);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk_test_0f1d50d1f476e5cd80b1f16563fbab39282cd1f5");

        String requestEntity = getBody(paystackRequest);
        HttpEntity<String> entity = new HttpEntity<String>(requestEntity,headers);

        String paystackUrl = "https://api.paystack.co/transaction/initialize";
        ResponseEntity<Response> response = restTemplate.postForEntity(paystackUrl, entity, Response.class);

        return response.getBody().data.authorization_url;

    }


    public static PaystackCallbackResponse verifyPayment(String reference){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer sk_test_0f1d50d1f476e5cd80b1f16563fbab39282cd1f5");

        String paystackUrl = "https://api.paystack.co/transaction/verify/"+reference;
        ResponseEntity<PaystackCallbackResponse> response = restTemplate.getForEntity(paystackUrl, PaystackCallbackResponse.class);
        return response.getBody();
    }

    private static String getBody(final PaystackRequest paystackRequest) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(paystackRequest);
    }


}
