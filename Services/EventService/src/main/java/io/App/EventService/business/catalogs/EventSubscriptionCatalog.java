package io.App.EventService.business.catalogs;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.business.Event;
import io.App.EventService.business.exceptions.InternalAppException;
import io.App.EventService.databaseConnection.EventSubscriptionDatabaseConnection;

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
