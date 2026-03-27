public class main {

    public static void main(String[] args) {
        Snack s1= new Snack();
        Drink d1= new Drink();
        Combo c1= new Combo();

    //testrun only
        System.out.println("Here are the available snacks:");
        System.out.print(s1.toString());
        System.out.println("\nHere are the available drinks:");
        System.out.print(d1.toString());
        System.out.println("\nHere are the available combos:");
        System.out.print(c1.toString());
    }
}
