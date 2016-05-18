package com.upc.book.rule;


public class ItemPair {
    Item left;
    Item right;

    public ItemPair() {
        this.left = new Item();
        this.right = new Item();
    }

    public ItemPair(Item item) {
        this.left = new Item();
        this.right = item;
    }

    public ItemPair(Item left, Item right) {
        this.left = left;
        this.right = right;
    }

    //把item的数据放在itemPair的left项集合
    public static ItemPair expend(Item item, ItemPair itemPair) {
        ItemPair itemPairResult = new ItemPair();
        itemPairResult.left.getElements().addAll(itemPair.getLeft().getElements());
        itemPairResult.left.getElements().addAll(item.getElements());
        itemPairResult.right.getElements().addAll(itemPair.getRight().getElements());

        return itemPairResult;
    }

    //把item的数据放在itemPair的right项集合
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
