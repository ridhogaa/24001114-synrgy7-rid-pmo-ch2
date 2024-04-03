package org.ergea.repository;

import org.ergea.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    Optional<List<MenuItem>> getMenuItems();

    Optional<List<MenuItem>> readMenuItems();
}
