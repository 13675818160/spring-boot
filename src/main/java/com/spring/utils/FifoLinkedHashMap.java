package com.spring.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by maobg on 2015/10/20.
 */
public class FifoLinkedHashMap<K,V> extends LinkedHashMap<K,V> {
    public FifoLinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        MAX_ENTRIES = initialCapacity;
    }

    public FifoLinkedHashMap(int initialCapacity) {
        super(initialCapacity);
        MAX_ENTRIES = initialCapacity;
    }

    public FifoLinkedHashMap() {
        super();
    }

    private int MAX_ENTRIES = 100;

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }
}