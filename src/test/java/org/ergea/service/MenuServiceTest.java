package org.ergea.service;

import org.ergea.model.MenuItem;
import org.ergea.repository.MenuRepository;
import org.ergea.repositoryimpl.MenuRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {
    private MenuRepository menuRepository;
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        menuRepository = new MenuRepositoryImpl();
        menuService = new MenuService(menuRepository);
    }

    @AfterEach
    void tearDown() {
        menuRepository = null;
        menuService = null;
    }

    @Test
    void testGetMenuItems() {
        Optional<List<MenuItem>> menuItemsOptional = menuService.getMenuRepository().getMenuItems();
        assertTrue(menuItemsOptional.isPresent());
        MenuItem firstItem = menuItemsOptional.get().get(0);
        assertEquals(5, menuItemsOptional.get().size());
        assertEquals("Nasi Goreng", firstItem.getName());
        assertEquals(15000, firstItem.getPrice());
    }

    @Test
    void testReadMenuItemsWithValidCSV() {
        Optional<List<MenuItem>> menuItemsOptional = menuService.getMenuRepository().readMenuItems();

        assertTrue(menuItemsOptional.isPresent());
    }
}