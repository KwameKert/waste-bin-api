package com.springbootbroilerstarter.demo.services.implementations;

import com.springbootbroilerstarter.demo.domains.Role;
import com.springbootbroilerstarter.demo.domains.User;
import com.springbootbroilerstarter.demo.dtos.UserDTO;
import com.springbootbroilerstarter.demo.exceptions.BadRequestException;
import com.springbootbroilerstarter.demo.exceptions.ResourceNotFoundException;
import com.springbootbroilerstarter.demo.repositories.RoleRepository;
import com.springbootbroilerstarter.demo.repositories.UserRepository;
import com.springbootbroilerstarter.demo.services.interfaces.UserService;
import com.springbootbroilerstarter.demo.utils.Helpers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
    @Override
    public HashMap<String, Object> saveUser(UserDTO userDTO) {
        try{
            if(userDTO.getFirstName().contains("kwame"))
                throw new ResourceNotFoundException("error here");
            User user = new User();
            modelMapper.map(userDTO, user);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setStatus(1);
            Role userRole = roleRepository.findByRole(userDTO.getRole());
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));

            return Helpers.responseAPI(userRepository.save(user), "user saved successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
             Helpers.handleException(e);
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    Role getUserRole(String role){
        return this.roleRepository.findByRole(role);
    }

}
