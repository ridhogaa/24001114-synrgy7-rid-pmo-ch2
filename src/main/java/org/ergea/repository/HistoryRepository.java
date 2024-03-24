package org.ergea.repository;

import org.ergea.model.History;
import org.ergea.model.OrderItem;

import java.io.File;
import java.io.IOException;

public interface HistoryRepository {
    void writeHistoryFile(History historyItems, File file) throws IOException;

//    public int getTotalPrice() {
//        return orderItems.stream()
//                .mapToInt(OrderItem::getTotal)
//                .sum();
//    }
//
//    public int getTotalQuantity() {
//        return orderItems.stream()
//                .mapToInt(OrderItem::getQuantity)
//                .sum();
//    }

    void printOrder(History historyItems, String folderName);
}
