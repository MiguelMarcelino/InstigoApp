package com.di.fcullapp;

public class Community {

    private int id;
    private String name;

    public Community(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Community(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
