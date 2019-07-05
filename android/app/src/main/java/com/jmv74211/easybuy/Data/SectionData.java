package com.jmv74211.easybuy.Data;

import android.content.Context;

import com.jmv74211.easybuy.R;



public class SectionData {

    private static SectionData instance;
    private Context context;


    ////////////////////////////////////////////////////////////////////////////////////////////////

    private SectionData(Context context) {

        this.context = context;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static SectionData getInstance(Context context) {

        if (instance == null)
            instance = new SectionData(context);

        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getSection(int key) {

        /*
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
