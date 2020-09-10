package io.App.EventService.business;

public class Event {

	private int id;
	private String name;
	private String start;
	private String end;
	private int eventOwnerId;
	private int communityId;

	public Event(int id, String name, String start, String end, int communityId,
			int eventOwnerId) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.communityId = communityId;
		this.eventOwnerId = eventOwnerId;
	}

	public Event(String name, String start, String end, int communityId,
			int eventOwnerId) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.communityId = communityId;
		this.eventOwnerId = eventOwnerId;
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

	public int getCommunityId() {
		return communityId;
	}

	public int getEventOwnerId() {
		return eventOwnerId;
	}

}