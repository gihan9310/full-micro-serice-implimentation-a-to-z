package com.gihanz.controllers;

import com.gihanz.dtos.OrderDto;
import com.gihanz.services.OrderService;
import com.gihanz.utils.BindingErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j()
@RestController()
@RequestMapping(value = "orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<?>createOrder(@RequestBody @Valid OrderDto dto ,BindingResult bindingResult){
        log.debug("Calling................");
        if(bindingResult.hasErrors()){
            return BindingErrorsResponse.errorFields(bindingResult.getFieldErrors());
        }
        OrderDto order = orderService.createOrder(dto);
        return new ResponseEntity(order, HttpStatus.OK);
    }
}
