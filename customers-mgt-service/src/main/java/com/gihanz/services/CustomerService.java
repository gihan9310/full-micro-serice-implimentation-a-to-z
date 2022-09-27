package com.gihanz.services;

import com.gihanz.dtos.ContactInfoDTO;
import com.gihanz.dtos.CustomerDTO;
import com.gihanz.entities.ContactInfo;
import com.gihanz.entities.Customer;
import com.gihanz.exceptions.CustomerException;
import com.gihanz.repositories.ContactInfoRepository;
import com.gihanz.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Transactional()
    public CustomerDTO createCustomerAccount(CustomerDTO dto) {

        ContactInfoDTO contactInfoDTO = dto.getContactInfoDTO();
        if (contactInfoDTO == null) {
            throw new CustomerException("Can not found contact information.");
        }
        if(this.customerRepository.existsByNic(dto.getNic())){
            throw new CustomerException("Already used nic number.");
        }
        try {
            Customer entity = dto.getEntity();
            Customer save = customerRepository.save(entity);
            ContactInfo contactInfo = contactInfoDTO.getEntity();
            contactInfo.setCustomer(save);
            ContactInfo info = contactInfoRepository.save(contactInfo);
            CustomerDTO savedDto = save.getDto();
            savedDto.setContactInfoDTO(info.getDto());
            return savedDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("Can not create customer account");
        }
    }

    public List<CustomerDTO> getAllCustomers() {

        try {
            List<Customer> customerList = customerRepository.findAll();
            List<CustomerDTO> tempList = new ArrayList<>();
            customerList.forEach(customer -> {
                ContactInfo contactInfo = this.contactInfoRepository.findByCustomer_Id(customer.getId());
                CustomerDTO dto = customer.getDto();
                dto.setContactInfoDTO(contactInfo == null ? null : contactInfo.getDto());
                tempList.add(dto);
            });
            return tempList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("Can not get customer information");
        }
    }

    public CustomerDTO findCustomerById(Long id){
        try {
            Optional<Customer> optional = customerRepository.findById(id);
            return   optional.isPresent() ? optional.get().getDto():null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("Can not find customer by id");
        }
    }

    public CustomerDTO findCustomerByINic(String nic){
        try {
            Optional<Customer> optional = customerRepository.findByNic(nic);
            return   optional.isPresent() ? optional.get().getDto():null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("Can not find customer by id");
        }
    }
}
