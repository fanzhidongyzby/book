package com.upc.book.rule;

import java.util.*;

/**
 * Created by florian on 16/4/11.
 */
public class ItemCollection {

  Map<Item, Integer> itemCountMap = new HashMap<>();

  public ItemCollection() {
  }

  public ItemCollection(OrderList orderList) {
    List<Item> items = orderList.getItems();

    //第一次生成1项集
    if (itemCountMap.isEmpty()) {
      for (Item item : items) {
        TreeSet<String> elements = item.getElements();
        for (String element : elements) {
          Item elemItem = new Item(element);
          itemCountMap.put(elemItem, 0);
        }
      }
    }
  }

  public boolean isEmpty() {
    return itemCountMap.isEmpty();
  }

  //对项集计数
  public void countItem(OrderList orderList) {
    List<Item> items = orderList.getItems();

    //对于任意项集
    Set<Item> countItems = itemCountMap.keySet();
    for (Item countItem : countItems) {
      for (Item item : items) {
        if (item.contains(countItem)) {
          itemCountMap.put(countItem, itemCountMap.get(countItem) + 1);
        }
      }
    }
  }

  //过滤：根据最小支持度过滤项集
  public static ItemCollection filter(ItemCollection itemCollection, int minSupportValue) {
    ItemCollection itemCollectionResult = new ItemCollection();
    Map<Item, Integer> itemCountMapResult = itemCollectionResult.getItemCountMap();

    Map<Item, Integer> itemCountMap = itemCollection.getItemCountMap();
    for (Item item : itemCountMap.keySet()) {
      Integer value = itemCountMap.get(item);
      if (value >= minSupportValue) {
        itemCountMapResult.put(item, value);
      }
    }

    return itemCollectionResult;
  }

  //裁剪：
  public static ItemCollection trim(ItemCollection joinedItemCollection, ItemCollection itemCollection) {
    ItemCollection itemCollectionResult = new ItemCollection();
    Map<Item, Integer> itemCountMapResult = itemCollectionResult.getItemCountMap();

    Map<Item, Integer> joinedItemCountMap = joinedItemCollection.getItemCountMap();
    Set<Item> items = itemCollection.getItemCountMap().keySet();

    for (Item joinedItem : joinedItemCountMap.keySet()) {
      List<Item> subItems = Item.getOriginSubItems(joinedItem);
      if (items.containsAll(subItems)) {
        itemCountMapResult.put(joinedItem,joinedItemCountMap.get(joinedItem));
      }
    }

    return itemCollectionResult;
  }

  //连接：公共前缀归并
  public static ItemCollection join(ItemCollection itemCollection) {
    ItemCollection itemCollectionResult = new ItemCollection();
    Map<Item, Integer> itemCountMapResult = itemCollectionResult.getItemCountMap();

    Map<Item, Integer> itemCountMap = itemCollection.getItemCountMap();
    Object[] items = itemCountMap.keySet().toArray();
    for (int i = 0; i < items.length - 1; i++) {
      for (int j = i + 1; j < items.length; j++) {
        Item itemLeft = (Item)items[i];
        Item itemRight = (Item)items[j];
        Item joinedItem = Item.join(itemLeft, itemRight);
        if (!joinedItem.getElements().isEmpty()) {
          itemCountMapResult.put(joinedItem, 0);
        }
      }
    }

    return itemCollectionResult;
  }

  //合并项集列表的所有项集到一个项集内，用于生成规则时查询
  public static ItemCollection merge(List<ItemCollection> itemCollectionList) {
    ItemCollection mergedItemCollection = new ItemCollection();

    for (ItemCollection itemCollection : itemCollectionList) {
      mergedItemCollection.itemCountMap.putAll(itemCollection.itemCountMap);
    }

    return mergedItemCollection;
  }

  public Map<Item, Integer> getItemCountMap() {
    return itemCountMap;
  }

  public void setItemCountMap(Map<Item, Integer> itemCountMap) {
    this.itemCountMap = itemCountMap;
  }
}
