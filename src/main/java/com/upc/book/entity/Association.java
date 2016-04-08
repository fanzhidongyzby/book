package com.upc.book.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "association")
public class Association implements Serializable {

  @Id
  @Column(length = 36)
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @GeneratedValue(generator = "system-uuid")
  String id;

  @Column(nullable = false)
  String x;

  @Column(nullable = false)
  String y;


  double percent;

  public void setKey(String id) {
    this.id = id;
  }

  public void setX(String x) {
    this.x = x;
  }

  public void setY(String y) {
    this.y = y;
  }

  public void setPercent(double percent) {
    this.percent = percent;
  }

  public String getId() {

    return id;
  }

  public String getX() {
    return x;
  }

  public String getY() {
    return y;
  }

  public double getPercent() {
    return percent;
  }


  @Override
  public String toString() {
    return "Association{" +
      "id='" + id + '\'' +
      ", x='" + x + '\'' +
      ", y='" + y + '\'' +
      ", percent='" + percent + '\'' +
      '}';
  }
}
