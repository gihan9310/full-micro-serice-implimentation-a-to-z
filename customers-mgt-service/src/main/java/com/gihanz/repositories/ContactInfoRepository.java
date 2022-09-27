package com.gihanz.repositories;

import com.gihanz.entities.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo ,Long> {

    ContactInfo findByCustomer_Id(Long id);
}
