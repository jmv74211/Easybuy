package com.jmv74211.easybuy.tools;

public class Theme {

  private String name;
  private String colorPrimary;
  private String colorPrimaryDark;
  private String colorPrimaryLight;
  private String colorAccent;

  // -----------------------------------------------------------------------------------------------

  public Theme(){}

  // -----------------------------------------------------------------------------------------------

  public Theme(String name, String colorPrimary, String colorPrimaryDark, String colorPrimaryLight, String colorAccent) {
    this.name = name;
    this.colorPrimary = colorPrimary;
    this.colorPrimaryDark = colorPrimaryDark;
    this.colorPrimaryLight = colorPrimaryLight;
    this.colorAccent = colorAccent;
  }

  // -----------------------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  // -----------------------------------------------------------------------------------------------

  public void setName(String name) {
    this.name = name;
  }

  // -----------------------------------------------------------------------------------------------

  public String getColorPrimary() {
    return colorPrimary;
  }

  // -----------------------------------------------------------------------------------------------

  public void setColorPrimary(String colorPrimary) {
    this.colorPrimary = colorPrimary;
  }

  // -----------------------------------------------------------------------------------------------

  public String getColorPrimaryDark() {
    return colorPrimaryDark;
  }

  // -----------------------------------------------------------------------------------------------

  public void setColorPrimaryDark(String colorPrimaryDark) {
    this.colorPrimaryDark = colorPrimaryDark;
  }

  // -----------------------------------------------------------------------------------------------

  public String getColorAccent() {
    return colorAccent;
  }

  // -----------------------------------------------------------------------------------------------

  public void setColorAccent(String colorAccent) {
    this.colorAccent = colorAccent;
  }

  // -----------------------------------------------------------------------------------------------

  public String getColorPrimaryLight() {
    return colorPrimaryLight;
  }

  // -----------------------------------------------------------------------------------------------

  public void setColorPrimaryLight(String colorPrimaryLight) {
    this.colorPrimaryLight = colorPrimaryLight;
  }

  // -----------------------------------------------------------------------------------------------

}
