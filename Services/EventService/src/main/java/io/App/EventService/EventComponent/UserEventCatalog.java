package io.App.EventService.EventComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.UserEventDatabaseManagement;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.exceptions.InternalAppException;

@SpringBootApplication
public class UserEventCatalog {

	private UserEventDatabaseManagement uEDM;

	public UserEventCatalog() {
		this.uEDM = new UserEventDatabaseManagement();
	}

	public EventListWrapper eventsFromSubbedCommunities(int uID) throws NumberFormatException, InternalAppException {
		return uEDM.eventsFromSubCommunities(uID);
	}

}
