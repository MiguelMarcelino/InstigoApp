package io.App.EventService.EventComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.EventDatabaseConnection;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.InternalAppException;

@SpringBootApplication
public class EventCatalog {

	private EventDatabaseConnection eDC;

	public EventCatalog() {
		eDC = new EventDatabaseConnection();
	}

	public List<Event> getAllEvents() throws InternalAppException {
		return this.eDC.getAllEvents();
	}

	public List<Event> getEventsFromCommunity(int cID)
			throws InternalAppException {
		return this.eDC.getEventsFromCommunity(cID);
	}

	public void registerNewEvent(Event event)
			throws EventAlreadyExistsException, InternalAppException {
		this.eDC.createNewEvent(event);
	}

	public void deleteEvent(Event event) throws InternalAppException {
		this.eDC.deleteEvent(event);
	}
	
	public List<Event> eventsCreatedByUser(String userName) throws InternalAppException {
		return eDC.userCreatedEvents(userName);
	}

}
