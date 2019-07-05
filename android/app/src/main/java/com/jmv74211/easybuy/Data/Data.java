package com.jmv74211.easybuy.Data;

import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.POJO.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    private static Data instance;

    private ArrayList<User> users;
    private ArrayList<Integer> products;
    private ArrayList<ShoppingList> shoppingList;

    private Data(){

        User user1 = new User("jmv74211","jmv74211@gmail.com","Jonathan","Martín","Valera");
        User user2 = new User("npc93","nerea.perez.cobos@hotmail.com","Nerea","Pérez","Cobos");
        User user3 = new User("cristf93","cristian@gmail.com","Cristian","Fajardo","Romero");
        User user4 = new User("lucia96","lucia@gmail.com","Lucía","Villar","Ruiz");
        User user5 = new User("jose94","jose@gmail.com","Jose","Herrera","Rodríguez");

        users = new ArrayList<User>(Arrays.asList (user1,user2,user3,user4,user5));



        products = new ArrayList<Integer>(Arrays.asList (1,2));

        shoppingList = new ArrayList<>();


        ArrayList<String> u = new ArrayList<>(Arrays.asList("jmv74211","nerea"));
        ArrayList<Integer> p = new ArrayList<>(Arrays.asList(1,2));



        ShoppingList sh = new ShoppingList("Lista de prueba", "jmv74211", "10-10-2010", "15:30", u,p);
        ShoppingList sh2 = new ShoppingList("Lista de prueba2", "jmv74211", "10-10-2010", "16:30", u,p);
        shoppingList.add(sh);
        shoppingList.add(sh2);
    }

    public static Data getInstance() {
        if (instance == null)
            instance = new Data();

        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Integer> products) {
        this.products = products;
    }

    public void addShoppingList(ShoppingList shoppingList) {
        this.shoppingList.add(shoppingList);
    }

    public ArrayList<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ArrayList<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
