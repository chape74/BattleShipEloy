/**
 * This class contains the instructions to replace the actions of the second player if "player vs computer" has been chosen.
 */
public class Bot {
    /**
     * Asks the bot for the coordinates for the Place Stage.
     *
     * @return the randomized cords and orientation.
     */
    public static String place() {
        char[] auto = new char[3];
        auto[0] = (char) ('0' + Math.random() * 8);
        auto[1] = (char) ('A' + Math.random() * 8);
        char vh;
        if ((int) (Math.random() * 2) == 0) vh = 'V';
        else vh = 'H';
        auto[2] = vh;
        return new String(auto);
    }

    /**
     * Asks the bot for the coordinates for the Shoot Stage, with some variations depending on difficulty.
     *
     * @param difficulty the difficulty of the bot.
     * @param boards     the boards and coordinates.
     * @return the randomized cords.
     */
    public static String shoot(int difficulty, char[][][][] boards) {
        char[] auto = new char[2];
        auto[0] = (char) ('0' + Math.random() * 10);
        auto[1] = (char) ('A' + Math.random() * 10);

        //Hard difficulty will pick be forced to pick even coordinates.
        if (difficulty == 3) {
            if ((auto[0] + auto[1]) % 2 == 0)
                return shoot(difficulty, boards);
        }

        //Normal and hard will have forced chances to hit.
        double aimBot = Math.random();
        if (aimBot > (1 - (0.06 * difficulty)) && difficulty >= 2) {
            Loop:
            for (int j = 0; j < boards[0][0].length; j++) {
                for (int k = 0; k < boards[0][0][0].length; k++) {
                    if (boards[0][0][j][k] != '~' &&
                            boards[0][0][j][k] != '|' &&
                            boards[0][0][j][k] != 'X') {
                        auto[0] = (char) ('0' + j);
                        auto[1] = (char) ('A' + k);
                        System.out.print("aimBot triggered on ");
                        break Loop;
                    }
                }
            }
        }
        return new String(auto);
    }
}
