package org.ergea.service;

import org.ergea.model.MenuItem;
import org.ergea.model.OrderItem;
import org.ergea.repository.OrderRepository;
import org.ergea.repositoryimpl.OrderRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private List<OrderItem> orderItems;
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderItems = new ArrayList<>();
        orderRepository = new OrderRepositoryImpl(orderItems);
        orderService = new OrderService(orderRepository);
    }

    @AfterEach
    void tearDown() {
        orderItems = null;
        orderRepository = null;
        orderService = null;
    }

    @Test
    void testGetTotalPrice() {
        orderItems.add(new OrderItem(new MenuItem("Item1", 10), 2));
        orderItems.add(new OrderItem(new MenuItem("Item2", 20), 1));
        Optional<Integer> totalPrice = orderService.getOrderRepository().getTotalPrice();
        assertTrue(totalPrice.isPresent());
        assertEquals(40, totalPrice.get());
    }

    @Test
    void testGetTotalQuantity() {
        orderItems.add(new OrderItem(new MenuItem("Item1", 10), 2));
        orderItems.add(new OrderItem(new MenuItem("Item2", 20), 1));
        Optional<Integer> totalQuantity = orderService.getOrderRepository().getTotalQuantity();
        assertTrue(totalQuantity.isPresent());
        assertEquals(3, totalQuantity.get());
    }

    @Test
    void testGetTotalPriceEmptyOrder() {
        Optional<Integer> totalPrice = orderService.getOrderRepository().getTotalPrice();
        totalPrice.ifPresent(integer -> assertEquals(0, (int) integer));
    }

    @Test
    void testGetTotalQuantityEmptyOrder() {
        Optional<Integer> totalQuantity = orderService.getOrderRepository().getTotalQuantity();
        totalQuantity.ifPresent(integer -> assertEquals(0, (int) integer));
    }
}