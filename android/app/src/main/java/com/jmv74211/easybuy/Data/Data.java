package com.jmv74211.easybuy.Data;

import com.jmv74211.easybuy.POJO.ShoppingList;
import com.jmv74211.easybuy.POJO.User;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    private static Data instance;

    private ArrayList<User> users;
    private ArrayList<String> products;
    private ArrayList<ShoppingList> shoppingList;

    private Data(){

        User user1 = new User("jmv74211","jmv74211@gmail.com","Jonathan","Martín","Valera");
        User user2 = new User("npc93","nerea.perez.cobos@hotmail.com","Nerea","Pérez","Cobos");
        User user3 = new User("cristf93","cristian@gmail.com","Cristian","Fajardo","Romero");
        User user4 = new User("lucia96","lucia@gmail.com","Lucía","Villar","Ruiz");
        User user5 = new User("jose94","jose@gmail.com","Jose","Herrera","Rodríguez");

        users = new ArrayList<User>(Arrays.asList (user1,user2,user3,user4,user5));

        String product1 = "Pan";
        String product2 = "Leche";
        String product3 = "Huevos";
        String product4 = "Mantequilla";
        String product5 = "Carne";
        String product6 = "Pescado";
        String product7 = "Agua";
        String product8 = "Manzana";
        String product9 = "Pizza";
        String product10 = "Lechuga";

        products = new ArrayList<String>(Arrays.asList (product1,product2,product3,product4,product5,
                product6,product7,product8,product9,product10));

        shoppingList = new ArrayList<>();


        ArrayList<String> u = new ArrayList<>(Arrays.asList("jmv74211","nerea"));
        ArrayList<String> p = new ArrayList<>(Arrays.asList(product1,product4,product7));



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

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
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
