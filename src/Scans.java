import java.util.Scanner;

public class Scans {
    public static String askMenu() {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return askMenu();
        return sc.next();
    }

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

    public static String swap(String swp) {
        char[] temp = swp.toCharArray();
        char temp2 = temp[0];
        temp[0] = temp[1];
        temp[1] = temp2;
        swp = new String(temp);
        return swp;
    }
}