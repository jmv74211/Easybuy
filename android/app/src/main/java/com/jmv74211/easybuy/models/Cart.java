package com.jmv74211.easybuy.models;

import com.google.firebase.firestore.Exclude;
import com.jmv74211.easybuy.tools.Tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Cart implements Serializable {

  private String id;
  private List<ProductSelection> products = new ArrayList<ProductSelection>();

  // -----------------------------------------------------------------------------------------------

  public Cart() {}

  // -----------------------------------------------------------------------------------------------

  public Cart(List<ProductSelection> products) {
    this.products = products;
  }

  // -----------------------------------------------------------------------------------------------

  public Cart(HashMap<String, Object> data) {
    for(Object item: (ArrayList<Object>) data.get("products")){
      this.addProduct(new ProductSelection((HashMap<String, Object>) item));
    }
  }

  // -----------------------------------------------------------------------------------------------

  @Exclude
  public String getId() {
    return id;
  }

  // -----------------------------------------------------------------------------------------------

  public void setId(String id) {
    this.id = id;
  }

  // -----------------------------------------------------------------------------------------------

  public List<ProductSelection> getProducts() {
    return products;
  }

  // -----------------------------------------------------------------------------------------------

  public void setProducts(List<ProductSelection> products) {
    this.products = products;
  }

  // -----------------------------------------------------------------------------------------------

  public float getCartPrice() {

    float totalPrice = 0f;

    for (ProductSelection p : products) {
      totalPrice += p.calculatePrice();
    }

    return (float) Tools.round(totalPrice,2);
  }

  // -----------------------------------------------------------------------------------------------

  public void addProduct(ProductSelection p){
    if(p != null){
      this.products.add(p);
    }
  }

  // -----------------------------------------------------------------------------------------------

  public boolean containsProduct(int productId) {
    boolean exist = false;

    for(ProductSelection item: products){
      if(productId == item.getProduct().getId()){
        exist = true;
      }
    }

    return exist;
  }

  // -----------------------------------------------------------------------------------------------

  public int getProductPosition(int productId) {

    int position = 0;

    for(ProductSelection p: this.products){
      if(p.getProduct().getId() == productId)
        return position;
      else
        position++;
    }

    return -1;
  }

  // -----------------------------------------------------------------------------------------------

  @Override
  public String toString() {
    return "Cart{" +
            "products=" + products +
            '}';
  }

  // -----------------------------------------------------------------------------------------------

  public HashMap<String,Object> toMap(){

    HashMap<String,Object> data = new HashMap<String,Object>();
    data.put("products", this.products);

    return data;
  }

  // -----------------------------------------------------------------------------------------------

}
