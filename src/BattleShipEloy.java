import java.util.Scanner;

public class BattleShipEloy {
    public static void main(String[] args) {
        char[][][][] boards = new char[2][2][10][10];
        waterBoard(boards);
        int gamemode = menu();

        int difficulty = 0;
        if (gamemode == 2) difficulty = difMenu();

        placeBoats(boards);

    }
    public static void waterBoard(char[][][][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k < a[0][0].length; k++) {
                    for (int l = 0; l < a[0][0][0].length; l++) {
                        a[i][j][k][l] = '~';
                    }
                }
            }
        }
    }
    public static int menu() {
        String selection;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("**************************");
        System.out.println("* Welcome to BattleShip! *");
        System.out.println("* Select gamemode:       *");
        System.out.println("* 1- Player vs Player    *");
        System.out.println("* 2- Player vs Computer  *");
        System.out.println("**************************");
        System.out.print("Select 1 or 2: ");
        selection = askMenu();
        if (Integer.parseInt(selection) > 2 || Integer.parseInt(selection) < 1) {
            return menu();
        }
        return Integer.parseInt(selection);
    }

    public static int difMenu() {
        String selection;
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("**************************");
        System.out.println("* Select difficulty:     *");
        System.out.println("* 1- Easy                *");
        System.out.println("* 2- Normal              *");
        System.out.println("* 3- Hard                *");
        System.out.println("**************************");
        System.out.print("Select 1, 2 or 3: ");
        selection = askMenu();
        if (Integer.parseInt(selection) > 3 || Integer.parseInt(selection) < 1) {
            return difMenu();
        }
        return Integer.parseInt(selection);
    }

    public static String askMenu() {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return askMenu();
        return sc.next();
    }

    public static String askCor() {
        Scanner sc = new Scanner(System.in);
        return sc.next().toUpperCase()+"   ";
        /*if ((aux.charAt(0) >= '0' && aux.charAt(0) <= '9')
                && (aux.charAt(1) >= 'A' && aux.charAt(1) <= 'J')
                && (aux.charAt(3) == 'V' || aux.charAt(3) <= 'H')) {
            return aux;
        } else return askCor();*/
    }

    public static void placeBoats(char[][][][] boards) {
        carrier(0,boards);
    }



    public static void carrier(int player,char[][][][] boards) {
        display(0,0,boards);
        System.out.println("Jugador "+(player+1)+" Coloque su crucero: ");
        String cor = askCor();
        if (!((cor.charAt(0) >= '0' && cor.charAt(0) <= '9')
                && (cor.charAt(1) >= 'A' && cor.charAt(1) <= 'J')
                && (cor.charAt(2) == 'V' || cor.charAt(2) == 'H'))) {
            carrier(player,boards);
        }

    }


    public static void display(int player, int type ,char[][][][] boards) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("J"+(player+1)+"  A  B  C  D  E  F  G  H  I  J");
        for (int i = 0; i < boards[0][0].length; i++) {
            if (i < 9) System.out.print(" ");
            System.out.print(i + 1 + " ");
            for (int j = 0; j < boards[0][0][0].length; j++) {
                System.out.print(" "+boards[0][0][i][j]+" ");
            }
            System.out.println();
        }
    }
}
