
public class Ticket {
//data properties
private String ticketID;
private String ticketType;

private static int counter = 0;


//-------------------- methods --------------
//no arg cons
public Ticket(){
	this.ticketID = String.format("T%03d", counter++);
	this.ticketType = " ";
}
  
//cons with args
public Ticket(String ticketType){
	this.ticketID = String.format("T%03d", counter++);
	this.ticketType = ticketType;

}

//getter
public String getTicketID(){
	return ticketID;
}

public String getTicketType(){
	return ticketType;
}


//setter
public void setTicketType(String ticketType){
	this.ticketType = ticketType;
}

//------------------ other methods --------------------
public static String ticketHeader(){
	return String.format("\t\t%-15s \n\t\t%-15s \n\t\t%-15s\n\n%-15s %-11s\n%-15s %-11s\n", 
							"========", " Ticket", "========",
							"Ticket ID", "Ticket Type", "=========", "===========" );
}

public static String noOfTickets(){
	return "Please enter number of tickets ";
}

public static String selectTicketType(){
	return "Please select a ticket type\n" +
			"1. Adult\n" +
			"2. Child\n" +
			"3. Student\n" +
			"Enter your choice for ticket ";
} 


public String toString(){
	return String.format("%-15s %-11s\n",
						ticketID, ticketType);
	
}
    
}
