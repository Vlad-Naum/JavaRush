package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap data = new DualHashBidiMap();

    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    public void put(Long key, String value) {
        data.put(key, value);
    }

    public String getValue(Long key) {
        return (String) data.get(key);
    }

    public Long getKey(String value) {
        return (Long) data.getKey(value);
    }
}
