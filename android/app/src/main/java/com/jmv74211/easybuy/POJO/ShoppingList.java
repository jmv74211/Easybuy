package com.jmv74211.easybuy.POJO;

import com.google.firebase.firestore.Exclude;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShoppingList implements Comparable<ShoppingList>, java.io.Serializable {

    private String id;
    private String name;
    private String creatorUser;
    private String date;
    private String time;
    private ArrayList<String> participants;
    private ArrayList<CartProduct> cartProducts;
    private float price;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ShoppingList(){

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ShoppingList(String id, String name, String creatorUser, String date, String time, ArrayList<String> participants, ArrayList<CartProduct> cartProducts) {
        this.id = id;
        this.name = name;
        this.creatorUser = creatorUser;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.cartProducts = cartProducts;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ShoppingList(String name, String creatorUser, String date, String time, ArrayList<String> participants, ArrayList<CartProduct> cartProducts) {
        this.name = name;
        this.creatorUser = creatorUser;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.cartProducts = cartProducts;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Exclude
    public String getId() {
        return id;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setId(String id) {
        this.id = id;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setName(String name) {
        this.name = name;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getCreatorUser() {
        return creatorUser;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getDate() {
        return date;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setDate(String date) {
        this.date = date;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getTime() {
        return time;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setTime(String time) {
        this.time = time;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<String> getParticipants() {
        return participants;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public int getNumParticipants() {
        return participants.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<CartProduct> getCartProducts() {
        return cartProducts;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public int getNumProducts() {

        int count = 0;

        for(CartProduct cp: cartProducts){
            count += cp.getQuantity();
        }

        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void addCartProduct(CartProduct cartProduct) {
        this.cartProducts.add(cartProduct);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setCartProducts(ArrayList<CartProduct> cartProductProducts) {
        this.cartProducts = cartProductProducts;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public float getPrice() {
        return price;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public float calculatePrice(){

        float cost = 0f;

        for(CartProduct c: cartProducts){
            cost += c.getProduct().getPrice() * c.getQuantity();
        }

        return cost;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setPrice(float price) {
        this.price = price;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatorUser='" + creatorUser + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", participants=" + participants +
                ", cartProducts=" + cartProducts +
                ", price=" + price +
                '}';
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    //Positive value indicates this object is greater, negative less, 0 equal.
    public int compareTo(ShoppingList sh) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        java.util.Date d1 = null;

        try {
            d1 = format.parse(this.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.util.Date d2 = null;
        try {
            d2 = format.parse(sh.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (d1.compareTo(d2) < 0) {
            return -1; // less
        }
        else if(d1.compareTo(d2) > 0){
            return 1; // greater
        }
        // If same date, check time
        else{

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");


            java.util.Date t1 = null;
            try {
                t1 = sdf.parse(this.time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.util.Date t2 = null;
            try {
                t2 = sdf.parse(sh.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long elapsed = t1.getTime() - t2.getTime();

            if(elapsed > 0){
                return 1; // greater
            }
            else{
                return -1; // less
            }

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
