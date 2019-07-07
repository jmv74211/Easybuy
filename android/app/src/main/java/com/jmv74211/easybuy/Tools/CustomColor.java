package com.jmv74211.easybuy.Tools;

import java.util.ArrayList;
import java.util.Random;

public class CustomColor {

    private static CustomColor instance;

    private ArrayList<String> colors;


    private CustomColor() {

        this.colors = new ArrayList<>();

        String c1 = "#64b5f6";
        String c2 = "#ef5350" ;
        String c3 = "#ab47bc";
        String c4 = "#5c6bc0" ;
        String c5 = "#4dd0e1";
        String c6 = "#81c784" ;
        String c7 = "#dce775";
        String c8 = "#ffb74d";
        String c9 = "#a1887f";
        String c10 = "#fff176";
    }

    public static CustomColor getInstance() {

        if( instance == null){
            instance = new CustomColor();
        }

        return instance;
    }

    public String getColor(int position){

        String color = "#000000";

        if(position  < colors.size()){
            color = this.colors.get(position);
        }

        return color;
    }

    public String getRandomColor(){

        Random aleatorio = new Random(System.currentTimeMillis());
        int random = aleatorio.nextInt(colors.size());

        return colors.get(random);
    }

}
