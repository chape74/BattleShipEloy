
import java.util.Scanner;

public class BattleShipEloy {
    public static void main(String[] args) {
        char[][][][] boards = new char[2][2][10][10];
        String[] hit = {"",""};
        int victory=0;
        int turn=0;
        waterBoard(boards);
        int gamemode = menu();

        int difficulty = 0;
        if (gamemode == 2) difficulty = difMenu();

        placeBoats(boards, difficulty);

        while (victory==0){
            turn(turn,boards,hit);
            if (turn==0) turn=1;
            else turn=0;
        }

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
        clear();
        System.out.println("**************************");
        System.out.println("* Welcome to BattleShip! *");
        System.out.println("* Select gamemode:       *");
        System.out.println("* 1- Player vs Player    *");
        System.out.println("* 2- Player vs Computer  *");
        System.out.println("* 3- Rules               *");
        System.out.println("**************************");
        System.out.println();
        System.out.print("Select 1 to 3: ");
        selection = askMenu();
        if (Integer.parseInt(selection) > 2 || Integer.parseInt(selection) < 1) {
            return menu();
        }
        return Integer.parseInt(selection);
    }

    public static int difMenu(){
        String selection;
        clear();
        System.out.println("**************************");
        System.out.println("* Select difficulty:     *");
        System.out.println("* 1- Easy                *");
        System.out.println("* 2- Normal              *");
        System.out.println("* 3- Hard                *");
        System.out.println("**************************");
        System.out.println();
        System.out.print("Select 1, 2 or 3: ");
        selection = askMenu();
        if (Integer.parseInt(selection) > 3 || Integer.parseInt(selection) < 1) {
            return difMenu();
        }
        return Integer.parseInt(selection);
    }

    public static String askMenu() {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return "0";
        return sc.next();
    }

    public static String askCor() {
        Scanner sc = new Scanner(System.in);
        return sc.next().toUpperCase()+" ";
    }

    public static void placeBoats(char[][][][] boards, int difficulty) {
        boat(0, "carrier", boards, difficulty);
        boat(0, "battleship", boards, difficulty);
        boat(0, "cruiser", boards, difficulty);
        boat(0, "submarine", boards, difficulty);
        boat(0, "destroyer", boards, difficulty);
        boat(1, "carrier", boards, difficulty);
        boat(1, "battleship", boards, difficulty);
        boat(1, "cruiser", boards, difficulty);
        boat(1, "submarine", boards, difficulty);
        boat(1, "destroyer", boards, difficulty);
    }

    public static void boat(int player, String type, char[][][][] boards, int difficulty) {
        clear();
        display(player, 0, boards);
        System.out.print("Player " + (player + 1) + " place your " + type + ": ");
        String cor;
        if (player >= 0 && difficulty > 0) {
            cor = botPlace();
        } else cor = askCor();
        if (cor.charAt(0) > '9') {
            cor = swap(cor);
        }
        if (!((cor.charAt(0) >= '0' && cor.charAt(0) <= '9')
                && (cor.charAt(1) >= 'A' && cor.charAt(1) <= 'J')
                && (cor.charAt(2) == 'V' || cor.charAt(2) == 'H'))) {
            boat(player, type, boards, difficulty);
        }
        int size = 0;
        switch (type) {
            case "carrier":
                size = 5;
                break;
            case "battleship":
                size = 4;
                break;
            case "cruiser":
            case "submarine":
                size = 3;
                break;
            case "destroyer":
                size = 2;
                break;
        }
        if (cor.charAt(2) == 'V') {
            if (cor.charAt(0) - '0' + size > 9) {
                cor = fix(cor, 0, size);
            }
        } else if (cor.charAt(1) - 'A' + size > 9) {
            cor = fix(cor, 1, size);
        }
        boolean collision = false;

        for (int i = 0; i < size; i++) {
            if (cor.charAt(2) == 'V')
                collision = boards[player][0][cor.charAt(0) - '0' + i][cor.charAt(1) - 'A'] != '~';
            else
                collision = boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A' + i] != '~';
            if (collision) break;

        }
        if (collision) boat(player, type, boards, difficulty);
        else {
            for (int i = 0; i < size; i++) {
                if (cor.charAt(2) == 'V')
                    boards[player][0][cor.charAt(0) - '0' + i][cor.charAt(1) - 'A'] = type.charAt(2);
                else boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A' + i] = type.charAt(2);
            }
        }
    }

    public static void turn(int player, char[][][][] boards,String[] hit) {
        clear();
        display(player,0, boards);
        display(player,1, boards);
        int rival = 0;
        if (player==0) rival=1;

        if (!hit[player].equals("")) System.out.println("HIT! you hit a "+hit[player]+"!");
        if (!hit[rival].equals("")) System.out.println("Your "+hit[rival]+" got hit!");
        System.out.print("Player " + (player + 1) + ", where do you shoot?: ");
        String cor = askCor();
        if (cor.charAt(0) > '9') {
            cor = swap(cor);
        }
        if (!((cor.charAt(0) >= '0' && cor.charAt(0) <= '9')
                && (cor.charAt(1) >= 'A' && cor.charAt(1) <= 'J'))) {
            turn(player, boards,hit);
        }
        if (boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == '~') hit[player] = torpedo(player,cor,boards);
        else turn(player,boards,hit);
    }

    public static String torpedo(int player, String cor,char[][][][] boards) {
        String hit = "";
        if (boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == '~') {
            hit = "";
            boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = '#';
            if (player == 0) boards[1][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = '#';
            else boards[0][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = '#';
        }
        else {
            if (boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 'r') hit = "carrier";
            if (boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 't') hit = "battleship";
            if (boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 'u') hit = "cruiser";
            if (boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 'b') hit = "submarine";
            if (boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 's') hit = "destroyer";
            boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = 'X';
            if (player == 0) boards[1][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = 'X';
            else boards[0][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = 'X';
        }
        return hit;
    }

    public static void display(int player, int type, char[][][][] boards) {
        System.out.println("P" + (player + 1) + "  A  B  C  D  E  F  G  H  I  J  P" + (player + 1));
        for (int i = 0; i < boards[0][0].length; i++) {
            System.out.print(" " + i + " ");
            for (int j = 0; j < boards[0][0][0].length; j++) {
                System.out.print(" " + boards[player][type][i][j] + " ");
            }
            System.out.println(" " + i + " ");
        }
        System.out.println("P" + (player + 1) + "  A  B  C  D  E  F  G  H  I  J  P" + (player + 1));
        System.out.println();
    }

    public static String swap(String swp) {
        char[] temp = swp.toCharArray();
        char temp2 = temp[0];
        temp[0] = temp[1];
        temp[1] = temp2;
        swp = new String(temp);
        return swp;
    }

    public static String fix(String fx, int ch, int size) {
        char[] temp = fx.toCharArray();
        if (ch == 0) temp[0] = (char) ('9' - (size - 1));
        else temp[1] = (char) ('J' - (size - 1));
        fx = new String(temp);
        return fx;
    }

    public static String botPlace() {
        char[] auto = new char[3];
        auto[0] = (char) ('0' + Math.random() * 8);
        auto[1] = (char) ('A' + Math.random() * 8);
        char vh;
        if ((int) (Math.random() * 2) == 0) vh = 'V';
        else vh = 'H';
        auto[2] = vh;
        return new String(auto);
    }
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
