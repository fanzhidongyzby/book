package com.upc.book.pojo;

import com.upc.book.entity.Book;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<BookCount> bookCounts = new ArrayList<>();

    public boolean addBook(Book book, int count) {
        if (book == null || count < 1) {
            return false;
        }
        BookCount bookCount = new BookCount();
        bookCount.setBook(book);
        bookCount.setCount(count);
        int index = bookCounts.indexOf(bookCount);
        if (index == -1) {
            bookCounts.add(bookCount);
        } else {
            bookCount = bookCounts.get(index);
            bookCount.setCount(bookCount.getCount() + count);
        }

        return true;
    }

    public boolean removeBook(Book book, int count) {
        if (book == null || count < 1) {
            return false;
        }

        BookCount bookCount = new BookCount();
        bookCount.setBook(book);
        bookCount.setCount(count);
        int index = bookCounts.indexOf(bookCount);
        if (index == -1) {
            return false;
        }

        bookCount = bookCounts.get(index);
        if (bookCount.getCount() < count) {
            return false;
        } else if (bookCount.getCount() == count) {
            bookCounts.remove(index);
        } else {
            bookCount.setCount(bookCount.getCount() - count);
        }

        return true;
    }

    public List<BookCount> getBookCounts() {
        return bookCounts;
    }

    public void setBookCounts(List<BookCount> bookCounts) {
        this.bookCounts = bookCounts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "bookCounts=" + bookCounts +
                '}';
    }
}
