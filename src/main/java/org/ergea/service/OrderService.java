package org.ergea.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ergea.repository.OrderRepository;

@Data
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
}