package com.springbootbroilerstarter.demo.controllers;


import com.springbootbroilerstarter.demo.domains.Bin;
import com.springbootbroilerstarter.demo.services.interfaces.BinService;
import com.springbootbroilerstarter.demo.services.interfaces.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bin")
public class BinController {

    @Autowired
    private BinService binService;

    @Autowired
    private SettingService settingService;

    @PostMapping
    public ResponseEntity<?> addBin(@RequestBody Bin bin) {
        return new ResponseEntity<>(binService.create(bin), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBin(@RequestBody Bin bin) {
        return new ResponseEntity<>(binService.update(bin), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> fetchBins() {
        return new ResponseEntity<>(binService.fetchAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> fetchBins(@PathVariable("id") Long id) {
        return new ResponseEntity<>(binService.get(id), HttpStatus.OK);
    }



    @GetMapping("/invoice")
    public ResponseEntity<?> fetchBinInvoices() {
        return new ResponseEntity<>(binService.invoiceList(), HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> fetchDashboard() {
        return new ResponseEntity<>(settingService.getDashboard(), HttpStatus.OK);
    }


    @GetMapping("/invoice/checkout/{id}")
    public ResponseEntity<?> getCheckoutUrl(@PathVariable("id") Long id) {
        return new ResponseEntity<>(binService.getInvoiceCheckoutUrl(id), HttpStatus.OK);
    }

    @GetMapping("/empty/{id}")
    public ResponseEntity<?> emptyBin(@PathVariable("id") Long id) {
        return new ResponseEntity<>(binService.emptyBin(id), HttpStatus.OK);
    }




}
