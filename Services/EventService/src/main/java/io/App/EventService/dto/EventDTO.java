package io.App.EventService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("start")
	private String start;

	@JsonProperty("end")
	private String end;

	@JsonProperty("cID")
	private String cID;

	@JsonProperty("cName")
	private String cName;

	public EventDTO() {
		// For REST only
	}

	public EventDTO(String id, String name, String start, String end,
			String cID, String cName) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.cID = cID;
		this.cName = cName;
	}

	public String getId() {
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

	public String getcID() {
		return cID;
	}

	public String getcName() {
		return cName;
	}

}
