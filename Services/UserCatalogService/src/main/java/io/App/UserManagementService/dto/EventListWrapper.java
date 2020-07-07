package io.App.UserManagementService.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.UserManagementService.userComponent.Event;

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
