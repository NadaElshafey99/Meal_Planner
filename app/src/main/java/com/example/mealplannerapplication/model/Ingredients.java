package com.example.mealplannerapplication.model;

public class Ingredients {
   private String idIngredient;
   private String strDescription;
   private String strIngredient;
   private String strType;

    public Ingredients(){
    }

    public Ingredients(String idIngredient, String strDescription, String strIngredient, String strType) {
        this.idIngredient = idIngredient;
        this.strDescription = strDescription;
        this.strIngredient = strIngredient;
        this.strType = strType;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }
}
