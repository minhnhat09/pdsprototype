package fr.esiag.sim.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import fr.esiag.sim.model.Event;

@RestController
public class EventController {

	@RequestMapping("/events")
	public List<Event> getEvents() {
		
		List<Event> events = getData();
		
		return events;
	}
	
	public List<Event> getData(){
		List<Event> events = new ArrayList<>();
		try {
			
			//MongoClient mongo = new MongoClient("mongodb://admin:1109@ds063929.mongolab.com/events",63929);
			
			String textUri = "mongodb://admin:1109@ds063929.mongolab.com:63929/events";
			MongoClientURI uri = new MongoClientURI(textUri);
			MongoClient mongo = new MongoClient(uri);
			
			DB db = mongo.getDB("events");
			DBCollection collection = db.getCollection("event");
			DBCursor cursor = collection.find();
			while(cursor.hasNext()) {
				DBObject object = cursor.next();
				Event event = new Event();
				event.setName(object.get("name").toString());
				System.out.println(object.get("name").toString());
				events.add(event);
			}
			
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		return events;
		
	}
	
}
