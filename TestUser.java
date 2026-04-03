import java.util.Scanner;

public class TestUser {
    
    public static void main(String[] args) {
    	
    	   //create Scanner object
        Scanner scan = new Scanner(System.in);
        boolean continueInput = true;
        User[] users = new User[100];
        int option = 0;
        int userCount = 0;
        
        
        //design (do later)
        System.out.println("Welcome to ......");
        System.out.println("-----------------");
        
        //Exception handling
        do{
          try{
            System.out.println("========Menu========");
            System.out.println("1. Register\n2. Login\n3. Exit");
            System.out.print("\nPlease select an option: ");
            option = scan.nextInt();//read user input
            continueInput = false;
            if (option < 1 || option > 3){
            	System.out.println("Incorrect input: An integer between 1-3 is required ");
            	continueInput = true;
            }
          }catch(Exception e){
        	  System.out.println("Incorrect input: An integer between 1-3 is required ");
        	  scan.nextLine();
            }
        }while(continueInput);
        
        clearScreen(100);
        switch(option){
        	case 1:
        		//not yet put trycatch and loop for validation
        		System.out.println("\n\n-----------REGISTER----------");
        		System.out.println("1. Customer");
        		System.out.println("2. Staff");
        		System.out.print("\nPlease select the user mode: ");
        		int mode = scan.nextInt();
        		scan.nextLine();
        		String role = roleConvert(mode);
        		clearScreen(100);
        		
        		System.out.print("Enter username: ");
        		String username = scan.nextLine();
        		System.out.print("Enter password: ");
        		String password = scan.nextLine();
        		//test
        		System.out.println(users.toString());
        		users[userCount] = new User(username, password, role);
        		
        		if (mode == 1){
        			//users[userCount] = new Customer(username, password);
        		}
        		userCount++;	
        		break;
          case 2:
               //login
        	     break;
        	case 3:
        		break;
        	default:
        		System.out.println("Incorrect input: The option is between 1-3 only");
        	
        }
    }
    private static String roleConvert(int mode){
    	   if (mode == 1)
    	   	return "Customer";
    	   else if (mode == 2)
    	   	return "Staff";
        else
        	return "";
    }
    private static void clearScreen(int lines) {
        for (int i = 0; i < lines; i++) System.out.println();
    }
}
