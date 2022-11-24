import java.util.Scanner;

/**
 * This class contains the scanner and instructions for the menu and the coordinate's selection.
 */
public class Scans {
    /**
     * Asks integer for menu selection.
     *
     * @return the integer with the selection.
     */
    public static String askMenu() {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            System.out.print("Please, input a number: ");
            return askMenu();
        }
        return sc.next();
    }

    /**
     * Ask the chars for coordinate selection.
     *
     * @return the char with coordinates.
     */
    public static String askCor() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next().toUpperCase() + "V  ";
        if (str.charAt(0) > '9') {
            str = swap(str);
        }
        if (!((str.charAt(0) >= '0' && str.charAt(0) <= '9')
                && (str.charAt(1) >= 'A' && str.charAt(1) <= 'J')
                && (str.charAt(2) == 'V' || str.charAt(2) == 'H'))) {
            System.out.print("Incorrect cords, try again: ");
            return askCor();
        }
        return str;
    }

    /**
     * Swaps the first and second char if they got ordered incorrectly.
     *
     * @param cor the string to be corrected.
     * @return the corrected string.
     */
    public static String swap(String cor) {
        char[] temp = cor.toCharArray();
        char temp2 = temp[0];
        temp[0] = temp[1];
        temp[1] = temp2;
        cor = new String(temp);
        return cor;
    }
}
