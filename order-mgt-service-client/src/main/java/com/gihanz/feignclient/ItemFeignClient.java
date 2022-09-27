package com.gihanz.feignclient;

import com.gihanz.dtos.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient( value = "order-mgt-api-gateway-service")
public interface ItemFeignClient {

    @GetMapping("manage-items-service/items/findByItemCode/{itemCode}")
    ItemDTO getByItemCode(@PathVariable String itemCode);

    @PostMapping("manage-items-service/items/update-item-qty")
    boolean updateItemQty(@RequestBody Map<String ,Double> listItems);

}
