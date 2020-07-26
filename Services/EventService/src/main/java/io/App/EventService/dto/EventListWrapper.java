package io.App.EventService.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EventListWrapper {

    @JsonProperty("listEvents")
    private ArrayList<EventDTO> list;


    public EventListWrapper() {
        //For REST Only
    }

    public ArrayList<EventDTO> getList() {
        return list;
    }

    public void setList(ArrayList<EventDTO> someList) {
        this.list = someList;
    }
}
