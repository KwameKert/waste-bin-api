package com.springbootbroilerstarter.demo.utils;

import com.springbootbroilerstarter.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

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

}
