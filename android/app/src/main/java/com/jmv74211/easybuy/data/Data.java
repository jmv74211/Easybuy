package com.jmv74211.easybuy.data;

import android.content.Context;

public class Data {

  private static Data instance;
  private Context context;

  private static ProductData productData;
  private static SectionData sectionData;
  private static SettingsData settingsData;
  private static CartData cartData;

  // -----------------------------------------------------------------------------------------------

  private Data(Context context) {
    this.context = context;
    productData = ProductData.getInstance(context);
    sectionData = SectionData.getInstance(context);
    settingsData = SettingsData.getInstance(context);
    cartData = CartData.getInstance(context);
  }

  // -----------------------------------------------------------------------------------------------

  public static Data getInstance(Context context) {
    if (instance == null) {
      instance = new Data(context);
    }
    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public void reloadData() {
    productData.reloadData(context);
    sectionData.reloadData(context);
    settingsData.reloadData(context);
    cartData.reloadData();
  }

  // -----------------------------------------------------------------------------------------------

}
