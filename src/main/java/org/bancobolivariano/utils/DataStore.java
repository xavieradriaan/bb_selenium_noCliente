package org.bancobolivariano.utils;

import java.util.Map;

public class DataStore {
    private static DataStore instance;
    private Map<Integer, Map<String, String>> fileData;

    private DataStore() {}

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void setFileData(Map<Integer, Map<String, String>> fileData) {
        this.fileData = fileData;
    }

    public Map<Integer, Map<String, String>> getFileData() {
        return fileData;
    }

    public Map<String, String> getRowData(int id) {
        return fileData != null ? fileData.get(id) : null;
    }
}
