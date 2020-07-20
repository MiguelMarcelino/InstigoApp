package io.App.EventService.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.EventService.EventComponent.Event;


public class EventListWrapper {

    @JsonProperty("listEvents")
    private ArrayList<Event> list;


    public EventListWrapper() {
        //For REST Only
    }

    public ArrayList<Event> getList() {
        return list;
    }

    public void setList(ArrayList<Event> someList) {
        this.list = someList;
    }
}
