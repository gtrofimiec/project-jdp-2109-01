package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/orders")
@RequiredArgsConstructor
public class OrderController {

    private final SecurityService securityService;

    @GetMapping
    public List<OrderDto> getOrders() {
        return Arrays.asList(
                new OrderDto(new BigDecimal(100)), new OrderDto(new BigDecimal(200)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto addOrder(@RequestBody OrderDto orderDto,
                             @RequestParam("userId") Long userId,
                             @RequestParam("accessKey") String accessKey) {

        securityService.validateAccess(userId, accessKey);

        return orderDto;
    }

    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return new OrderDto(new BigDecimal(300));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{orderId}")
    public OrderDto updateOrder(@PathVariable("orderId") Long orderId, @RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        System.out.println("Order number " + orderId + " deleted.");
    }
}
