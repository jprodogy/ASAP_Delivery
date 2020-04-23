package com.example.asap_delivery;

import java.util.Arrays;
import java.util.List;

public class FoodItems {

    String title, image, description, price, type;
    List<String> keywords;

    //constructor
    public FoodItems(){}

    //getter and setters press Alt+Insert

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setKeywords(String keywords){
        this.keywords = Arrays.asList(keywords.split("/"));
    }

    public void setType(String type){ this.type = type;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice(){ return price;}

    public void setPrice(String price){ this.price = price;}

    public String getType(){return type;}

    public List<String> getKeywords(){return keywords;}

}