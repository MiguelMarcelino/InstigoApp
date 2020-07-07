package io.App.EventService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.EventService.EventComponent.Event;

public class EventListWrapper {

	@JsonProperty("listEvents")
	private List<Event> list;

	public EventListWrapper() {
		// For REST only
	}

	public List<Event> getList() {
		return list;
	}

	public void setList(List<Event> someList) {
		this.list = someList;
	}
}
