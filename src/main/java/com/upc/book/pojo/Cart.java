package com.upc.book.pojo;

import com.upc.book.entity.Book;
import com.upc.book.exception.BookException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private static String BOOK_SEP = ";";
    private static String COUNT_SEP = ":";

    public static Map<String, Integer> getBookCountMap(String value) {
        Map<String, Integer> map = new HashMap<>();
        if (value == null) {
            return map;
        }

        //获取书-数量对
        String[] pairs = value.split(BOOK_SEP);
        for (String pair : pairs) {
            //获取书、数量
            String[] kv = pair.split(COUNT_SEP);
            if (kv.length != 2) {
                continue;
            }
            String id = kv[0];
            int count = Integer.parseInt(kv[1]);
            map.put(id, count);
        }

        return map;
    }
// 把购物车对象转换为字符串：1:10;2:9;
    public static String toInnerString(Cart cart) {
        StringBuffer stringBuffer = new StringBuffer();
        if (cart == null) {
            return stringBuffer.toString();
        }

        for (BookCount bookCount : cart.bookCounts) {
            Book book = bookCount.getBook();
            int count = bookCount.getCount();
            if (book == null || count < 1) {
                return stringBuffer.toString();
            }

            //append字符串加法运算
            stringBuffer.append(book.getId() + COUNT_SEP + count + BOOK_SEP);
        }

        return stringBuffer.toString();
    }

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
