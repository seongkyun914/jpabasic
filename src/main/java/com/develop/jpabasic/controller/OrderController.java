package com.develop.jpabasic.controller;

import com.develop.jpabasic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
}
