package com.upc.book.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

  @Id
  @Column(length = 36)
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @GeneratedValue(generator = "system-uuid")
  String id;

  @Column(unique = true, nullable = false)
  String number;

  @Column(nullable = false)
  String name;

  String author;

  double price;

  String description;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Book{" +
      "id='" + id + '\'' +
      ", number='" + number + '\'' +
      ", name='" + name + '\'' +
      ", author='" + author + '\'' +
      ", price=" + price +
      ", description='" + description + '\'' +
      '}';
  }
}
