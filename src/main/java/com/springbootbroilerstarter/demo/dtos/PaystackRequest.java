package com.springbootbroilerstarter.demo.dtos;

public class PaystackRequest {
    public float amount;
    public String email;
    public Long metadata;

    public PaystackRequest(float amount, String email, Long metaData) {
        this.amount = amount;
        this.email = email;
        this.metadata = metaData;
    }
}
