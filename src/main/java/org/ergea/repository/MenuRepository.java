package org.ergea.repository;

import org.ergea.model.MenuItem;

import java.util.List;

public interface MenuRepository {
    List<MenuItem> getMenuItems();

    List<MenuItem> readMenuItems();

}
