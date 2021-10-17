package com.springbootbroilerstarter.demo.services.implementations;

import com.springbootbroilerstarter.demo.repositories.BinRepository;
import com.springbootbroilerstarter.demo.repositories.UserRepository;
import com.springbootbroilerstarter.demo.services.interfaces.SettingService;
import com.springbootbroilerstarter.demo.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BinRepository binRepository;

    @Override
    public HashMap<String, Object> getDashboard() {
        try{
            int userSize = this.userRepository.findAll().size();
            int binSize = this.binRepository.findAll().size();
            float pendingTransactions = this.userRepository.sumPendingTransactions();
            float completedTransactions = this.userRepository.sumCompletedTransactions();

            HashMap<String, Object> response = new HashMap<>();
            response.put("userSize",userSize);
            response.put("binSize", binSize);
            response.put("pendingTransactions", pendingTransactions);
            response.put("completedTransactions", completedTransactions);

            return Helpers.responseAPI(response, "dashboard components ", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            Helpers.handleException(e);
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
