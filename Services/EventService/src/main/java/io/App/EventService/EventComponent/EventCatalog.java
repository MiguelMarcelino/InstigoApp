package io.App.EventService.EventComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.EventDatabaseConnection;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.EventDoesNotExistException;
import io.App.EventService.exceptions.InternalAppException;
import io.App.EventService.exceptions.NoEventsFromCommunityException;

@SpringBootApplication
public class EventCatalog {

	private EventDatabaseConnection eDC;

	public EventCatalog() {
		eDC = new EventDatabaseConnection();
	}

	public EventListWrapper getAllEvents() {
		return this.eDC.getAllEvents();
	}

	public EventListWrapper getEventsFromCommunity(int cID)
			throws NoEventsFromCommunityException {
		EventListWrapper eLW = this.eDC.getEventsFromCommunity(cID);
		if (eLW == null) {
			throw new NoEventsFromCommunityException();
		}
		return eLW;
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
