package com.springbootbroilerstarter.demo.services.implementations;

import com.springbootbroilerstarter.demo.domains.Bin;
import com.springbootbroilerstarter.demo.repositories.BinRepository;
import com.springbootbroilerstarter.demo.services.interfaces.BinService;
import com.springbootbroilerstarter.demo.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    private BinRepository binRepository;

    @Override
    public HashMap<String, Object> create(Bin bin) {
        try{
            Bin binResponse = this.binRepository.save(bin);
            return Helpers.responseAPI(binResponse, "Bin saved successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> update(Bin bin) {
        try{
            Optional<Bin> binFound = this.binRepository.findById(bin.getId());
            if(binFound.isEmpty()){
                return Helpers.responseAPI(null, "No BIN found", HttpStatus.BAD_REQUEST);
            }
            Bin binResponse = this.binRepository.save(bin);
            return Helpers.responseAPI(binResponse, "Bin updated successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> fetchAll() {
        try{
            List<Bin> binResponse = this.binRepository.findAll();
            return Helpers.responseAPI(binResponse, "Bin fetched successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> get(Long id) {
        try{
            Optional<Bin> binResponse = this.binRepository.findById(id);
            if(binResponse.isEmpty()){
                return Helpers.responseAPI(null, "No BIN found", HttpStatus.BAD_REQUEST);
            }
            return Helpers.responseAPI(binResponse, "Bin fetched successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    public void updateBinSensor(int status){
        Bin bin = this.binRepository.findById(1L).get();
        bin.setIsFull(status);
        this.binRepository.save(bin);
    }

    @Override
    public HashMap<String, Object> delete(Long id) {
        try{
            this.binRepository.deleteById(id);
            return Helpers.responseAPI(null, "Bin deleted successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
