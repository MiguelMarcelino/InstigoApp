package io.App.EventService.facade.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventDTO extends ClientRequestWrapper implements Serializable {

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
	private int communityId;

	@JsonProperty("eventOwner")
	private int eventOwnerId;

	public EventDTO() {
		// For REST only
	}

	public EventDTO(int i, String name, String start, String end,
			int communityId, int eventOwnerId) {
		this.id = i;
		this.name = name;
		this.start = start;
		this.end = end;
		this.eventOwnerId = eventOwnerId;
		this.communityId = communityId;
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

	public int getEventOwnerId() {
		return this.eventOwnerId;
	}

	public int getCommunity() {
		return communityId;
	}

}
