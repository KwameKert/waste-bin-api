package com.springbootbroilerstarter.demo.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Channel {
    public int id;
    public String name;
    public String description;
    public String latitude;
    public String longitude;
    public String field1;
    public String field2;
    public Date created_at;
    public Date updated_at;
    public int last_entry_id;
}
