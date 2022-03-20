package com.example.book_shopping.schedule;

import com.example.book_shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lengo
 * created on 3/19/2022
 */
@EnableAsync
@Component
public class Schedule {
    @Autowired
    private OrderService orderService;
    @Scheduled(fixedDelay = 60000)
    public void acceptOrder() {
        orderService.acceptOrder();
        orderService.delivered();
    }
}
