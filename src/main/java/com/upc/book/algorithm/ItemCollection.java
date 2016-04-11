package com.upc.book.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by florian on 16/4/11.
 */
public class ItemCollection {
    Map<Item, Integer> itemCountMap = new HashMap<>();

    public Map<Item, Integer> getItemCountMap() {
        return itemCountMap;
    }

    public void setItemCountMap(Map<Item, Integer> itemCountMap) {
        this.itemCountMap = itemCountMap;
    }
}
