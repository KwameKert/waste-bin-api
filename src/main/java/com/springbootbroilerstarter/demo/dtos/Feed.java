package com.springbootbroilerstarter.demo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Feed {

    public Date created_at;
    public int entry_id;
    public String field1;
    public String field2;
}
