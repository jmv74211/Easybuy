package com.jmv74211.easybuy.models;

import com.jmv74211.easybuy.tools.Tools;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProductSelection implements Serializable {

  private Product product;
  private int quantity;

  // -----------------------------------------------------------------------------------------------

  public ProductSelection(){
    this.product = new Product();
    this.quantity = 0;
  }

  // -----------------------------------------------------------------------------------------------

  public ProductSelection(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  // -----------------------------------------------------------------------------------------------

  public ProductSelection(Map<String, Object> data) {
    this.product = new Product((HashMap<String, Object>) data.get("product"));
    this.quantity = ((Long) data.get("quantity")).intValue();
  }

  // -----------------------------------------------------------------------------------------------

  public Product getProduct() {
    return product;
  }

  // -----------------------------------------------------------------------------------------------

  public void setProduct(Product product) {
    this.product = product;
  }

  // -----------------------------------------------------------------------------------------------

  public int getQuantity() {
    return quantity;
  }

  // -----------------------------------------------------------------------------------------------

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  // -----------------------------------------------------------------------------------------------

  public float getPrice(){ return (float) Tools.round((this.product.getPrice() * quantity), 2); }

  // -----------------------------------------------------------------------------------------------

  @Override
  public String toString() {
    return "ProductSelection{" +
            "product=" + product +
            ", quantity=" + quantity +
            '}';
  }

  // -----------------------------------------------------------------------------------------------

}
