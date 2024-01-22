package com.example.model;

import lombok.Data;

@Data
public class Product {

  private String name;
  private double price;
/** lombok сам сдеалет методы для полей*/
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public double getPrice() {
//    return price;
//  }
//
//  public void setPrice(double price) {
//    this.price = price;
//  }
}
