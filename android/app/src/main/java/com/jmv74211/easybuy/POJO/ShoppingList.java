package com.jmv74211.easybuy.POJO;

import java.util.ArrayList;

public class ShoppingList {

    private int id;
    private String name;
    private String creatorUser;
    private String date;
    private String time;
    private ArrayList<String> participants;
    private ArrayList<String> products;
    private float price;

    public ShoppingList(int id, String name, String creatorUser, String date, String time, ArrayList<String> participants, ArrayList<String> products) {
        this.id = id;
        this.name = name;
        this.creatorUser = creatorUser;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.products = products;
    }

    public ShoppingList(String name, String creatorUser, String date, String time, ArrayList<String> participants, ArrayList<String> products) {
        this.name = name;
        this.creatorUser = creatorUser;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public int getNumParticipants() {
        return participants.size();
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public int getNumProducts() {
        return products.size();
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public float getPrice() {
        return price;
    }

    private void calculatePrice(){
        // Implement this method later
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatorUser='" + creatorUser + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", participants=" + participants +
                ", products=" + products +
                ", price=" + price +
                '}';
    }
}
