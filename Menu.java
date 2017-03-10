package Organizer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
	
	Funcionalities func;
	public Menu(){
		func = new Funcionalities();
	}
	public void choice() throws ParseException, IOException, ClassNotFoundException{
	func.loadFile();
		System.out.println("            WELCOME TO YOUR ORGANIZER            ");
		while(true){
			System.out.println();
			System.out.println("Please, select an option: ");			
			System.out.println("1.View all events");
			System.out.println("2.Add event");
			System.out.println("3.Remove event");
			System.out.println("4.Edit event");
			System.out.println("5.Print events for day or month");
			System.out.println("6.Exit");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch(choice){
		
		case 1: {
			func.printAllEvents(); 
			break; 
		}
		case 2:{ 
			try{
			System.out.println("Enter type of event (Meeting, Task): ");			
		    String typeE = input.next();		   
		    EventType type = EventType.valueOf(typeE);
			System.out.println("Enter marker of event(Public, Confidential, Private): ");
			String markerE = input.next();
			MarkerType marker = MarkerType.valueOf(markerE); 
			System.out.println("Enter date of the event in format dd/MM/yyyy-hh:mm ");
			String dateE = input.next();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
			Date date = formatter.parse(dateE);
			System.out.println("Enter description of your event: ");
			String description = input.next();
			Event event = new Event(type, marker, date, description);
			func.addEventToList(event); 
			break;
			}			
			catch(Exception e){
					System.out.println("You didn't write something in right format, please try again!");
					continue;
				}
			}
		
		
		case 3: {
			try{
		System.out.println("Enter the number of event you want to delete: ");
		int index = input.nextInt();
		func.removeElementFromFile(index); 
		break;
			}
			catch(Exception e){
				System.out.println("There is not an event with this index, try again");
				continue; 
			}
		}
		
		case 4: {
			try{
		System.out.println("Enter the number of event you want to edit: ");
		int i = input.nextInt();
		func.editEvent(i-1); 
		break;
			}
			catch(Exception e){
				System.out.println("There is not an event with this index, try again");
				continue; 
			}
		}
		case 5:{
			func.printEventsByMonthOrByDay();
			break;
		}
		case 6:{
			func.addArrayToFile();
			System.out.println("\nBye");
			System.exit(0);
		}
		
	    }
	  }
   }
}
