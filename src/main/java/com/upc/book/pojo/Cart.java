package com.upc.book.pojo;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<BookCount> bookCounts = new ArrayList<>();

    double total;

    public List<BookCount> getBookCounts() {
        return bookCounts;
    }

    public void setBookCounts(List<BookCount> bookCounts) {
        this.bookCounts = bookCounts;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "bookCounts=" + bookCounts +
                ", total=" + total +
                '}';
    }
}
