package com.springbootbroilerstarter.demo.dtos;

public class PaystackRequest {
    public float amount;
    public String email;

    public PaystackRequest(float amount, String email) {
        this.amount = amount;
        this.email = email;
    }
}
