package io.App.EventService.facade.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EventListWrapper {

    @JsonProperty("listEvents")
    private List<EventDTO> list;


    public EventListWrapper() {
        //For REST Only
    }

    public EventListWrapper(List<EventDTO> list) {
		this.list = list;
	}

	public List<EventDTO> getList() {
        return list;
    }

    public void setList(List<EventDTO> someList) {
        this.list = someList;
    }
}
