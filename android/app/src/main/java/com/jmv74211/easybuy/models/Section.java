package com.jmv74211.easybuy.models;

import java.io.Serializable;

public class Section implements Serializable {

  private int id;
  private String name;
  private String color;

  // ---------------------------------------------------------------------------------------------

  public Section() {

  }

  // ---------------------------------------------------------------------------------------------

  public Section(int id, String name) {
    this.id = id;
    this.name = name;
    this.color = "#000000";
  }

  // ---------------------------------------------------------------------------------------------

  public Section(int id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  // ---------------------------------------------------------------------------------------------

  public int getId() {
    return id;
  }

  // ---------------------------------------------------------------------------------------------

  public void setId(int id) {
    this.id = id;
  }

  // ---------------------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  // ---------------------------------------------------------------------------------------------

  public void setName(String name) {
    this.name = name;
  }

  // ---------------------------------------------------------------------------------------------

  public String getColor() {
    return color;
  }

  // ---------------------------------------------------------------------------------------------

  public void setColor(String color) {
    this.color = color;
  }

  // ---------------------------------------------------------------------------------------------

  @Override
  public String toString() {
    return "Section{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", color='" + color + '\'' +
            '}';
  }

  // ---------------------------------------------------------------------------------------------

}
