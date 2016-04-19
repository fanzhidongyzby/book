package com.upc.book.rule;

/**
 * Created by florian on 16/4/19.
 */
public class Rule {
    Item left;
    Item right;
    double confidence;

    public Rule() {
    }

    public Rule(Item left, Item right, double confidence) {
        this.left = left;
        this.right = right;
        this.confidence = confidence;
    }

    public double getConfidence() {
        return confidence;
    }

    public String prettyFormat() {
        return left.prettyFormat() + " => " + right.prettyFormat() + " : " + (int)(confidence * 100) + "%";
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
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
