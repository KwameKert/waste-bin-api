package com.springbootbroilerstarter.demo.controllers;


import com.springbootbroilerstarter.demo.domains.Bin;
import com.springbootbroilerstarter.demo.dtos.UserDTO;
import com.springbootbroilerstarter.demo.services.interfaces.BinService;
import com.springbootbroilerstarter.demo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bin")
public class BinController {

    @Autowired
    private BinService binService;

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




}
