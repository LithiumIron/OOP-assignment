import java.io.*;
import java.util.*;

public class Hall {
    private static final String FILE_NAME = "halls.txt";
    private static List<Hall> hallList = new ArrayList<>();
    private static int idCounter = 1;

    private String hallId;
    private String type; // Small / Medium / Large

    public Hall(String id, String type) {
        this.hallId = id;
        this.type = type;
    }

    public Hall(String type) {
        this.hallId = generateNextId();
        this.type = type;
    }

    public String getHallId() { return hallId; }
    public String getType() { return type; }

    // ---------- ID ----------
    private static String generateNextId() {
        return String.format("H%03d", idCounter++);
    }

    // ---------- FILE ----------
    public static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int maxId = 0;

            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length == 2) {
                    hallList.add(new Hall(p[0], p[1]));

                    int num = Integer.parseInt(p[0].substring(1));
                    if (num > maxId) maxId = num;
                }
            }
            idCounter = maxId + 1;
        } catch (Exception e) {
            System.out.println("Error loading halls.");
        }
    }

    private static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Hall h : hallList) {
                bw.write(h.hallId + "|" + h.type);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving halls.");
        }
    }

    // ---------- ADD ----------
    public static void addHallByType(int typeChoice) {
        String type = switch (typeChoice) {
            case 1 -> "Small";
            case 2 -> "Medium";
            case 3 -> "Large";
            default -> "";
        };

        Hall h = new Hall(type);
        hallList.add(h);
        saveToFile();

        System.out.println("\nHall added successfully!");
        System.out.println("ID: " + h.getHallId());
        System.out.println("Type: " + h.getType());
    }

    // ---------- SUMMARY ----------
    public static void displayHallSummary() {
        int small = 0, medium = 0, large = 0;

        for (Hall h : hallList) {
            switch (h.type) {
                case "Small" -> small++;
                case "Medium" -> medium++;
                case "Large" -> large++;
            }
        }

        System.out.println("\n========= HALL SUMMARY =========");
        System.out.println("Small Hall  : " + small);
        System.out.println("Medium Hall : " + medium);
        System.out.println("Large Hall  : " + large);
        System.out.println("-------------------------------");
        System.out.println("Total Number of Halls: " + hallList.size());
        System.out.println("================================");

        
        System.out.print("\nPress Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    // ---------- DELETE ----------
    public static void deleteHallPrompt(Scanner scanner) {
        displayAllHalls();

        System.out.print("Enter Hall ID to delete: ");
        String id = scanner.nextLine().trim();

        Hall h = findHallById(id);
        if (h == null) {
            System.out.println("Hall ID not found!");
            System.out.print("\nPress ENTER to continue...");
            scanner.nextLine();
            return;
        }

        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            hallList.remove(h);
            saveToFile();
            System.out.println("Hall deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static Hall findHallById(String id) {
        for (Hall h : hallList) {
            if (h.hallId.equalsIgnoreCase(id)) return h;
        }
        return null;
    }

    // ---------- LIST ----------
    private static void displayAllHalls() {
        System.out.println("\n===== ALL HALLS =====");
        for (Hall h : hallList) {
            System.out.println("ID: " + h.hallId + " | Type: " + h.type);
        }
        System.out.println("=====================\n");
    }

    // ---------- SEAT MAP ----------
    public static void displaySeatMapMenu(Scanner scanner) {
        while (true) {
            clearScreen();

            System.out.println("======= VIEW HALL SEAT MAP =======");
            System.out.println("1. Small Hall");
            System.out.println("2. Medium Hall");
            System.out.println("3. Large Hall");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            int c = getIntRange(scanner,0,3);

            if (c == 0) return;

            clearScreen();

            switch (c) {
                case 1 -> printSmallHall();
                case 2 -> printMediumHall();
                case 3 -> printLargeHall();
            }

            pause();
        }
    }

    // ---------- ASCII MAPS ----------
    private static void printSmallHall() {
        System.out.println("        Small Hall");
        System.out.println("      ---------------");
        System.out.println("   __/    SCREEN     \\__");
        System.out.println("");
        System.out.println("D| o o o o o  o o o o o o ");
        System.out.println("C| o o o o o  o o o o o o ");
        System.out.println("B| o o o o o  o o o o o o ");
        System.out.println("A| o o o o o  o o o o o o ");
        System.out.println("--------------------------");
        System.out.println("   1 2 3 4 5  6 7 8 9 a b");
        System.out.println("\nHall Capacity: 44 seats");
    }

    private static void printMediumHall() {
        System.out.println("            Medium Hall");
        System.out.println("      -------------------------");
        System.out.println("  ___/         SCREEN         \\___");
        System.out.println("");
        System.out.println("E| o o o o o  o o o o o o  o o o o ");
        System.out.println("D| o o o o o  o o o o o o  o o o o ");
        System.out.println("C| o o o o o  o o o o o o  o o o o ");
        System.out.println("B| o o o o o  o o o o o o  o o o o ");
        System.out.println("A| o o o o o  o o o o o o  o o o o ");
        System.out.println("------------------------------------");
        System.out.println("   1 2 3 4 5  6 7 8 9 a b  c d e f");
        System.out.println("\nHall Capacity: 75 seats");
    }

    private static void printLargeHall() {
        System.out.println("            Large Hall");
        System.out.println("        -----------------");
        System.out.println("   ____/      SCREEN     \\____");
        System.out.println("");
        System.out.println("K| o o o  o o o o o o o o  o o ");
        System.out.println("J| o o o  o o o o o o o o  o o ");
        System.out.println("I| o o o  o o o o o o o o  o o ");
        System.out.println("H| o o o  o o o o o o o o  o o ");
        System.out.println("G| o o o  o o o o o o o o  o o ");
        System.out.println("F| o o o  o o o o o o o o  o o ");
        System.out.println("E| o o o  o o o o o o o o  o o ");
        System.out.println("D| o o o  o o o o o o o o  o o ");
        System.out.println("C| o o o  o o o o o o o o  o o ");
        System.out.println("B| o o o  o o o o o o o o  o o ");
        System.out.println("A| o o o  o o o o o o o o  o o ");
        System.out.println("---------------------------------");
        System.out.println("   1 2 3  4 5 6 7 8 9 a b  c d");
        System.out.println("\nHall Capacity: 143 seats");
    }
    
    //display all halls detailed
    public static void displayAllHallsDetailed(Scanner scanner){
    	clearScreen();
    	
    	System.out.println("------------------------------------------\n|             HALL DETAILS               |\n----------------------+-------------------");
 		System.out.println("|       Hall ID       |       Type       |\n----------------------+-------------------");
 
    	for (Hall h : hallList) {
        	System.out.printf("|        %-5s        |      %-7s     |", h.hallId,h.type);
        	System.out.println("\n|---------------------+------------------|");
    	}

    	System.out.println("\nTotal halls: " + hallList.size());

    	System.out.print("\nPress Enter to continue...");
    	scanner.nextLine();
    	
    }

    // ---------- UTILS ----------
    private static void pause() {
        System.out.print("\nPress Enter to continue...");
        new Scanner(System.in).nextLine(); 
    }

    private static void clearScreen() {
        for (int i = 0; i < 30; i++) System.out.println();
    }

    private static int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a number: ");
            sc.next();
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }
    
    private static int getIntRange(Scanner sc, int min, int max) {
        while (true) {
            int v = getInt(sc);
            if (v >= min && v <= max) return v;
            System.out.printf("Please enter a number between %d and %d: ", min, max);
        }
    }
    
}
