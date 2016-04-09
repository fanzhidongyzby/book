package com.upc.book.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
public class Order implements Serializable {

    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    String id;

    @Column(nullable = false)
    String username;

    String cart;

    @DateTimeFormat
    String timestamp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userName='" + username + '\'' +
                ", cart='" + cart + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
