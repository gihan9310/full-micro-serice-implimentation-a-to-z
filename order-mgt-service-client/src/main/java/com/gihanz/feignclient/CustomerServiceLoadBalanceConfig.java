package com.gihanz.feignclient;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@Slf4j()
//@LoadBalancerClient(value = "manage-customer-service")
public class CustomerServiceLoadBalanceConfig {

//    @LoadBalanced()
//    @Bean()
//    public Feign.Builder feignBuilder(){
//        log.info("CustomerServiceLoadBalanceConfig Feign ");
//       return Feign.builder();
//    }

}
