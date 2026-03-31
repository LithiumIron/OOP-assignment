import java.io.*;
import java.util.*;

/**
 * Movie class represents a movie entity.
 * Manages movie data and provides CRUD operations with file persistence.
 * 
 * ID Generation: uses a persistent counter (movie_counter.txt) to ensure 
 * unique IDs even after movies are deleted.
 */
public class Movie {
    // ==================== CONSTANTS & STATIC FIELDS ====================
    private static final String FILE_NAME = "movies.txt";
    private static final String COUNTER_FILE = "movie_counter.txt";
    private static List<Movie> movieList = new ArrayList<>();
    private static int nextIdNumber = 1;   // next available ID number (M001 -> 1)

    public static final String[] VALID_GENRES = {
        "Action", "Adventure", "Animation", "Comedy", "Drama",
        "Fantasy", "Horror", "Musical", "Romance", "Sci-Fi",
        "Thriller", "War"
    };

    public static final String[] VALID_STATUSES = {
        "Now Showing", "Coming Soon", "Ended"
    };

    // ==================== INSTANCE FIELDS ====================
    private String movieId;
    private String title;
    private String genre;
    private int duration;
    private int ageRating;
    private String status;

    // ==================== CONSTRUCTORS ====================
    public Movie(String movieId, String title, String genre, int duration, int ageRating, String status) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ageRating = ageRating;
        this.status = status;
    }

    public Movie(String title, String genre, int duration, int ageRating, String status) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ageRating = ageRating;
        this.status = status;
        this.movieId = generateNextId();   // use persistent counter
    }

    // ==================== GETTERS ====================
    public String getMovieId()   { return movieId; }
    public String getTitle()     { return title; }
    public String getGenre()     { return genre; }
    public int getDuration()     { return duration; }
    public int getAgeRating()    { return ageRating; }
    public String getStatus()    { return status; }

    // ==================== SETTERS ====================
    public void setTitle(String title)          { this.title = title; }
    public void setGenre(String genre)          { this.genre = genre; }
    public void setDuration(int duration)       { this.duration = duration; }
    public void setAgeRating(int ageRating)     { this.ageRating = ageRating; }
    public void setStatus(String status)        { this.status = status; }

    // ==================== STRING REPRESENTATION ====================
    public String toString() {
        return String.format("ID: %-6s | Title: %-20s | Genre: %-12s | Duration: %3d | Rating: %2d | Status: %-12s",
                getMovieId(), getTitle(), getGenre(), getDuration(), getAgeRating(), getStatus());
    }

    // ==================== ID GENERATION (persistent counter) ====================
    /**
     * Generates next movie ID using a persistent counter that never decreases.
     * Format: M001, M002, ...
     */
    private static String generateNextId() {
        String id = String.format("M%03d", nextIdNumber);
        nextIdNumber++;          // increment for next movie
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
            System.out.println("Error saving movie counter.");
        }
    }

    /**
     * Loads the counter from file. If file doesn't exist, initializes counter
     * based on the maximum ID found in the movie list (for backward compatibility).
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
                System.out.println("Error loading movie counter.");
            }
        }

        // If counter file doesn't exist, calculate from existing movies
        int max = 0;
        for (Movie m : movieList) {
            int num = Integer.parseInt(m.getMovieId().substring(1));
            if (num > max) max = num;
        }
        nextIdNumber = max + 1;
        saveCounter();   // create the counter file for future runs
    }

    // ==================== FILE OPERATIONS ====================
    public static void loadFromFile() {
        // First load movies
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 6) {
                        movieList.add(new Movie(parts[0], parts[1], parts[2],
                                Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]), parts[5]));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error loading movies.");
            }
        }

        // Then load/initialize counter (depends on movieList)
        loadCounter();
    }

    private static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Movie m : movieList) {
                bw.write(m.getMovieId() + "|" + m.getTitle() + "|" + m.getGenre() + "|" +
                        m.getDuration() + "|" + m.getAgeRating() + "|" + m.getStatus());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving movies.");
        }
        // counter is saved in generateNextId, but we also save it here for safety
        saveCounter();
    }

    // ==================== CRUD OPERATIONS ====================
    public static void addMovie(Movie m) {
        movieList.add(m);
        saveToFile();
        System.out.println("\nMovie added successfully!");
    }

    public static void deleteMovie(String id) {
        Movie m = findMovieById(id);
        if (m == null) {
            System.out.println("Movie ID not found!");
            return;
        }
        movieList.remove(m);
        saveToFile();
        System.out.println("Movie deleted successfully!");
        // Counter is NOT decreased, so IDs remain unique forever.
    }

    public static Movie findMovieById(String id) {
        for (Movie m : movieList) {
            if (m.getMovieId().equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    public static List<Movie> findMoviesByTitle(String keyword) {
        List<Movie> result = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(m);
            }
        }
        return result;
    }

    public static void updateMovie(String id, Scanner scanner) {
        Movie movie = findMovieById(id);
        if (movie == null) {
            System.out.println("Movie ID not found!");
            return;
        }

        clearScreen();

        System.out.println("Current movie info:");
        System.out.println("---------------------------------");
        System.out.printf("|ID      : %-21s|\n", movie.getMovieId());
        System.out.printf("|Title   : %-21s|\n", movie.getTitle());
        System.out.printf("|Genre   : %-21s|\n", movie.getGenre());
        System.out.printf("|Duration: %-21d|\n", movie.getDuration());
        System.out.printf("|Rating  : %-21d|\n", movie.getAgeRating());
        System.out.printf("|Status  : %-21s|\n", movie.getStatus());
        System.out.println("---------------------------------");

        System.out.println("\n--- Update Movie Status ---");
        String newStatus = selectStatus(scanner);

        movie.setStatus(newStatus);
        saveToFile();

        System.out.println("\nMovie status updated successfully!");
        System.out.println(movie.toString());

        System.out.print("\nPress Enter to return...");
        scanner.nextLine();
    }

    // ==================== DISPLAY METHODS ====================
    public static void displayAllMovies() {
        printTable(movieList, "All Movies");
    }

    public static void displayMoviesMenu(Scanner scanner) {
        while (true) {
            Set<String> set = new TreeSet<>();
            for (Movie m : movieList) set.add(m.getGenre());
            List<String> genres = new ArrayList<>(set);

            System.out.println("========================================");
            System.out.println("         DISPLAY MOVIES MENU           ");
            System.out.println("========================================");

            int i = 1;
            for (String g : genres) {
                System.out.println(i++ + ". " + g);
            }
            System.out.println(i + ". All Movies");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-" + i + "): ");

            int choice = getIntRange(scanner, 0, i);

            if (choice == 0) return;

            List<Movie> list;
            String title;

            if (choice == i) {
                list = new ArrayList<>(movieList);
                title = "All Movies";
            } else {
                String g = genres.get(choice - 1);
                list = filterByGenre(g);
                title = g + " Movies";
            }

            clearScreenLarge();
            printTable(list, title);

            System.out.print("Press Enter to return...");
            scanner.nextLine();
            clearScreenLarge();
        }
    }

    public static void displayMovieSummary() {
        Map<String, Integer> map = new TreeMap<>();

        for (Movie m : movieList) {
            map.put(m.getGenre(), map.getOrDefault(m.getGenre(), 0) + 1);
        }

        System.out.println("================== MOVIE SUMMARY ==================");
        System.out.printf("%-15s | %-6s%n", "Genre", "Count");
        System.out.println("-----------------+--------");

        for (String g : map.keySet()) {
            System.out.printf("%-15s | %-6d%n", g, map.get(g));
        }

        System.out.println("===================================================");
        System.out.println("\nTotal number of movies: " + movieList.size());
    }

    // ==================== HELPER METHODS ====================
    private static List<Movie> filterByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.getGenre().equalsIgnoreCase(genre)) result.add(m);
        }
        return result;
    }

    private static void printTable(List<Movie> list, String title) {
        System.out.println("====================================================================================");
        System.out.println("                             " + title.toUpperCase());
        System.out.println("====================================================================================");

        if (list.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }

        System.out.printf("%-6s | %-20s | %-12s | %-8s | %-6s | %-12s%n",
                "ID", "Title", "Genre", "Duration", "Rating", "Status");
        System.out.println("-------+----------------------+--------------+----------+--------+--------------");

        for (Movie m : list) {
            System.out.printf("%-6s | %-20s | %-12s | %-8d | %-6d | %-12s%n",
                    m.getMovieId(),
                    truncate(m.getTitle(), 20),
                    m.getGenre(),
                    m.getDuration(),
                    m.getAgeRating(),
                    m.getStatus());
        }

        System.out.println("====================================================================================");
        System.out.println("\nTotal number of " + title.toLowerCase() + ": " + list.size());
    }

    // ==================== USER INPUT METHODS ====================
    public static String selectGenre(Scanner scanner) {
        System.out.println("\n--- Select Genre ---");
        for (int i = 0; i < VALID_GENRES.length; i++) {
            System.out.println((i + 1) + ". " + VALID_GENRES[i]);
        }
        System.out.print("Enter your choice (1-12): ");
        int c = getIntRange(scanner, 1, VALID_GENRES.length);
        return VALID_GENRES[c - 1];
    }

    public static String selectStatus(Scanner scanner) {
        System.out.println("\n--- Select Status ---");
        for (int i = 0; i < VALID_STATUSES.length; i++) {
            System.out.println((i + 1) + ". " + VALID_STATUSES[i]);
        }
        System.out.print("Enter your choice (1-3): ");
        int c = getIntRange(scanner, 1, VALID_STATUSES.length);
        return VALID_STATUSES[c - 1];
    }

    // ==================== UTILITY METHODS ====================
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

    private static void clearScreen() {
        for (int i = 0; i < 20; i++) System.out.println();
    }

    private static void clearScreenLarge() {
        for (int i = 0; i < 80; i++) System.out.println();
    }

    private static String truncate(String s, int len) {
        if (s.length() <= len) return s;
        return s.substring(0, len - 3) + "...";
    }
}
