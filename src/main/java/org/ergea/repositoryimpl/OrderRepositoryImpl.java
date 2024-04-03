package org.ergea.repositoryimpl;

import org.ergea.model.OrderItem;
import org.ergea.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    private final List<OrderItem> orderItems;

    public OrderRepositoryImpl(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public Optional<Integer> getTotalPrice() {
        return Optional.of(orderItems.stream()
                .mapToInt(value -> value.getMenuItem().getPrice() * value.getQuantity())
                .sum());
    }

    @Override
    public Optional<Integer> getTotalQuantity() {
        return Optional.of(orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum());
    }

}
