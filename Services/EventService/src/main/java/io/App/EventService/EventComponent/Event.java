package io.App.EventService.EventComponent;

public class Event {

	private int id;
	private String name;
	private String start;
	private String end;
	private User eventOwner;
	private Community community;

	public Event(int id, String name, String start, String end,
			Community community, User eventOwner) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.community = community;
		this.eventOwner = eventOwner;
	}

	public Event(String name, String start, String end,
			Community community, User eventOwner) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.community = community;
		this.eventOwner = eventOwner;
	}

	public Event() {
		// For REST Only
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

	public Community getCommunity() {
		return community;
	}

	public User getEventOwner() {
		return eventOwner;
	}

}