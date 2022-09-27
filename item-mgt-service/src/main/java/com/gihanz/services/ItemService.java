package com.gihanz.services;

import com.gihanz.dtos.ItemDTO;
import com.gihanz.entities.Item;
import com.gihanz.exceptions.ItemException;
import com.gihanz.repositories.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public ItemDTO createItem(ItemDTO dto) {

        if (itemRepository.existsByItemCode(dto.getItemCode())) {
            throw new ItemException("Already used item code.");
        }

        try {
            Item entity = dto.getEntity();
            Item save = this.itemRepository.save(entity);
            return save.getDto();
        } catch (Exception e) {
            throw new ItemException("Can not create item.");
        }
    }

    public List<ItemDTO> getAllItems() {
        try {
            List<Item> all = this.itemRepository.findAll();
            return all.stream().map(item -> item.getDto()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItemException("Can not get all items.");
        }
    }

    public ItemDTO findByItemCode(String itemCode) {
        try {
            Item item = this.itemRepository.findByItemCode(itemCode);
            return item.getDto();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItemException("Can not find item (" + itemCode + ").");
        }
    }

    @Transactional()
    public boolean updateItemQty(Map<String, Double> list) {
        log.info("Calling.............." + list);
        try {
            list.forEach((key, qty) -> {
                Item item = this.itemRepository.findByItemCode(key);
                if (item == null) {
                    throw new ItemException("Item not found ( " + key + " )");
                }
                if (item.getItemQty() >= qty) {
                    item.setItemQty(item.getItemQty() - qty);
                } else {
                    throw new ItemException("Item qty is not available.");
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
