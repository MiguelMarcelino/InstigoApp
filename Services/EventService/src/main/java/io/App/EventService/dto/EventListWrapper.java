package io.App.EventService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.EventService.EventComponent.Event;

import java.util.ArrayList;

public class EventListWrapper {

    @JsonProperty("listEvents")
    private ArrayList<Event> list;


    public EventListWrapper() {
        // For REST only
    }

    public ArrayList<Event> getList() {
        return list;
    }

    public void setList(ArrayList<Event> someList) {
        this.list = someList;
    }
}
