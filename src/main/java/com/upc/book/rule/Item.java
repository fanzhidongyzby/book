package com.upc.book.rule;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by florian on 16/4/11.
 */
public class Item {
    TreeSet<String> elements = new TreeSet<>();

    public Item() {}

    public Item(String bookId) {
        if (bookId == null) {
            return;
        }

        elements.add(bookId);
    }

    public Item(Set<String> bookIds) {
        if (bookIds == null) {
            return;
        }

        for (String bookId : bookIds) {
            elements.add(bookId);
        }
    }

    public TreeSet<String> getElements() {
        return elements;
    }

    public void setElements(TreeSet<String> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "Item{" +
                "elements=" + Arrays.toString(elements.toArray()) +
                '}';
    }
}
