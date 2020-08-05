package io.App.EventService.EventComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.EventDatabaseManagement;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.EventDoesNotExistException;
import io.App.EventService.exceptions.InternalAppException;

@SpringBootApplication
public class EventCatalog {

	private EventDatabaseManagement eDC;

	public EventCatalog() {
		eDC = new EventDatabaseManagement();
	}

	public EventListWrapper getAllEvents() {
		return this.eDC.getAllEvents();
	}

	public EventListWrapper getEventsFromCommunity(int cID)
			throws InternalAppException {
		return this.eDC.getEventsFromCommunity(cID);
	}

	public void registerNewEvent(Event event)
			throws EventAlreadyExistsException, InternalAppException {
		this.eDC.registerNewEvent(event);
	}

	public void deleteEvent(Event event) throws EventDoesNotExistException {
		if (this.eDC.getEventByName(event.getName()) == null) {
			throw new EventDoesNotExistException();
		}
		eDC.deleteEvent(event);
	}
	
	

}
