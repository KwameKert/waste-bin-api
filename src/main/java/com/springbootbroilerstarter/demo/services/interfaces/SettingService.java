package com.springbootbroilerstarter.demo.services.interfaces;

import com.springbootbroilerstarter.demo.dtos.AuthRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public interface SettingService {

    HashMap<String, Object> getDashboard();
}
