package com.springbootbroilerstarter.demo.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Data
@Setter
@Getter
@NoArgsConstructor
public class CallbackData{
    public int amount;
    public String currency;
    public Date transaction_date;
    public String status;
    public String reference;
    public String domain;
    public MetaData metadata;
    public String gateway_response;
    public Object message;
    public String channel;
    public String ip_address;
    public Object fees;
   // public Customer customer;
    public String plan;
    public int requested_amount;
}
