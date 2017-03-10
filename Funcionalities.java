package Organizer;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Funcionalities {
	public static ArrayList<Event> arrayOfEvents;
	private DateFormat formatter;
	public Funcionalities(){
		arrayOfEvents = new ArrayList<Event>();
		formatter = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
	}

	public void loadFile() throws IOException, ClassNotFoundException{
		FileInputStream reader = null;
		ObjectInputStream in = null;

		try {
			reader = new FileInputStream("new.txt");
			in = new ObjectInputStream(reader);
			arrayOfEvents = (ArrayList<Event>) in.readObject();

		} catch (Exception e) {
			System.out.println("Problem with loading your events or there aren't events for load!");
		    e.printStackTrace();
		}
			
		finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public void addEventToList(Event event) throws IOException{
		try{
		arrayOfEvents.add(event);	
		addArrayToFile(); 
		System.out.println("You successfully add the event!");
		System.out.println();
		}
		catch(Exception e){
			System.out.println("You didn't add event!");
		}
		}
	
	public void removeElementFromFile(int index) throws IOException{
		try{
		arrayOfEvents.remove(index-1);
		addArrayToFile(); 
		System.out.println("You successfully delete this event!");
		System.out.println();
		}
		catch(Exception e){
			System.out.println("You didn't remove event!");
		}
	}

	
	public void addArrayToFile() throws IOException{
		ObjectOutputStream out = null;
		try {
		    FileOutputStream writer = new FileOutputStream("new.txt");
		    out = new ObjectOutputStream(writer);   
		    out.writeObject(arrayOfEvents); 
		} 
		catch(IOException e) {
			System.out.println("Your events are not saved!");
		    e.printStackTrace();
		}
		finally{
			out.close();
		}
	}
	
	public void printAllEvents(){
		try{
		for(int i=0; i<arrayOfEvents.size(); i++){
			System.out.println(i+1 + ". " + arrayOfEvents.get(i));
		}
		    System.out.println();
		}
		catch(Exception e){
			System.out.println("Problem with printing the events!");
		}
		}
	
		public void editEvent(int i) throws ParseException, IOException{
			try{
			Scanner sc=new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice; 
		System.out.println("Enter what do you want to edit: ");
		System.out.println("1. The type of the event");
		System.out.println("2. The marker of the event");
		System.out.println("3. The date of the event");
		System.out.println("4. The description of the event");
		choice = sc.nextInt();
		System.out.println("Enter the new value:");
		String justNew = br.readLine();
		switch(choice){
		case 1:{
			arrayOfEvents.get(i).setType(EventType.valueOf(justNew));
			System.out.println("Done!");
			addArrayToFile();
			break;
		}
		case 2: {
			arrayOfEvents.get(i).setMarker(MarkerType.valueOf(justNew));
			System.out.println("Done!");
			addArrayToFile();
			break;
		}
		case 3: {
			arrayOfEvents.get(i).setDate(formatter.parse(justNew));
			System.out.println("Done!");
			addArrayToFile();
			break;
		}
		case 4: {
			arrayOfEvents.get(i).setDescription(justNew);
			System.out.println("Done!");
			addArrayToFile();
			break;
		}
		default: {
			System.out.println("There isn't such property");
			break;
		}
		}
			}
		catch(Exception e){
			System.out.println("Problem with editing an event!");
		}
	}
		
		public void printEventsByMonthOrByDay(){
			try{
			int choice=0;
			Scanner sc=new Scanner(System.in);
			System.out.println("Choose an option:\n");
			System.out.println("1.Print event which month is the month you are going to enter");
			System.out.println("2.Print event which day of month is equal to the day you are goint to enter");
			choice=sc.nextInt();
			
			int month=0,counter=0;
			String date;
			boolean isPrinted=false;
			if(choice==1){
			System.out.println("Enter month:");
			month=sc.nextInt();
			System.out.println("Events for month "+month);
			for(int c=0;c<arrayOfEvents.size();c++){
				if(arrayOfEvents.get(c).getDate().getMonth()+1==month){		
					System.out.println(arrayOfEvents.get(c).toString());
					isPrinted=true;
				}
			}
			if(!isPrinted){
				System.out.println("There aren't events for this month");
			}
			
			}
			
		else if(choice==2){
				System.out.println("Enter day and month:dd/MM");
				date=sc.next();
				String[] dateSplitted=date.split("/");
				System.out.println(Integer.parseInt(dateSplitted[1]));
				System.out.println("Events for "+date);
				for(int c=0;c<arrayOfEvents.size();c++){
					if(arrayOfEvents.get(c).getDate().getDate()==Integer.parseInt(dateSplitted[0]) && arrayOfEvents.get(c).getDate().getMonth()+1==Integer.parseInt(dateSplitted[1]) ){
						System.out.println(arrayOfEvents.get(c).toString());
						isPrinted=true;
					}
				}
				if(!isPrinted){
					System.out.println("There aren't events for this day");
				}
			}	
			}
			catch(Exception e){
				System.out.println("Problem with printing the events by choice!");
			}
			
	}
}
	

