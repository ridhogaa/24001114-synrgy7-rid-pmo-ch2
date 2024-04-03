package org.ergea.repositoryimpl;

import org.ergea.model.MenuItem;
import org.ergea.repository.MenuRepository;
import org.ergea.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuRepositoryImpl implements MenuRepository {
    @Override
    public Optional<List<MenuItem>> getMenuItems() {
        return readMenuItems();
    }

    @Override
    public Optional<List<MenuItem>> readMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Utils.csvMenuFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split(",");
                if (keyValue.length == 2) {
                    String name = keyValue[0].trim();
                    int price = Integer.parseInt(keyValue[1].trim());
                    menuItems.add(new MenuItem(name, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return Optional.of(menuItems);
    }
}