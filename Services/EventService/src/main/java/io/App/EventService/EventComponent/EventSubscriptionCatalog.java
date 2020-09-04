package io.App.EventService.EventComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.EventSubscriptionDatabaseConnection;
import io.App.EventService.exceptions.InternalAppException;

@SpringBootApplication
public class EventSubscriptionCatalog {

	private EventSubscriptionDatabaseConnection uEDM;

	public EventSubscriptionCatalog() {
		this.uEDM = new EventSubscriptionDatabaseConnection();
	}

	public List<Event> eventsFromSubbedCommunities(int uID) throws NumberFormatException, InternalAppException {
		return uEDM.eventsFromSubCommunities(uID);
	}

}
