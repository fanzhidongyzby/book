package com.upc.book.pojo;

import com.upc.book.entity.Book;

public class BookCount {

    Book book;

    int count;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BookCount{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }
}
