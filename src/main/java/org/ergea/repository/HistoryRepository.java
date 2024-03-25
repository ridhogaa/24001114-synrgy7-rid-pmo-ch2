package org.ergea.repository;

import org.ergea.model.History;

import java.io.File;
import java.io.IOException;

public interface HistoryRepository {
    void writeHistoryFile(History historyItems, File file) throws IOException;
    void printOrder(History historyItems, String folderName);
}
