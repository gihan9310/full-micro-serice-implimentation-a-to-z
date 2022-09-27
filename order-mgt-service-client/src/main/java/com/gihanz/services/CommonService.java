package com.gihanz.services;

import com.gihanz.dtos.CustomerDTO;
import com.gihanz.feignclient.CustomerFeignClient;
import com.gihanz.feignclient.ItemFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CommonService {
    @Autowired
    private ItemFeignClient itemFeignClient;

    @Autowired
    private CustomerFeignClient customerFeignClient;

    @CircuitBreaker(name = "customerService",fallbackMethod = "fallBackGetfindCustomerNic")
    public CustomerDTO findCustomer(String nic){
        log.info("nic : "+nic);
        return customerFeignClient.getCustomerByNic(nic);
    }

    @CircuitBreaker(name = "manageItemsService",fallbackMethod = "fallBackUpdateItemData")
    public boolean updateItemData(Map<String, Double> collect){
        return itemFeignClient.updateItemQty(collect);
    }

    public CustomerDTO fallBackGetfindCustomerNic(String nic ,Throwable throwable){
        log.info("fallBackGetfindCustomerNic : Calling..........");
        return null;
    }


    public boolean fallBackUpdateItemData(Map<String, Double> collect ,Throwable throwable){
        log.info("fallBackUpdateItemData : return fales..........");
        return false;
    }
}
