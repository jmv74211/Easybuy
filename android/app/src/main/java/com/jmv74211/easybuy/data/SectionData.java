package com.jmv74211.easybuy.data;

import android.content.Context;

import com.jmv74211.easybuy.models.Section;
import com.jmv74211.easybuy.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SectionData {

  private static SectionData instance;
  private Context context;
  private ArrayList<Section> sectionList;

  // -----------------------------------------------------------------------------------------------

  private SectionData(Context context) {
    this.context = context;
    String color = "#000000";

    this.sectionList = new ArrayList<Section>(
      Arrays.asList(
        new Section(0, context.getResources().getString(R.string.allProducts),color),
        new Section(1,context.getResources().getString(R.string.dairy),color),
        new Section(2,context.getResources().getString(R.string.bakery),color),
        new Section(3,context.getResources().getString(R.string.sweetsAndAssortments),color),
        new Section(4,context.getResources().getString(R.string.dressingsAndSauces),color),
        new Section(5,context.getResources().getString(R.string.pulsesAndPasta),color),
        new Section(6,context.getResources().getString(R.string.meats),color),
        new Section(7,context.getResources().getString(R.string.sausages),color),
        new Section(8,context.getResources().getString(R.string.fruitAndVegetables),color),
        new Section(9,context.getResources().getString(R.string.frozen),color),
        new Section(10,context.getResources().getString(R.string.fishes),color),
        new Section(11,context.getResources().getString(R.string.drinks),color),
        new Section(12,context.getResources().getString(R.string.cleanlinessAndHygiene),color),
        new Section(13,context.getResources().getString(R.string.drugstore),color)
      )
    );
  }

  // -----------------------------------------------------------------------------------------------

  public static SectionData getInstance(Context context) {
    if (instance == null)
      instance = new SectionData(context);

    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public ArrayList<Section> getSectionData(){
    return this.sectionList;
  }

  // -----------------------------------------------------------------------------------------------

  public String getSection(int key) {
    if(key >= sectionList.size()){
      return sectionList.get(0).getName();
    }

    return sectionList.get(key).getName();
  }

  // -----------------------------------------------------------------------------------------------

  public void reloadData(Context context){
    instance = null;
    instance = getInstance(context);
  }

  // -----------------------------------------------------------------------------------------------

}
