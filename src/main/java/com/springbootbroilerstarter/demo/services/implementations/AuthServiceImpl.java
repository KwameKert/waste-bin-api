package com.springbootbroilerstarter.demo.services.implementations;

import com.springbootbroilerstarter.demo.domains.User;
import com.springbootbroilerstarter.demo.dtos.AuthRequest;
import com.springbootbroilerstarter.demo.exceptions.BadRequestException;
import com.springbootbroilerstarter.demo.repositories.UserRepository;
import com.springbootbroilerstarter.demo.services.interfaces.AuthService;
import com.springbootbroilerstarter.demo.utils.Helpers;
import com.springbootbroilerstarter.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {



    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwts;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwts, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwts = jwts;
        this.userRepository = userRepository;
    }

    @Override
    public HashMap<String, Object> login(AuthRequest authRequest) {
        try{
            //$2a$10$qmQhif1zIi46fOxq4fBY.uzs1KHXvLYFb8veB4QgHSxoJfR9PjpPC
            System.out.println(bCryptPasswordEncoder.matches(authRequest.getPassword(),"$2a$10$dD.CUVm1H/LdDG0KHDRb5OW/0MOQAEtaREhWFrwbX.VAu5lSEvaY2"));
            User user = this.userRepository.findByUsername(authRequest.getUsername());
            System.out.println(user);
            if(user == null || !bCryptPasswordEncoder.matches(authRequest.getPassword(),user.getPassword())){
                throw new BadRequestException("Invalid username or password");
            }
            String token = this.jwts.generateToken(user);
            HashMap<String, Object> data = new HashMap<>();
            data.put("token",token);
            data.put("user", user);
            return Helpers.responseAPI(data, "User logged in successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            Helpers.handleException(e);
           return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }
}
