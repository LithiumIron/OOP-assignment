import java.util.Scanner;
import java.util.List;

public class mh_main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Movie.loadFromFile();
        Hall.loadFromFile();

        boolean exit = false;
        while (!exit) {//no exit
            clearScreen(100);
            printMainMenu();

            int choice = getIntInput(0, 2);
            scanner.nextLine();

            switch (choice) {
                case 1:manageMovies();break;
                case 2:manageHalls();break;
                case 0:{
                    exit = true;
                    showExitScreen();
                }break;
            }
        }
        scanner.close();
    }

    // ================== MAIN MENU ==================
    private static void printMainMenu() {
        System.out.println("========================================");
        System.out.println("   CINEMA MANAGEMENT SYSTEM - STAFF    ");
        System.out.println("========================================");
        System.out.println("1. Manage Movies");
        System.out.println("2. Manage Halls");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // ================== MOVIE ==================
    private static void manageMovies() {
        boolean back = false;

        while (!back) {
            clearScreen(20);
            printMovieMenu();

            int choice = getIntInput(0, 6);
            scanner.nextLine();

            switch (choice) {
                case 1:addMovie();break;
                case 2:Movie.displayMoviesMenu(scanner);break;
                case 3:updateMovie();break;
                case 4:deleteMovie();break;
                case 5:searchMovie();break;
                case 6:{
                    clearScreen(20);
                    Movie.displayMovieSummary();
                    System.out.print("\nPress Enter to return...");
                    scanner.nextLine();
                }break;
                case 0:back = true;break;
            }
        }
    }

    private static void printMovieMenu() {
        System.out.println("--- MOVIE MANAGEMENT ---");
        System.out.println("1. Add New Movie");
        System.out.println("2. View Movies (by genre / all)");
        System.out.println("3. Update Movie");
        System.out.println("4. Delete Movie");
        System.out.println("5. Search Movie");
        System.out.println("6. View Movie Summary (by genre)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void addMovie() {
        clearScreen(20);
        System.out.println("--- Add New Movie ---");

        System.out.print("Title: ");
        String title = scanner.nextLine().trim();

        clearScreen(20);
        String genre = Movie.selectGenre(scanner);

        System.out.print("Duration (minutes): ");
        int duration = getIntInput(1, 500);
        scanner.nextLine();

        System.out.print("Age Rating: ");
        int rating = getIntInput(0, 100);
        scanner.nextLine();

        String status = Movie.selectStatus(scanner);

        Movie m = new Movie(title, genre, duration, rating, status);
        Movie.addMovie(m);

        System.out.println("\nMovie Added:");
        System.out.println(m);

        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void deleteMovie() {
        clearScreen(20);
        Movie.displayAllMovies();

        System.out.print("Enter Movie ID to delete: ");
        String id = scanner.nextLine().trim();

        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            Movie.deleteMovie(id);
        } else {
            System.out.println("Deletion cancelled.");
        }

        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void updateMovie() {
        System.out.print("Enter Movie ID: ");
        String id = scanner.nextLine();
        Movie.updateMovie(id, scanner);
    }

    private static void searchMovie() {
    	clearScreen(40);
        System.out.println("Search by movie title.\nExample: Minion, ion, mi, i");
        System.out.print("\nEnter keyword: ");
        String key = scanner.nextLine();

        List<Movie> results = Movie.findMoviesByTitle(key);

        if (results.isEmpty()) {
            System.out.println("No results.");
        } else {
            for (Movie m : results) {
                System.out.println(m);
            }
        }

        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // ================== HALL ==================
    private static void manageHalls() {
        boolean back = false;

        while (!back) {
            clearScreen(20);
            printHallMenu();

            int choice = getIntInput(0, 5);
            scanner.nextLine();

            switch (choice) {
                case 1:
                	addHall();break;
                case 2:
                	clearScreen(100);
                	Hall.displayHallSummary();break;
                case 3:
                	Hall.deleteHallPrompt(scanner);break;
                case 4:
                	Hall.displaySeatMapMenu(scanner);break;
                case 5:
                	Hall.displayAllHallsDetailed(scanner);break;
                case 0:
                	back = true;break;
            }
        }
    }

    private static void printHallMenu() {
        System.out.println("--- HALL MANAGEMENT ---");
        System.out.println("1. Add New Hall");
        System.out.println("2. View Hall Summary");
        System.out.println("3. Delete Hall");
        System.out.println("4. View Hall Seat Map");
        System.out.println("5. View Hall Details");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void addHall() {
        clearScreen(20);

        System.out.println("--- Select Hall Type ---");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        System.out.print("Enter choice: ");

        int type = getIntInput(1, 3);
        Hall.addHallByType(type);

        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    // ================== EXIT ==================
    private static void showExitScreen() {
        clearScreen(100);

        System.out.println(" /$$$$$$$                            /$$$$$$$                            /$$");
        System.out.println("| $$__  $$                          | $$__  $$                          | $$");
        System.out.println("| $$  \\ $$ /$$   /$$  /$$$$$$       | $$  \\ $$ /$$   /$$  /$$$$$$       | $$");
        System.out.println("| $$$$$$$ | $$  | $$ /$$__  $$      | $$$$$$$ | $$  | $$ /$$__  $$      | $$");
        System.out.println("| $$__  $$| $$  | $$| $$$$$$$$      | $$__  $$| $$  | $$| $$$$$$$$      |__/");
        System.out.println("| $$  \\ $$| $$  | $$| $$_____/      | $$  \\ $$| $$  | $$| $$_____/");
        System.out.println("| $$$$$$$/|  $$$$$$$|  $$$$$$$      | $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$");
        System.out.println("|_______/  \\____  $$ \\_______/      |_______/  \\____  $$ \\_______/      |__/");
        System.out.println("           /$$  | $$                           /$$  | $$");
        System.out.println("          |  $$$$$$/                          |  $$$$$$/");
        System.out.println("           \\______/                            \\______/");

        System.out.println("\n------------------------------------------");
        System.out.println("   Thank you for using the system. Goodbye!");
        System.out.println("------------------------------------------");

        System.out.println("\nProcess completed.");
    }

    // ================== UTILS ==================
    private static int getIntInput(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int v = scanner.nextInt();
                if (v >= min && v <= max) return v;
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            }
        }
    }

    private static void clearScreen(int lines) {
        for (int i = 0; i < lines; i++) System.out.println();
    }
}
