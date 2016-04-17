package com.upc.book.rule;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public String toString() {
        return "OrderList{" +
                "items=" + Arrays.toString(items.toArray()) +
                '}';
    }
}
