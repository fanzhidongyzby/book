package com.upc.book.pojo;

import com.upc.book.entity.Book;

public class BookCount {

    Book book = new Book();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCount bookCount = (BookCount) o;

        return book.equals(bookCount.book);

    }

    @Override
    public int hashCode() {
        return book.hashCode();
    }
}
