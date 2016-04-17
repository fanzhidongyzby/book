package com.upc.book.rule;

import java.util.*;

/**
 * Created by florian on 16/4/11.
 */
public class ItemCollection {

  public ItemCollection() {}

  public ItemCollection(OrderList orderList) {
    List<Item> items = orderList.getItems();
    for (Item item : items) {
      TreeSet<String> elements = item.getElements();
      for (String element : elements) {
        Item elemItem = new Item(element);
        if (itemCountMap.containsKey(elemItem)) {
          itemCountMap.put(elemItem, itemCountMap.get(elemItem) + 1);
        } else {
          itemCountMap.put(elemItem, 1);
        }
      }
    }
  }

  //根据最小支持度过滤项集
  public static ItemCollection filter(ItemCollection itemCollection, int minSupportValue) {
    ItemCollection resultItemCollection = new ItemCollection();
    Map<Item, Integer> resultItemCountMap = resultItemCollection.getItemCountMap();

    Map<Item, Integer> itemCountMap = itemCollection.getItemCountMap();
    for (Item item : itemCountMap.keySet()) {
      Integer value = itemCountMap.get(item);
      if (value >= minSupportValue) {
        resultItemCountMap.put(item, value);
      }
    }

    return resultItemCollection;
  }

  //裁剪
  public static ItemCollection trim(ItemCollection itemCollection) {
    ItemCollection resultItemCollection = new ItemCollection();
    Map<Item, Integer> resultItemCountMap = resultItemCollection.getItemCountMap();

    resultItemCollection.setItemCountMap(resultItemCountMap);

    return resultItemCollection;
  }

  Map<Item, Integer> itemCountMap = new HashMap<>();

  public Map<Item, Integer> getItemCountMap() {
        return itemCountMap;
    }

  public void setItemCountMap(Map<Item, Integer> itemCountMap) {
        this.itemCountMap = itemCountMap;
    }
}
