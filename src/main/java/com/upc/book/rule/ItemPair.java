package com.upc.book.rule;

/**
 * Created by florian on 16/4/19.
 */
public class ItemPair {
    Item left;
    Item right;

    public ItemPair() {
        this(new Item());
    }

    public ItemPair(Item item) {
        this(new Item(), item);
    }

    public ItemPair(Item left, Item right) {
        this.left = left;
        this.right = right;
    }

    public static ItemPair expend(Item item, ItemPair itemPair) {
        ItemPair itemPairResult = new ItemPair();
        itemPairResult.left.getElements().addAll(itemPair.getLeft().getElements());
        itemPairResult.left.getElements().addAll(item.getElements());
        itemPairResult.right.getElements().addAll(itemPair.getRight().getElements());

        return itemPairResult;
    }

    public static ItemPair expend(ItemPair itemPair, Item item) {
        ItemPair itemPairResult = new ItemPair();
        itemPairResult.left.getElements().addAll(itemPair.getLeft().getElements());
        itemPairResult.right.getElements().addAll(itemPair.getRight().getElements());
        itemPairResult.right.getElements().addAll(item.getElements());

        return itemPairResult;
    }

    public Item getLeft() {
        return left;
    }

    public void setLeft(Item left) {
        this.left = left;
    }

    public Item getRight() {
        return right;
    }

    public void setRight(Item right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ItemPair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
