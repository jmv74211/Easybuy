package com.jmv74211.easybuy.Data;


import com.jmv74211.easybuy.POJO.CartProduct;
import com.jmv74211.easybuy.POJO.ShoppingList;

public class Data {


    private static Data instance;

    private static ShoppingList shoppingList;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Data() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    public static Data getInstance() {
        if (instance == null)
            instance = new Data();

        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static ShoppingList getShoppingList() {
        return shoppingList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setShoppingList(ShoppingList sh) {
        Data.shoppingList = sh;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean checkIfCartContainsProduct(int productId) {
        boolean exist = false;

        for(CartProduct item: shoppingList.getCartProducts()){
            if(productId == item.getProduct().getId()){
                exist = true;
            }
        }

        return exist;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
