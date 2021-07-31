package com.springbootbroilerstarter.demo.dtos;


import com.springbootbroilerstarter.demo.utils.Helpers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthRequest {
    private String username;
    private String password;

}
