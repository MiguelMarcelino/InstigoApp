package io.App.UserCatalogService;

import java.util.List;

//Esta classe foi criada visto que no RegisterService o 
//restTemplate.getForObject nao aceita listas
public class EventListWrapper {

	private List<Event> list;
	
    public EventListWrapper() {
    	
    }

	public List<Event> getList() {
		return list;
	}

	public void setList(List<Event> someList) {
		this.list = someList;
	}

}