package com.gihanz.controllers;

import com.gihanz.dtos.ItemDTO;
import com.gihanz.services.ItemService;
import com.gihanz.utils.BindingErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "items")
@CrossOrigin()
@RefreshScope
public class ItemServiceController {

    @Autowired
    private ItemService itemService;

    @Value("${item.service.profile}")
    private String profile;

    @PostMapping("create")
    public ResponseEntity<?> createItem(@RequestBody @Valid ItemDTO dto , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return BindingErrorsResponse.errorFields(bindingResult.getFieldErrors());
        }
        ItemDTO item = itemService.createItem(dto);
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @GetMapping("getAllItems")
    public ResponseEntity<?> getAllItems(){
        log.debug("GET ALL ITEMS API CALLING...");
        List<ItemDTO> allItems = itemService.getAllItems();
        return new ResponseEntity(allItems, HttpStatus.OK);
    }

    @GetMapping(value = "findByItemCode/{itemCode}")
    public ResponseEntity<?> getAllItems(@PathVariable("itemCode") String itemCode){
        log.debug("GET ITEM API CALLING...");
        ItemDTO dto = itemService.findByItemCode(itemCode);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PostMapping("update-item-qty")
    public ResponseEntity<?> updateItemQty(@RequestBody Map<String,Double> listOfItems){
        boolean result = itemService.updateItemQty(listOfItems);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "check-profile")
    public ResponseEntity<?> getProfile(){
        return new ResponseEntity(this.profile, HttpStatus.OK);
    }
}
