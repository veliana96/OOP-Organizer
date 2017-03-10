package Organizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EventType type;
	private MarkerType marker;
	private Date date;  
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
	
	private String description;
	
	
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public MarkerType getMarker() {
		return marker;
	}
	public void setMarker(MarkerType marker) {
		this.marker = marker;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	public Event(EventType type, MarkerType marker, Date date, String description){
		this.type = type;
		this.marker = marker;
		this.description = description;
		this.date = date;	
	}	
	
	@Override
	public String toString() { //override
	    return "Type of event: " + this.getType() + 
	           ", Privacy Marker: " + this.getMarker() + ", Date: "+this.getDate()+ ", Description: "+ this.getDescription();
	}
}
	