package com.gihanz.services;

import com.gihanz.dtos.CustomerDTO;
import com.gihanz.dtos.OrderDetailDto;
import com.gihanz.dtos.OrderDto;
import com.gihanz.entities.Order;
import com.gihanz.entities.OrderDetail;
import com.gihanz.exceptions.OrderException;
import com.gihanz.feignclient.CustomerFeignClient;
import com.gihanz.feignclient.ItemFeignClient;
import com.gihanz.repositories.OrderDetailRepository;
import com.gihanz.repositories.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {

    private static final String ITEM_QTY_NOT_AVAILABLE ="Itm quantity not available.";
    private static final String CUSTOMER_NOT_FOUND ="Customer Not Found.";
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CommonService commonService;

    @Transactional()
    public OrderDto createOrder(OrderDto dto) {

        List<OrderDetailDto> detailDtos = dto.getOrderDetailDtoList();

        if (detailDtos == null || detailDtos.isEmpty()) {
            throw new OrderException("Order product details not found.");
        }
        try {
            CustomerDTO customer = commonService.findCustomer(dto.getCustomerNic());
            log.info("Customer : "+customer);
            if(customer==null){
                throw new OrderException(CUSTOMER_NOT_FOUND);
            }
            Order entity = dto.getEntity();
            Order order = this.orderRepository.save(entity);
            List<OrderDetail> detailList = detailDtos.stream().map(detailDto -> detailDto.getEntity(order)).collect(Collectors.toList());
            List<OrderDetail> details = this.orderDetailRepository.saveAll(detailList);
            log.info("details : "+details);
            Map<String, Double> collect = detailDtos.stream().collect(Collectors.toMap(OrderDetailDto::getItemCode, OrderDetailDto::getQty));
            log.info("list collect : "+collect);
            boolean result =commonService. updateItemData(collect);
            if(!result){
                throw new OrderException(ITEM_QTY_NOT_AVAILABLE);
            }
            List<OrderDetailDto> detailDtoList = details.stream().map(OrderDetail::getDto).collect(Collectors.toList());
            OrderDto orderDto = order.getDto();
            orderDto.setOrderDetailDtoList(detailDtoList);
            return orderDto;
        } catch (Exception e) {

            String errorMgt ="Order can not create order.";
            if(e.getMessage().equals(ITEM_QTY_NOT_AVAILABLE)){
                errorMgt =e.getMessage();
                throw new OrderException(errorMgt);
            }
            if(e.getMessage().equals(CUSTOMER_NOT_FOUND)){
                errorMgt =e.getMessage();
                throw new OrderException(errorMgt);
            }
            e.printStackTrace();
            throw new OrderException(errorMgt);
        }
    }


}
