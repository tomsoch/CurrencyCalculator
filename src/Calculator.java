import java.util.Locale;
import java.util.Scanner;
/**
 * Calculator class with examples of the currency conversion
 * @author Tomasz Sochacki
 * */
public class Calculator {
    public static void main(String[] args) {
        System.out.println("\nApp converting EUR to the choosen currency\n");
        //example uses of the Parser

        Parser object1 = new Parser();
        System.out.println("1 EUR = " + object1.exchanger(2,"GBP" ) + " GBP");

        Parser object2 = new Parser();
        System.out.println("1 EUR = " + object2.exchanger(5, "PLN" ) + " PLN");

        String next;
        do {
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the amount of Euro to convert: ");
            int a = scanner.nextInt();
            System.out.println("Please enter the currency to convert: ");
            String c = scanner.next();
            Parser object3 = new Parser();
            System.out.println("Result of conversion: " + a + " EUR = " + object3.exchanger(a, c) + " " + c.toUpperCase(Locale.ROOT));
            System.out.println("Would you like to do another conversion? enter: Yes/No");
            next = scanner.next();
        }while (next.equalsIgnoreCase(("yes")));

    }
}