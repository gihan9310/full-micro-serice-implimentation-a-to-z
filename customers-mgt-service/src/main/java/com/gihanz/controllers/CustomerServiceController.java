package com.gihanz.controllers;

import com.gihanz.dtos.CustomerDTO;
import com.gihanz.services.CustomerService;
import com.gihanz.utils.BindingErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "customers")
public class CustomerServiceController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "save")
    public ResponseEntity<?> createCustomerAccount(@RequestBody @Valid CustomerDTO dto , BindingResult bindingResult){

        log.debug("CustomerServiceController save ");

        if(bindingResult.hasErrors()){
            return BindingErrorsResponse.errorFields(bindingResult.getFieldErrors());
        }
        CustomerDTO account = customerService.createCustomerAccount(dto);
        return new ResponseEntity(account, HttpStatus.OK);
    }

    @GetMapping(value = "findAll")
    public ResponseEntity<?> findAll(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity(allCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "findById/{id}")
    public ResponseEntity<?> findAll(@PathVariable("id") Long id){
       CustomerDTO dto = customerService.findCustomerById(id);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping(value = "findByNic/{nic}")
    public ResponseEntity<?> findAll(@PathVariable("nic") String nic){
        CustomerDTO dto = customerService.findCustomerByINic(nic);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
