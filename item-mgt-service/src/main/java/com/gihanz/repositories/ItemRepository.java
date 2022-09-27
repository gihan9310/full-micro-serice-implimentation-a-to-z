package com.gihanz.repositories;

import com.gihanz.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item ,Long> {

    Item findByItemCode(String itemCode);
    boolean existsByItemCode(String itemCode);
}
