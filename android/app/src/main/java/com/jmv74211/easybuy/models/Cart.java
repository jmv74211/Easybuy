package com.jmv74211.easybuy.models;

import android.util.Pair;

import com.jmv74211.easybuy.tools.Tools;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private List<Pair<Product, Integer>> products;

  // ---------------------------------------------------------------------------------------------

  public Cart() {
    this.products = new ArrayList<>();
  }

  // ---------------------------------------------------------------------------------------------

  public Cart(List<Pair<Product, Integer> >products) {
    this.products = products;
  }

  // ---------------------------------------------------------------------------------------------

  public List<Pair<Product, Integer>> getProducts() {
    return products;
  }

  // ---------------------------------------------------------------------------------------------

  public float getCartPrice() {
    float cart_price = 0f;

    for(Pair<Product, Integer> item: this.products){
      cart_price += item.first.getPrice() * item.second;
    }

    return (float) Tools.round(cart_price, 2);
  }

  // ---------------------------------------------------------------------------------------------

  public void setProducts(List<Pair<Product, Integer>> products) {
    this.products = products;
  }

  // ---------------------------------------------------------------------------------------------

  @Override
  public String toString() {
    return "Cart{" +
            "products=" + products +
            '}';
  }

  // ---------------------------------------------------------------------------------------------
}
