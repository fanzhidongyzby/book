package com.upc.book.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 16/4/11.
 */
public class OrderList {
    List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
