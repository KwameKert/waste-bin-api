package com.springbootbroilerstarter.demo.services.interfaces;

import com.springbootbroilerstarter.demo.domains.Bin;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface BinService {

    public HashMap<String, Object> create(Bin bin);
    public HashMap<String, Object> update(Bin bin);
    public HashMap<String, Object> fetchAll();
    public HashMap<String, Object> get(Long id);
    public HashMap<String, Object> delete(Long id);
    public void updateBinSensor(int status);

}
