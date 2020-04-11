package com.jmv74211.easybuy.data;

import android.content.Context;

import com.jmv74211.easybuy.tools.Theme;

import java.util.ArrayList;
import java.util.Arrays;

public class ThemeData {

  private static ThemeData instance;

  private Context context;
  private ArrayList<Theme> themes;

  // -----------------------------------------------------------------------------------------------

  private ThemeData(Context context) {
    this.context = context;

    this.themes = new ArrayList<Theme>(
      Arrays.asList(
        new Theme("default", "#008577", "#00574B", "#81c784", "#D81B60"),
        new Theme("orange", "#ff9800", "#c66900", "#ffc947", "#546e7a"),
        new Theme("yellow", "#ffeb3b", "#c8b900", "#ffff72", "#424242"),
        new Theme("green", "#8bc34a", "#5a9216", "#bef67a", "#3949ab"),
        new Theme("blue", "#03a9f4", "#007ac1", "#67daff", "#455a64"),
        new Theme("purple", "#7e57c2", "#4d2c91", "#b085f5", "#fdd835"),
        new Theme("pink", "#ec407a", "#b4004e", "#ff77a9", "#7e57c2"),
        new Theme("darkBlue", "#3f51b5", "#002984", "#757de8", "#fdd835"),
        new Theme("black", "#212121", "#000000", "#484848", "#ffeb3b"),
        new Theme("red", "#f44336", "#ba000d", "#ff7961", "#bdbdbd"),
        new Theme("brown", "#795548", "#4b2c20", "#a98274", "#fdd835"),
        new Theme("grey", "#78909c", "#4b636e", "#a7c0cd", "#ffb74d")
      )
    );
  }

  // -----------------------------------------------------------------------------------------------

  public static ThemeData getInstance(Context context) {
    if (instance == null)
      instance = new ThemeData(context);

    return instance;
  }

  // -----------------------------------------------------------------------------------------------

  public ArrayList<Theme> getThemes() {
    return themes;
  }

  // -----------------------------------------------------------------------------------------------

  public Theme getTheme(String name) {
    for (Theme theme : themes) {
      if (theme.getName().equals(name)) {
        return theme;
      }
    }

    return new Theme();
  }

  // -----------------------------------------------------------------------------------------------

  public String getThemeName(int position) {
    if (position < 0 || position >= themes.size()) {
      return "default";
    }

    return themes.get(position).getName();
  }

  // -----------------------------------------------------------------------------------------------

}
