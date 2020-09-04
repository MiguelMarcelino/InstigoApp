package io.App.EventService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.EventService.EventComponent.Community;
import io.App.EventService.EventComponent.User;

public class EventDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("start")
	private String start;

	@JsonProperty("end")
	private String end;
	
	@JsonProperty("community")
	private Community community;

	@JsonProperty("eventOwner")
	private User eventOwner;
	
	@JsonProperty("currentUser")
	private User currentUser;

	public EventDTO() {
		// For REST only
	}

	public EventDTO(int i, String name, String start, String end,
			Community community, User eventOwner) {
		this.id = i;
		this.name = name;
		this.start = start;
		this.end = end;
		this.eventOwner = eventOwner;
		this.community = community;
	}

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

	public User getEventOwner() {
		return this.eventOwner;
	}

	public Community getCommunity() {
		return community;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

}
