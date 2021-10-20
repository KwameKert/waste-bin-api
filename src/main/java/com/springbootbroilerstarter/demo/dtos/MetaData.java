package com.springbootbroilerstarter.demo.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaData {
    private Long invoiceId;

    public MetaData(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
