package com.upc.book.rule;

import java.util.*;

/**
 * Created by florian on 16/4/11.
 */
public class ItemCollection {

  Map<Item, Integer> itemCountMap = new HashMap<>();

  public ItemCollection() {
  }

  //第一次生成1项集（此时支持度全为0）
  public ItemCollection(OrderList orderList) {
    // 取出orderlist的items成员
    List<Item> items = orderList.getItems();

    for (Item item : items) {

      TreeSet<String> elements = item.getElements();
      for (String element : elements) {
        Item elemItem = new Item(element);
        itemCountMap.put(elemItem, 0);
      }
    }
  }

  public boolean isEmpty() {
    return itemCountMap.isEmpty();
  }

  //根据orderlist对项集计数
  public void countItem(OrderList orderList) {
    List<Item> items = orderList.getItems();

    //对于任意项集根据key取支持度，再对项集的项计数，更新支持度
    Set<Item> countItems = itemCountMap.keySet();
    for (Item countItem : countItems) {
      for (Item item : items) {

        //orderlist的每一项（订单）是否包含项集的项
        if (item.contains(countItem)) {
          Integer oldValue = itemCountMap.get(countItem);
          itemCountMap.put(countItem, oldValue + 1);
        }
      }
    }
  }

  //过滤：根据最小支持度过滤项集
  public static ItemCollection filter(ItemCollection itemCollection, int minSupportValue) {
    ItemCollection itemCollectionResult = new ItemCollection();
    Map<Item, Integer> itemCountMapResult = itemCollectionResult.getItemCountMap();//获取项集ItemCountMap内部数据结构

    Map<Item, Integer> itemCountMap = itemCollection.getItemCountMap();
    for (Item item : itemCountMap.keySet()) {
      Integer value = itemCountMap.get(item);
      if (value >= minSupportValue) {
        itemCountMapResult.put(item, value);
      }
    }

    return itemCollectionResult;
  }

  //裁剪：joinedItemCollection是候选集，itemCollection是生成候选集的项集（老项集）
  public static ItemCollection trim(ItemCollection joinedItemCollection, ItemCollection itemCollection) {
    ItemCollection itemCollectionResult = new ItemCollection();

    Set<Item> items = itemCollection.itemCountMap.keySet();

    for (Item joinedItem : joinedItemCollection.itemCountMap.keySet()) {
      //获取候选n项集每一项的所有n-1阶子项的集合
      List<Item> subItems = Item.getOriginSubItems(joinedItem);

      //老项集的项包含这些子项集合，containsAll是Set的自带函数
      if (items.containsAll(subItems)) {
        //从候选集拷贝结果
        Integer value = joinedItemCollection.itemCountMap.get(joinedItem);
        itemCollectionResult.itemCountMap.put(joinedItem, value);
      }
    }

    return itemCollectionResult;
  }

  //连接：公共前缀归并（项集的链接操作，join属于ItemCollection类）
  public static ItemCollection join(ItemCollection itemCollection) {
    ItemCollection itemCollectionResult = new ItemCollection();
    Map<Item, Integer> itemCountMapResult = itemCollectionResult.getItemCountMap();

    Map<Item, Integer> itemCountMap = itemCollection.getItemCountMap();

    //将老的项集的所有的key原来的列表形式转换为数组
    Object[] items = itemCountMap.keySet().toArray();

    //两两组合
    for (int i = 0; i < items.length - 1; i++) {
      for (int j = i + 1; j < items.length; j++) {

        //类型转换
        Item itemLeft = (Item)items[i];
        Item itemRight = (Item)items[j];

        //连接任意两个项，这是Item类的join操作
        Item joinedItem = Item.join(itemLeft, itemRight);

        //连接成功，就把连接后的项记录下来
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
