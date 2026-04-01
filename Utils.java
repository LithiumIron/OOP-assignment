import java.util.Scanner;

public class Utils {

    //clear screen
    public static void clearScreen(int lines) {
        for (int i = 0; i < lines; i++) System.out.println();
    }
  
    //pause
    public static void pause() {
        System.out.print("\nPress Enter to continue...");
        new Scanner(System.in).nextLine();
    }

    //check Int input
    public static int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {//if invalid then retry until valid input
            System.out.print("Please enter a number: ");
            sc.next();
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    //chekc Int input range
    public static int getIntRange(Scanner sc, int min, int max) {
        while (true) {
            int v = getInt(sc);
            if (v >= min && v <= max) return v;
            System.out.printf("Please enter a number between %d and %d: ", min, max);
        }
    }
}
