import java.io.*;
import java.util.*;

/**
 * Hall class represents a cinema hall.
 * Manages hall data (ID, type) and provides CRUD operations with file persistence.
 * 
 * ID Generation: uses a persistent counter (hall_counter.txt) to ensure 
 * unique IDs even after halls are deleted.
 */
public class Hall {
    // ==================== CONSTANTS & STATIC FIELDS ====================
    private static final String FILE_NAME = "halls.txt";
    private static final String COUNTER_FILE = "hall_counter.txt";
    private static List<Hall> hallList = new ArrayList<>();
    private static int nextIdNumber = 1;   // next available ID number (H001 -> 1)

    // ==================== INSTANCE FIELDS ====================
    private String hallId;
    private String type; // Small / Medium / Large

    // ==================== CONSTRUCTORS ====================
    public Hall(String hallId, String type) {
        this.hallId = hallId;
        this.type = type;
    }

    public Hall(String type) {
        this.hallId = generateNextId();
        this.type = type;
    }

    // ==================== GETTERS ====================
    public String getHallId() { return hallId; }
    public String getType()   { return type; }

    // ==================== SETTERS ====================
    public void setType(String type) { this.type = type; }

    // ==================== STRING REPRESENTATION ====================
    public String toString() {
        return "ID: " + getHallId() + " | Type: " + getType();
    }

    // ==================== ID GENERATION (persistent counter) ====================
    /**
     * Generates next hall ID using a persistent counter that never decreases.
     * Format: H001, H002, ...
     */
    private static String generateNextId() {
        String id = String.format("H%03d", nextIdNumber);
        nextIdNumber++;          // increment for next hall
        saveCounter();           // immediately save the new counter value
        return id;
    }

    /**
     * Saves the current counter value to a separate file.
     */
    private static void saveCounter() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(COUNTER_FILE))) {
            bw.write(String.valueOf(nextIdNumber));
        } catch (IOException e) {
            System.out.println("Error saving hall counter.");
        }
    }

    /**
     * Loads the counter from file. If file doesn't exist, initializes counter
     * based on the maximum ID found in the hall list (for backward compatibility).
     */
    private static void loadCounter() {
        File counterFile = new File(COUNTER_FILE);
        if (counterFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(counterFile))) {
                String line = br.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    nextIdNumber = Integer.parseInt(line.trim());
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error loading hall counter.");
            }
        }

        // If counter file doesn't exist, calculate from existing halls
        int max = 0;
        for (Hall h : hallList) {
            int num = Integer.parseInt(h.getHallId().substring(1));
            if (num > max) max = num;
        }
        nextIdNumber = max + 1;
        saveCounter();   // create the counter file for future runs
    }

    // ==================== FILE OPERATIONS ====================
    public static void loadFromFile() {
        // First load halls
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 2) {
                        hallList.add(new Hall(parts[0], parts[1]));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error loading halls.");
            }
        }

        // Then load/initialize counter (depends on hallList)
        loadCounter();
    }

    private static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Hall h : hallList) {
                bw.write(h.getHallId() + "|" + h.getType());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving halls.");
        }
        // counter is saved in generateNextId, but we also save it here for safety
        saveCounter();
    }

    // ==================== CRUD OPERATIONS ====================
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

    private static Hall findHallById(String id) {
        for (Hall h : hallList) {
            if (h.getHallId().equalsIgnoreCase(id)) return h;
        }
        return null;
    }

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
            // Counter is NOT decreased, so IDs remain unique forever.
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // ==================== DISPLAY METHODS ====================
    public static void displayHallSummary() {
        int small = 0, medium = 0, large = 0;

        for (Hall h : hallList) {
            switch (h.getType()) {
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

    private static void displayAllHalls() {
        System.out.println("\n===== ALL HALLS =====");
        for (Hall h : hallList) {
            System.out.println("ID: " + h.getHallId() + " | Type: " + h.getType());
        }
        System.out.println("=====================\n");
    }

    public static void displayAllHallsDetailed(Scanner scanner) {
        clearScreen();

        System.out.println("------------------------------------------");
        System.out.println("|             HALL DETAILS               |");
        System.out.println("----------------------+-------------------");
        System.out.println("|       Hall ID       |       Type       |");
        System.out.println("----------------------+-------------------");

        for (Hall h : hallList) {
            System.out.printf("|        %-5s        |      %-7s     |\n", h.getHallId(), h.getType());
            System.out.println("|---------------------+------------------|");
        }

        System.out.println("\nTotal halls: " + hallList.size());

        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // ==================== SEAT MAP DISPLAY ====================
    public static void displaySeatMapMenu(Scanner scanner) {
        while (true) {
            clearScreen();

            System.out.println("======= VIEW HALL SEAT MAP =======");
            System.out.println("1. Small Hall");
            System.out.println("2. Medium Hall");
            System.out.println("3. Large Hall");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");

            int c = getIntRange(scanner, 0, 3);

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

    // ==================== UTILITY METHODS ====================
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
