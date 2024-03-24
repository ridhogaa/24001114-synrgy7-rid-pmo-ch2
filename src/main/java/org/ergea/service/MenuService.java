package org.ergea.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ergea.repository.MenuRepository;

@Data
@AllArgsConstructor
public class MenuService {
    private MenuRepository menuRepository;
}
