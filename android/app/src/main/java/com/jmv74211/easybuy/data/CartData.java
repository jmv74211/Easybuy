package com.jmv74211.easybuy.data;

import com.jmv74211.easybuy.models.Cart;

public class CartData {

  private static CartData instance;
  private Cart cart;

  // -----------------------------------------------------------------------------------------------

  private CartData() {
    this.cart = new Cart();
  }

  // -----------------------------------------------------------------------------------------------

  public static CartData getInstance() {

    if (instance == null)
      instance = new CartData();

    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public void setCartData(Cart cart){
    if(cart != null){
      this.cart = cart;
    }
  }

  // -----------------------------------------------------------------------------------------------

  public Cart getCartData(){
    return this.cart;
  }

  // -----------------------------------------------------------------------------------------------

}
