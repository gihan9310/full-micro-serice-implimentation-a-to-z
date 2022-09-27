package com.gihanz.feignclient;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

//@LoadBalancerClient(value = "manage-items-service")
public class ItemServiceLoadBalanceConfig {
//
//    @LoadBalanced()
//    @Bean()
//    public Feign.Builder feignBuilder(){
//        return Feign.builder();
//    }
}
