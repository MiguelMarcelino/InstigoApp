package io.App.UserCatalogService;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventListWrapper {

    @JsonProperty("listEvents")
    private List<Event> list;


    public EventListWrapper() {
        //For REST Only
    }

    public List<Event> getList() {
        return list;
    }

    public void setList(List<Event> someList) {
        this.list = someList;
    }
}
