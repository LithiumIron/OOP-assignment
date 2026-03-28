import java.io.*;
import java.util.*;

public class Movie {
    private static final String FILE_NAME = "movies.txt";
    private static List<Movie> movieList = new ArrayList<>();

    public static final String[] VALID_GENRES = {
        "Action", "Adventure", "Animation", "Comedy", "Drama",
        "Fantasy", "Horror", "Musical", "Romance", "Sci-Fi",
        "Thriller", "War"
    };

    public static final String[] VALID_STATUSES = {
        "Now Showing", "Coming Soon", "Ended"
    };

    private String movieId;
    private String title;
    private String genre;
    private int duration;
    private int ageRating;
    private String status;

    // ---------- CONSTRUCTOR ----------
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
        this.movieId = generateNextId();
    }

    @Override
    public String toString() {
        return String.format("ID: %-6s | Title: %-20s | Genre: %-12s | Duration: %3d | Rating: %2d | Status: %-12s",
                movieId, title, genre, duration, ageRating, status);
    }

    // ---------- FILE ----------
    public static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length == 6) {
                    movieList.add(new Movie(p[0], p[1], p[2],
                            Integer.parseInt(p[3]),
                            Integer.parseInt(p[4]), p[5]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading movies.");
        }
    }

    private static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Movie m : movieList) {
                bw.write(m.movieId + "|" + m.title + "|" + m.genre + "|" +
                        m.duration + "|" + m.ageRating + "|" + m.status);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving movies.");
        }
    }

    private static String generateNextId() {
        int max = 0;
        for (Movie m : movieList) {
            int num = Integer.parseInt(m.movieId.substring(1));
            if (num > max) max = num;
        }
        return String.format("M%03d", max + 1);
    }

    // ---------- CRUD ----------
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
    }

    public static Movie findMovieById(String id) {
        for (Movie m : movieList) {
            if (m.movieId.equalsIgnoreCase(id)) return m;
        }
        return null;
    }

    public static List<Movie> findMoviesByTitle(String keyword) {
        List<Movie> result = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.title.toLowerCase().contains(keyword.toLowerCase())) {
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
        System.out.printf("|ID     : %-10s|\n", movie.movieId);
        System.out.printf("|Title  : %-10s|\n", movie.title);
        System.out.printf("|Genre  : %-10s|\n", movie.genre);
        System.out.printf("|Duration: %-9d|\n", movie.duration);
        System.out.printf("|Rating : %-10d|\n", movie.ageRating);
        System.out.printf("|Status : %-10s|\n", movie.status);
        System.out.println("---------------------------------");

        System.out.println("\n--- Update Movie Status ---");
        String newStatus = selectStatus(scanner);

        movie.status = newStatus;
        saveToFile();

        System.out.println("\nMovie status updated successfully!");
        System.out.println(movie);

        System.out.print("\nPress Enter to return...");
        scanner.nextLine();
    }

    // ---------- DISPLAY MENU ----------
    public static void displayMoviesMenu(Scanner scanner) {
        while (true) {


            Set<String> set = new TreeSet<>();
            for (Movie m : movieList) set.add(m.genre);
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

            int choice = getInt(scanner);

            if (choice == 0) return;

            List<Movie> list;
            String title;

            if (choice == i) {
                list = new ArrayList<>(movieList);
                title = "All Movies";
            } else if (choice >= 1 && choice <= genres.size()) {
                String g = genres.get(choice - 1);
                list = filterByGenre(g);
                title = g + " Movies";
            } else {
                System.out.println("Invalid choice.");
                continue;
            }

            clearScreenLarge();

            printTable(list, title);

            System.out.print("Press Enter to return...");
            scanner.nextLine();
        }
    }

    private static List<Movie> filterByGenre(String g) {
        List<Movie> r = new ArrayList<>();
        for (Movie m : movieList)
            if (m.genre.equalsIgnoreCase(g)) r.add(m);
        return r;
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
        System.out.println("--------+----------------------+--------------+----------+--------+--------------");

        for (Movie m : list) {
            System.out.printf("%-6s | %-20s | %-12s | %-8d | %-6d | %-12s%n",
                    m.movieId,
                    truncate(m.title, 20),
                    m.genre,
                    m.duration,
                    m.ageRating,
                    m.status);
        }

        System.out.println("====================================================================================");
        System.out.println("\nTotal number of " + title.toLowerCase() + ": " + list.size());
    }

    // ---------- SUMMARY ----------
    public static void displayMovieSummary() {
        Map<String, Integer> map = new TreeMap<>();

        for (Movie m : movieList) {
            map.put(m.genre, map.getOrDefault(m.genre, 0) + 1);
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

    // ---------- INPUT ----------
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

    // ---------- UTILS ----------
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

    public static void displayAllMovies() {
        printTable(movieList, "All Movies");
    }
}
