package com.springbootbroilerstarter.demo.dtos;

public class PaystackRequest {
    public float amount;
    public String email;
    public MetaData metaData;

    public PaystackRequest(float amount, String email, MetaData metaData) {
        this.amount = amount;
        this.email = email;
        this.metaData = metaData;
    }
}
