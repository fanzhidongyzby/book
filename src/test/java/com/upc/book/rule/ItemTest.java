package com.upc.book.rule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by florian on 16/4/19.
 */
public class ItemTest {

    @Test
    public void testGetSubItemPairs() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Item item = new Item(list);
        List<ItemPair> itemPairs = Item.getSubItemPairs(item);

        System.out.print(itemPairs);
    }
}