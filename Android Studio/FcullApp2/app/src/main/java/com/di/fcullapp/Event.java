package com.di.fcullapp;

public class Event {

    private int id;
    private String name;
    private String start;
    private String end;
    private int cID;
    private String cName;

    public Event(int id, String name, String start, String end, int cID, String cName) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.cID = cID;
        this.cName = cName;
    }

    public Event(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getcID() {
        return cID;
    }

    public String getcName() {
        return cName;
    }

}

