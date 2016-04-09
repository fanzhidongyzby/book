package com.upc.book.pojo;

import com.upc.book.entity.Book;

import java.io.Serializable;
import java.util.List;

public class OrderDTO implements Serializable {

    String id;

    List<Book> books;

    String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", books=" + books +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
