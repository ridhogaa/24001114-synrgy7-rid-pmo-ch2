package org.ergea.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class History {
    private List<OrderItem> orderItems;
}
