package org.ergea.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ergea.repository.HistoryRepository;

@Data
@AllArgsConstructor
public class HistoryService {
    private HistoryRepository historyRepository;
}
