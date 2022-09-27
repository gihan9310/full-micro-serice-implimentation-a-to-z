package com.gihanz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient()
@SpringBootApplication
public class ItemMgtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemMgtServiceApplication.class, args);
	}

}
