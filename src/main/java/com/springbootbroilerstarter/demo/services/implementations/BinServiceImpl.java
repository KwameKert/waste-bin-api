package com.springbootbroilerstarter.demo.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springbootbroilerstarter.demo.domains.Bin;
import com.springbootbroilerstarter.demo.domains.Invoice;
import com.springbootbroilerstarter.demo.repositories.BinRepository;
import com.springbootbroilerstarter.demo.repositories.InvoiceRepository;
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

    @Autowired
    private InvoiceRepository invoiceRepository;

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
            return Helpers.responseAPI(binResponse, "Bin updated successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> emptyBin(Long id) {

        try{
            Optional<Bin> binOptional = this.binRepository.findById(id);
            if(binOptional.isEmpty()){
                return Helpers.responseAPI(null, "No BIN found", HttpStatus.BAD_REQUEST);
            }
            Bin bin = binOptional.get();
            bin.setIsFull(0);
            this.binRepository.save(bin);
            this.recordInvoice(bin);
            return Helpers.responseAPI(binOptional.get(), "Bin updated successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> fetchAll() {
        try{
            List<Bin> binResponse = this.binRepository.findAll();
            return Helpers.responseAPI(binResponse, "Bin fetched successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }


    private void recordInvoice(Bin bin){
        Invoice invoice = new Invoice();
        invoice.setAmount(5.5f);
        invoice.setDriverId(2l);
        invoice.setCustomerId(3l);
        invoice.setInvoiceId(Helpers.generateInvoiceNumber());
        this.invoiceRepository.save(invoice);
    }


    @Override
    public HashMap<String, Object> get(Long id) {
        try{
            Optional<Bin> binResponse = this.binRepository.findById(id);
            if(binResponse.isEmpty()){
                return Helpers.responseAPI(null, "No BIN found", HttpStatus.BAD_REQUEST);
            }
            return Helpers.responseAPI(binResponse, "Bin fetched successfully",HttpStatus.OK);
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
            return Helpers.responseAPI(null, "Bin deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> invoiceList() {
        try{
            List<Invoice> invoiceList = this.invoiceRepository.findAll();
            return Helpers.responseAPI(invoiceList, "Invoices fetched successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> getInvoiceCheckoutUrl(Long id) {
        try{
            Optional<Invoice> invoiceOptional = this.invoiceRepository.findById(id);
            if(invoiceOptional.isEmpty())
                 return Helpers.responseAPI(null, "No Invoice found", HttpStatus.BAD_REQUEST);

            Integer amount = Math.round(invoiceOptional.get().getAmount() * 100);
            String url = Helpers.getCheckoutUrl(amount, "test@gmail.com");
            return Helpers.responseAPI(url, "Checkout url found", HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Helpers.responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }


}
