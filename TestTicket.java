import java.util.Scanner;
public class TestTicket {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Ticket t1 = new Ticket();
    
             
       	System.out.print(Ticket.noOfTickets());
        int tickets = scan.nextInt();
        Ticket [] tic = new Ticket[tickets];
        scan.nextLine();//get number of tickets
        
        int size;
        do{
        	System.out.print(Ticket.selectHallSize());
       	 	size = scan.nextInt();
        	scan.nextLine();
        		if(size < 1 || size > 3)
        		System.out.println(Ticket.sizeInvalidMsg());
        
        	}while (size < 1 || size > 3);//read and validate hall size data
        
        for (int i=0 ; i<tickets ; i++){//loop count based on number of tickets enterred
        	String ticketType = "";
        	int choice = -1;
        	
        	double ticketAmt = 0;
        	
        do{
        	switch(size){
        		case 1:
        			System.out.print(Ticket.selectTicketType1() + (i + 1) + ": ");
        			choice = scan.nextInt();
        			scan.nextLine();
        			
           				switch(choice){
        					case 1:
        						ticketType = "Adult";
        						ticketAmt = 15.00;
        						break;
        					case 2:
        						ticketType = "Child";
        						ticketAmt = 10.00;
        						break;
        					case 3:
        						ticketType = "Senior";
        						ticketAmt = 10.00;
        						break;
        					default:
        						System.out.println("Invalid choice. Please enter again!");
        					break;
           				}//end switch 1 for small hall
        			
        			break;
        		case 2:
        			System.out.print(Ticket.selectTicketType2() + (i + 1) + ": ");
      				choice = scan.nextInt();
      				scan.nextLine();
      				
      				
        				switch(choice){
        					case 1:
        						ticketType = "Adult";
        						ticketAmt = 20.00;
        						break;
        					case 2:
        						ticketType = "Child";
        						ticketAmt = 12.00;
        						break;
        					case 3:
        						ticketType = "Senior";
        						ticketAmt = 12.00;
        						break;
        					default:
        						System.out.println("Invalid choice. Please enter again!");
        						break;
        				}//end switch 2 for medium hall
      				
      				break;
        		case 3:	
        			System.out.print(Ticket.selectTicketType3() + (i + 1) + ": ");
      				choice = scan.nextInt();
      				scan.nextLine();
      			
        				switch(choice){
        					case 1:
        						ticketType = "Adult";
        						ticketAmt = 25.00;
        						break;
        					case 2:
        						ticketType = "Child";
        						ticketAmt = 15.00;
        						break;
        					case 3:
        						ticketType = "Senior";
        						ticketAmt = 15.00;
        						break;
        					default:
        						System.out.println("Invalid choice. Please enter again!");
        						break;
        			
               			}//end switch 3 for large hall
      				
      				break;
               	  
        		}//end switch size 
        }while(choice < 1 || choice > 3); //end do-while 
        		
        		tic[i] = new Ticket(ticketType, ticketAmt);//array creation to store ticket value
           
           }//end for loop
        		
        		//printing part
        		System.out.println(Ticket.ticketHeader());
        		for (Ticket t : tic){
        			System.out.println(t);
        			
        		}
        		System.out.println(Ticket.printTotal(tic));
        		
        		
        
    }
}

