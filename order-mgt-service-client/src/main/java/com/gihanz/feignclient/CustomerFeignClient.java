package com.gihanz.feignclient;

import com.gihanz.dtos.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( value = "order-mgt-api-gateway-service")
public interface CustomerFeignClient {

    @GetMapping("manage-customer-service/customers/findByNic/{nic}")
    CustomerDTO getCustomerByNic(@PathVariable("nic") String nic);
}
