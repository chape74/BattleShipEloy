public class BattleShipEloy {
    public static void main(String[] args) {
        char[][][][] boards = new char[2][2][10][10];
        String[] hit = {"", ""};
        boolean victory = false;
        int turn = 0, difficulty = 0, gameMode = 0;
        waterBoard(boards);

        while (gameMode != 1 && gameMode != 2) {
            gameMode = mainMenu();
            if (gameMode == 3) System.out.println();
        }

        if (gameMode == 2) difficulty = difMenu();

        PlaceStage.placeBoats(boards, difficulty);

        while (!victory) {
            ShootStage.turn(turn, boards, hit, difficulty);
            victory = check(boards);
            if (turn == 0) turn = 1;
            else turn = 0;
        }

        Screen.clear();
        Screen.display(turn, 1, boards);
        System.out.println("Player " + (turn + 1) + " Won!");

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

    public static int mainMenu() {
        String selection;
        Screen.clear();
        System.out.println("**************************");
        System.out.println("* Welcome to BattleShip! *");
        System.out.println("* Select a game mode:    *");
        System.out.println("* 1- Player vs Player    *");
        System.out.println("* 2- Player vs Computer  *");
        System.out.println("* 3- Rules               *");
        System.out.println("**************************");
        System.out.println();
        System.out.print("Select 1 to 3: ");
        selection = Scans.askMenu();
        if (Integer.parseInt(selection) > 3 || Integer.parseInt(selection) < 1) {
            return mainMenu();
        }
        return Integer.parseInt(selection);
    }

    public static int difMenu() {
        String selection;
        Screen.clear();
        System.out.println("**************************");
        System.out.println("* Select difficulty:     *");
        System.out.println("* 1- Easy                *");
        System.out.println("* 2- Normal              *");
        System.out.println("* 3- Hard                *");
        System.out.println("**************************");
        System.out.println();
        System.out.print("Select 1, 2 or 3: ");
        selection = Scans.askMenu();
        if (Integer.parseInt(selection) > 3 || Integer.parseInt(selection) < 1) {
            return difMenu();
        }
        return Integer.parseInt(selection);
    }


    public static boolean check(char[][][][] boards) {
        int[][] hp = new int[2][5];
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[0][0].length; j++) {
                for (int k = 0; k < boards[0][0][0].length; k++) {
                    if (boards[i][0][j][k] == 'r') hp[i][0]++;
                    if (boards[i][0][j][k] == 't') hp[i][1]++;
                    if (boards[i][0][j][k] == 'u') hp[i][2]++;
                    if (boards[i][0][j][k] == 'b') hp[i][3]++;
                    if (boards[i][0][j][k] == 's') hp[i][4]++;
                }
            }
        }
        int num1 = 0, num2 = 0;

        for (int i = 0; i < hp[0].length; i++) {
            num1 += hp[0][i];
        }
        for (int i = 0; i < hp[0].length; i++) {
            num2 += hp[1][i];
        }
        return num1 == 0 || num2 == 0;
    }



}
