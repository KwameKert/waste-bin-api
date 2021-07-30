package com.springbootbroilerstarter.demo.services.interfaces;

import com.springbootbroilerstarter.demo.domains.User;
import com.springbootbroilerstarter.demo.dtos.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface UserService {

     User findUserByEmail(String email);
     User findUserByUserName(String userName);
     HashMap<String, Object> saveUser(UserDTO userDTO );

}
