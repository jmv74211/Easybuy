package com.jmv74211.easybuy.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.jmv74211.easybuy.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class SettingsData {

  public static final String COLLECTION = "settings";
  public static final String LANGUAGE = "language";
  public static final String CURRENCY = "currency";
  public static final String THEME = "theme";

  private static SettingsData instance;

  private SharedPreferences sharedPreferences;
  private Context context;
  private String language;
  private String currency;
  private String theme;

  // -----------------------------------------------------------------------------------------------

  private SettingsData(Context context){
    this.context = context;
    this.sharedPreferences = context.getSharedPreferences(COLLECTION, MODE_PRIVATE);
  }

  // -----------------------------------------------------------------------------------------------

  public static SettingsData getInstance(Context context) {
    if (instance == null) {
      instance = new SettingsData(context);
      instance.loadData();
    }
    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public void loadData() {
    language = sharedPreferences.getString(LANGUAGE, "es");
    currency = sharedPreferences.getString(CURRENCY, "€");
    theme = sharedPreferences.getString(THEME, "default");
    setLanguage(language);
  }

  // -----------------------------------------------------------------------------------------------

  public String getLanguage(){
    return language;
  }

  // -----------------------------------------------------------------------------------------------

  public String getCurrency(){
    return currency;
  }

  // -----------------------------------------------------------------------------------------------

  public String getTheme() {
    return theme;
  }

  // -----------------------------------------------------------------------------------------------

  public void saveData(String language, String currency, String theme) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(LANGUAGE, language);
    editor.putString(CURRENCY, currency);
    editor.putString(THEME, theme);
    editor.apply();

    if(language.compareTo(getLanguage()) != 0){
      setLanguage(language);
      Data.getInstance(context).reloadData();
    }

    StyleableToast.makeText(context, context.getText(R.string.changesSaved).toString(),
            Toast.LENGTH_SHORT,R.style.toastSuccessAddProduct).show();
  }

  // -----------------------------------------------------------------------------------------------

  public void setLanguage(String localeCode) {
    Resources res = context.getResources();
    DisplayMetrics dm = res.getDisplayMetrics();
    Configuration conf = res.getConfiguration();
    conf.setLocale(new Locale(localeCode.toLowerCase()));
    res.updateConfiguration(conf, dm);
  }

  // -----------------------------------------------------------------------------------------------

  public void reloadData(Context context){
    instance = null;
    instance = getInstance(context);
  }

  // -----------------------------------------------------------------------------------------------

}
