package io.App.EventService.EventComponent;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.EventDatabaseConnection;
import io.App.EventService.dto.EventListWrapper;

@SpringBootApplication
public class EventCatalog {

	private EventDatabaseConnection eDC;
	private HashMap<Integer, Event> events;

	public EventCatalog() {
		events = new HashMap<Integer, Event>();
		eDC = new EventDatabaseConnection();
		ArrayList<Event> eventList = eDC.getAllEvents().getList();
		for (int i = 0; i < eventList.size(); i++) {
			events.put(eventList.get(i).getId(), eventList.get(i));
		}
	}

	public EventListWrapper getAllEvents() {
		EventListWrapper eLW = new EventListWrapper();
		ArrayList<Event> eventList = new ArrayList<Event>(events.values());
		eLW.setList(eventList);
		return eLW;
	}

	public EventListWrapper getEventsFromCommunity(int cID) {
		EventListWrapper eLW = new EventListWrapper();
		ArrayList<Event> eventsFromCommunity = new ArrayList<Event>();
		ArrayList<Event> allEventList = new ArrayList<Event>(events.values());
		for (int i = 0; i < allEventList.size(); i++) {
			if (allEventList.get(i).getcID() == cID) {
				eventsFromCommunity.add(allEventList.get(i));
			}
		}
		eLW.setList(eventsFromCommunity);
		return eLW;
	}

	public void registerNewEvent(Event event) {
		if (!events.containsKey(event.getId())) {
			events.put(event.getId(), event);
			eDC.registerNewEvent(event); 				// VER ISTO!
		}
	}

	public void deleteEvent(Event event) {
		if (events.containsKey(event.getId())) {
			events.remove(event.getId());
			eDC.deleteEvent(event); 					// VER ISTO!
		}
	}

}
