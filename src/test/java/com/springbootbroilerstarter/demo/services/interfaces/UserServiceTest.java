package com.springbootbroilerstarter.demo.services.interfaces;

import com.springbootbroilerstarter.demo.domains.User;
import com.springbootbroilerstarter.demo.dtos.UserDTO;
import com.springbootbroilerstarter.demo.repositories.RoleRepository;
import com.springbootbroilerstarter.demo.repositories.UserRepository;
import com.springbootbroilerstarter.demo.services.implementations.UserServiceImpl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserServiceImpl userServiceUnderTest;
    private User user;

    @BeforeEach
    public void setUp() {
       // initMocks(this);
        userServiceUnderTest = new UserServiceImpl(mockUserRepository,
                mockRoleRepository,
                mockBCryptPasswordEncoder);
        user = User.builder()
                .id(1l)
                .firstName("Gustavo")
                .lastName("Ponce")
                .email("test@test.com")
                .build();

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@test.com";

        final User result = userServiceUnderTest.findUserByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        HashMap<String, Object> result = userServiceUnderTest.saveUser(UserDTO.builder().build());

        // Verify the results
      //  assertEquals(email, result.get("data").getEmail());
    }

}