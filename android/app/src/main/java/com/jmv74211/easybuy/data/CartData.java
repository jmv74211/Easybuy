package com.jmv74211.easybuy.data;

import android.content.Context;

import com.jmv74211.easybuy.models.Cart;
import com.jmv74211.easybuy.models.Product;
import com.jmv74211.easybuy.models.ProductSelection;

public class CartData {

  private static CartData instance;
  private Cart cart;
  private Context context;

  // -----------------------------------------------------------------------------------------------

  private CartData(Context context) {
    this.context = context;
    this.cart = new Cart();
  }

  // -----------------------------------------------------------------------------------------------

  public static CartData getInstance(Context context) {

    if (instance == null)
      instance = new CartData(context);

    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public void setCartData(Cart cart){
    if(cart != null){
      this.cart = translateCart(cart);
    }
  }

  // -----------------------------------------------------------------------------------------------

  public Cart getCartData(){
    return this.cart;
  }

  // -----------------------------------------------------------------------------------------------

  private Cart translateCart(Cart cart){
    Cart translatedCart = new Cart();

    for(ProductSelection p : cart.getProducts()){
      Product product = ProductData.getInstance(context).getProduct(p.getProduct().getId());
      translatedCart.addProduct(new ProductSelection(product, p.getQuantity()));
    }

    translatedCart.setId(cart.getId());

    return translatedCart;
  }

  // -----------------------------------------------------------------------------------------------

  public void reloadData(){
    setCartData(cart);
  }

  // -----------------------------------------------------------------------------------------------

}
