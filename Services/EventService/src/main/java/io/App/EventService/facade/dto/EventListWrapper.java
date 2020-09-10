package io.App.EventService.facade.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EventListWrapper {

    @JsonProperty("listEvents")
    private List<EventDTO> eventList;


    public EventListWrapper() {
        //For REST Only
    }

    public EventListWrapper(List<EventDTO> list) {
		this.eventList = list;
	}

	public List<EventDTO> getList() {
        return eventList;
    }

    public void setList(List<EventDTO> someList) {
        this.eventList = someList;
    }
}
