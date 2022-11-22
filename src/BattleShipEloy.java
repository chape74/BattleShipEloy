public class BattleShipEloy {
    public static void main(String[] args) {
        char[][][][] boards = new char[2][2][10][10];
        String[] hit = {"", ""};
        boolean victory = false;
        int turn = 0, difficulty = 0, gameMode = 0;
        waterBoard(boards);

        while (gameMode != 1 && gameMode != 2) {
            gameMode = Menus.mainMenu();
            if (gameMode == 3) System.out.println();
        }

        if (gameMode == 2) difficulty = Menus.difMenu();

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
