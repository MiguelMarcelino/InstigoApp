package com.di.fcullapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EventListWrapper {

    @JsonProperty("listEvents")
    private List<Event> list;


    public EventListWrapper() {
        // TODO Auto-generated constructor stub
    }

    public List<Event> getList() {
        return list;
    }

    public void setList(List<Event> someList) {
        this.list = someList;
    }
}
