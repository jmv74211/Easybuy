package com.jmv74211.easybuy.Data;

import android.content.Context;

import com.jmv74211.easybuy.POJO.Section;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.Arrays;


public class SectionData {

    private static SectionData instance;
    private Context context;
    private ArrayList<Section> sectionList;


    ////////////////////////////////////////////////////////////////////////////////////////////////

    private SectionData(Context context) {

        this.context = context;
        String color = "#000000";

        Section s0 = new Section(0, context.getResources().getString(R.string.allProducts),color);
        Section s1 = new Section(1,context.getResources().getString(R.string.dairy),color);
        Section s2 = new Section(2,context.getResources().getString(R.string.bakery),color);
        Section s3 = new Section(3,context.getResources().getString(R.string.sweetsAndAssortments),color);
        Section s4 = new Section(4,context.getResources().getString(R.string.dressingsAndSauces),color);
        Section s5 = new Section(5,context.getResources().getString(R.string.pulsesAndPasta),color);
        Section s6 = new Section(6,context.getResources().getString(R.string.meats),color);
        Section s7 = new Section(7,context.getResources().getString(R.string.sausages),color);
        Section s8 = new Section(8,context.getResources().getString(R.string.fruitAndVegetables),color);
        Section s9 = new Section(9,context.getResources().getString(R.string.frozen),color);
        Section s10 = new Section(10,context.getResources().getString(R.string.fishes),color);
        Section s11 = new Section(11,context.getResources().getString(R.string.drinks),color);
        Section s12 = new Section(12,context.getResources().getString(R.string.cleanlinessAndHygiene),color);
        Section s13= new Section(13,context.getResources().getString(R.string.drugstore),color);

        this.sectionList = new ArrayList<Section>(Arrays.asList(s0,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static SectionData getInstance(Context context) {

        if (instance == null)
            instance = new SectionData(context);

        return instance;
    }


    public ArrayList<Section> getSectionData(){
        return this.sectionList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getSection(int key) {

        /*
         *  Todos los productos 0
         *  Lácteos 1
         *  Panadería 2
         *  Dulces y surtidos 3
         *  Aliños Salsas 4
         *  Legumbres y pasta 5
         *  Carnicería 6
         *  Embutidos 7
         *  Fruta y verdura 8
         *  Congelados 9
         *  Pescadería 10
         *  Bebida 11
         *  Limpieza e higiene 12
         *  Droguería 13
         * */

        String section = "";

        switch (key) {

            case 0:
                section = context.getResources().getString(R.string.allProducts);
                break;

            case 1:
                section = context.getResources().getString(R.string.dairy);
                break;
            case 2:
                section = context.getResources().getString(R.string.bakery);
                break;
            case 3:
                section = context.getResources().getString(R.string.sweetsAndAssortments);
                break;
            case 4:
                section = context.getResources().getString(R.string.dressingsAndSauces);
                break;
            case 5:
                section = context.getResources().getString(R.string.pulsesAndPasta);
                break;
            case 6:
                section = context.getResources().getString(R.string.meats);
                break;
            case 7:
                section = context.getResources().getString(R.string.sausages);
                break;
            case 8:
                section = context.getResources().getString(R.string.fruitAndVegetables);
                break;
            case 9:
                section = context.getResources().getString(R.string.frozen);
                break;
            case 10:
                section = context.getResources().getString(R.string.fishes);
                break;
            case 11:
                section = context.getResources().getString(R.string.drinks);
                break;
            case 12:
                section = context.getResources().getString(R.string.cleanlinessAndHygiene);
                break;
            case 13:
                section = context.getResources().getString(R.string.drugstore);
                break;

        }

        return section;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
