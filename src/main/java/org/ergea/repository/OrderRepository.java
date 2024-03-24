package org.ergea.repository;

import org.ergea.model.MenuItem;

import java.util.List;

public interface OrderRepository {
    int getTotalPrice();

    int getTotalQuantity();

    List<MenuItem> getListOrder();
}
