package org.ergea.repository;

import java.util.Optional;

public interface OrderRepository {
    Optional<Integer> getTotalPrice();

    Optional<Integer> getTotalQuantity();
}
