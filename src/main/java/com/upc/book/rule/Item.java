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

  public Item(Set<String> bookIds) {
    if (bookIds == null) {
      return;
    }

    for (String bookId : bookIds) {
      elements.add(bookId);
    }
  }

  private static boolean canJoin(Item itemLeft, Item itemRight) {
    if (itemLeft.elements.size() == 0 || itemLeft.elements.size() != itemRight.elements.size()) {
      return false;
    }

    SortedSet<String> commonLeft = itemLeft.elements.headSet(itemLeft.elements.last());
    SortedSet<String> commonRight = itemRight.elements.headSet(itemRight.elements.last());
    if (!commonLeft.containsAll(commonRight) || !commonRight.containsAll(commonLeft)) {
      return false;
    }

    return true;
  }

  public static Item join(Item itemLeft, Item itemRight) {
    Item resultItem = new Item();

    if (canJoin(itemLeft, itemRight)) {
      TreeSet<String> elements = resultItem.getElements();
      elements.addAll(itemLeft.elements);
      elements.add(itemRight.elements.last());
    }

    return resultItem;
  }

  public static List<Item> getSubItems(Item joinedItem) {
    List<Item> subItems = new ArrayList<>();
    Iterator<String> iterator = joinedItem.elements.iterator();
    while(iterator.hasNext()) {
      String current = iterator.next();
      Item subItem = new Item();
      subItem.elements.addAll(joinedItem.elements);
      subItem.elements.remove(current);
      subItems.add(subItem);
    }
    return subItems;
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
