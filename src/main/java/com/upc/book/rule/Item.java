package com.upc.book.rule;


import java.util.*;

/**
 * Created by florian on 16/4/11.
 */
public class Item {
  TreeSet<String> elements = new TreeSet<>();

  public Item() {
  }

  public Item(String bookId) {
    if (bookId == null) {
      return;
    }

    elements.add(bookId);
  }

  public Item(Collection<String> bookIds) {
    if (bookIds == null) {
      return;
    }

    for (String bookId : bookIds) {
      elements.add(bookId);
    }
  }

  public boolean contains(Item item) {
    // containsAll 是Java TreeSet自带的函数
    return elements.containsAll(item.elements);
  }

  //Item前缀是否相同
  private static boolean canJoin(Item itemLeft, Item itemRight) {
    //两个item的元素个数必须相等，且不等于零
    if (itemLeft.elements.size() == 0 || itemLeft.elements.size() != itemRight.elements.size()) {
      return false;
    }

    //取出公众前缀，headset和last函数都是TreeSet自带的库函数
    SortedSet<String> commonLeft = itemLeft.elements.headSet(itemLeft.elements.last());
    SortedSet<String> commonRight = itemRight.elements.headSet(itemRight.elements.last());

    //公共前缀不相等
    if (!commonLeft.containsAll(commonRight) || !commonRight.containsAll(commonLeft)) {
      return false;
    }

    return true;
  }

  public static Item join(Item itemLeft, Item itemRight) {
    Item resultItem = new Item();

    //如果可以连接
    if (canJoin(itemLeft, itemRight)) {
      //先添加左边item的所有元素
      resultItem.elements.addAll(itemLeft.elements);
      //再添加右边item的最后一个元素
      resultItem.elements.add(itemRight.elements.last());
    }

    return resultItem;
  }

  //获取joinedItem的所有的 低一阶的子集 集合
  public static List<Item> getOriginSubItems(Item joinedItem) {
    List<Item> subItems = new ArrayList<>();

    //遍历集合元素
    for (String current : joinedItem.elements) {
      //新建空的Item对象
      Item subItem = new Item();
      //先把集合的元素全部加进去
      subItem.elements.addAll(joinedItem.elements);
      //去除当前的元素，得到n-1阶Item
      subItem.elements.remove(current);

      subItems.add(subItem);
    }

    return subItems;
  }

  private static List<ItemPair> partitionPairs(Item item) {
    List<ItemPair> itemPairs = new ArrayList<>();

    if (item.elements.size() < 2) {
      itemPairs.add(new ItemPair(item));
      return itemPairs;
    }

    String lastElement = item.elements.last();
    SortedSet<String> subItemElements = item.elements.headSet(lastElement);

    Item lastElementItem = new Item(lastElement);
    Item subItem = new Item(subItemElements);

    List<ItemPair> subItemPairs = partitionPairs(subItem);
    for (ItemPair subItemPair : subItemPairs) {
      itemPairs.add(subItemPair.expend(lastElementItem, subItemPair));
      itemPairs.add(subItemPair.expend(subItemPair, lastElementItem));
    }

    return itemPairs;
  }

  public static List<ItemPair> getSubItemPairs(Item item) {
    List<ItemPair> itemPairs = partitionPairs(item);
    itemPairs.remove(itemPairs.size() - 1);

    return itemPairs;
  }

  public String prettyFormat() {
    StringBuffer stringBuffer = new StringBuffer(elements.first());

    for (String element : elements.tailSet(elements.first(), false)) {
      stringBuffer.append(" ^ " + element);
    }

    return stringBuffer.toString();
  }

  public TreeSet<String> getElements() {
    return elements;
  }

  public void setElements(TreeSet<String> elements) {
    this.elements = elements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    return elements.containsAll(item.elements) && item.elements.containsAll(elements);

  }

  @Override
  public int hashCode() {
    return Arrays.toString(elements.toArray()).hashCode();
  }

  @Override
  public String toString() {
    return "Item{" +
      "elements=" + Arrays.toString(elements.toArray()) +
      '}';
  }


}
