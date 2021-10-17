package com.springbootbroilerstarter.demo.services.interfaces;

import com.springbootbroilerstarter.demo.domains.Bin;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface BinService {

     HashMap<String, Object> create(Bin bin);
     HashMap<String, Object> update(Bin bin);
    HashMap<String, Object> emptyBin(Long id);
     HashMap<String, Object> fetchAll();
     HashMap<String, Object> get(Long id);
     HashMap<String, Object> delete(Long id);
     HashMap<String, Object> invoiceList();
     HashMap<String, Object> getInvoiceCheckoutUrl(Long id);
     void updateBinSensor(int status);

}
