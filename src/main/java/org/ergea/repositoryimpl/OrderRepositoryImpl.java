package org.ergea.repositoryimpl;

import org.ergea.model.MenuItem;
import org.ergea.model.OrderItem;
import org.ergea.repository.OrderRepository;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final List<OrderItem> orderItems;

    public OrderRepositoryImpl(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(value -> value.getMenuItem().getPrice() * value.getQuantity())
                .sum();
    }

    @Override
    public int getTotalQuantity() {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    @Override
    public List<MenuItem> getListOrder() {
        return orderItems.stream()
                .map(OrderItem::getMenuItem)
                .toList();
    }
}
