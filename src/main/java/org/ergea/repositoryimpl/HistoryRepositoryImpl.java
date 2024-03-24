package org.ergea.repositoryimpl;

import org.ergea.model.History;
import org.ergea.model.OrderItem;
import org.ergea.repository.HistoryRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.ergea.utils.Utils.*;

public class HistoryRepositoryImpl implements HistoryRepository {
    @Override
    public void writeHistoryFile(History historyItems, File file) throws IOException {
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(file))) {
            bwr.write("--------------------------------------------\n");
            bwr.write("Binarfud\n");
            bwr.write("--------------------------------------------\n");
            bwr.write("Thank you for ordering at Binarfud\n\n");
            bwr.write(String.format("Receipt ID: %s\n", file.getName()));
            bwr.write("Order Date: " + DATE_TIME_FORMATTER.format(LOCAL_DATE_NOW) + "\n\n");
            bwr.write("Order Details:\n\n");
            int count = 1;
            for (OrderItem orderItem : historyItems.getOrderItems()) {
                bwr.write(String.format("%d. %s\t%d\t%s\n", count++, orderItem.getMenuItem().getName(),
                        orderItem.getQuantity(), formatToRupiah(historyItems.getOrderItems().stream().mapToInt(value -> value.getMenuItem().getPrice() * value.getQuantity()).sum())));
            }
            bwr.write("-------------------------------------------+\n");
            bwr.write(String.format("Total Quantity: %d Total Price: %s\n", historyItems.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum(),
                    formatToRupiah(historyItems.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum())));
            bwr.write("--------------------------------------------\n");
            bwr.write("Payment Method: Binarcash\n\n");
            bwr.write("--------------------------------------------\n");
            bwr.write("Save this receipt as\nproof of payment\n");
            bwr.write("--------------------------------------------\n");
        }
    }

    @Override
    public void printOrder(History historyItems, String folderName) {
        int uniqueNumber = 1;
        String fileName = generateCsvFileName(uniqueNumber);
        File file = new File(folderName, fileName);

        while (file.exists()) {
            uniqueNumber++;
            fileName = generateCsvFileName(uniqueNumber);
            file = new File(folderName, fileName);
        }

        try {
            file.createNewFile();
            writeHistoryFile(historyItems, file);
            System.out.println("Order receipt created successfully in: " + folderName);
        } catch (IOException e) {
            System.out.println("Error creating order receipt: " + e.getMessage());
        }
    }
}
