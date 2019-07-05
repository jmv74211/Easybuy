package com.jmv74211.easybuy.DBInfo;

/*
 *  Author: @jmv74211
 *  Date: 06/2019
 *  ShoppingList info index map information
 * */

public class ShoppingListDBInfo {

    private static ShoppingListDBInfo instance;

    private final String COLLECTION_NAME="shoppingList";
    private final String KEY_ID = "id";
    private final String KEY_NAME = "listName";
    private final String KEY_USER_CREATOR = "userCreator";
    private final String KEY_DATE= "date";
    private final String KEY_TIME = "time";
    private final String KEY_PARTICIPANTS= "participants";
    private final String KEY_CART_PRODUCTS = "cartProducts";
    private final String KEY_PRICE = "price";


    ////////////////////////////////////////////////////////////////////////////////////////////////

    private ShoppingListDBInfo(){

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static ShoppingListDBInfo getInstance(){

        if(instance == null)
            instance = new ShoppingListDBInfo();

        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    public String getKEY_ID() {
        return KEY_ID;
    }

    public String getKEY_NAME() {
        return KEY_NAME;
    }

    public String getKEY_USER_CREATOR() {
        return KEY_USER_CREATOR;
    }

    public String getKEY_DATE() {
        return KEY_DATE;
    }

    public String getKEY_TIME() {
        return KEY_TIME;
    }

    public String getKEY_PARTICIPANTS() {
        return KEY_PARTICIPANTS;
    }

    public String getKEY_CART_PRODUCTS() {
        return KEY_CART_PRODUCTS;
    }

    public String getKEY_PRICE() {
        return KEY_PRICE;
    }
}
