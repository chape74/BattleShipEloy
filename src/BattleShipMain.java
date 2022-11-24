/**
 * Main class for the steps of each stage of the BattleShip game and some uncategorized methods.
 */
public class BattleShipMain {
    public static void main(String[] args) {

        //The boards array consists of:
        //1st dimension: Player number.
        //2nd dimension: Board 0 is boats, 1 is shoots.
        //3rd and 4th are the 0-9 and A-J coordinates of each board.
        char[][][][] boards = new char[2][2][10][10];
        waterBoard(boards);

        //Other variables for later on.
        String[] hit = {"", ""};
        boolean victory = false;
        int turn = 1, difficulty = 0, gameMode = 0;

        //Menu Stage, showing difficulty menu if player vs computer has been chosen.
        while (gameMode != 1 && gameMode != 2) {
            gameMode = Menus.mainMenu();
        }
        if (gameMode == 2) difficulty = Menus.difMenu();

        //Boat placement stage.
        PlaceStage.placeBoats(boards, difficulty);

        //Loops the shoot stage till victory condition is met.
        while (!victory) {
            if (turn == 0) turn = 1;
            else turn = 0;
            ShootStage.turn(turn, boards, hit, difficulty);
            victory = check(boards);

        }

        //Clears the screen, shows the final result, and declares the victory of the last player shoot.
        Screen.clear();
        Screen.display(turn, 1, boards);
        System.out.println("Player " + (turn + 1) + " Won!");

    }

    /**
     * Fills the array with Water chars.
     *
     * @param boards the boards and coordinates.
     */
    public static void waterBoard(char[][][][] boards) {
        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[0].length; j++) {
                for (int k = 0; k < boards[0][0].length; k++) {
                    for (int l = 0; l < boards[0][0][0].length; l++) {
                        boards[i][j][k][l] = '~';
                    }
                }
            }
        }
    }

    /**
     * Check victory condition, when a player has no segments from any boat.
     *
     * @param boards the boards and coordinates.
     * @return the victory condition is met, when a player has 0 total hp on their boats.
     */
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
