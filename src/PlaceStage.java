/**
 * The first stage of BattleShip, place the boats
 */
public class PlaceStage {
    /**
     * The boats that are added to the water field.
     *
     * @param boards     the boards and coordinates.
     * @param difficulty the difficulty of the bot.
     */
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

    /**
     * Switch to determine the size of the boats.
     *
     * @param type is the boat type.
     * @return the size of said boat.
     */
    public static int boatType(String type) {
        switch (type) {
            case "carrier":
                return 5;
            case "battleship":
                return 4;
            case "cruiser":
            case "submarine":
                return 3;
            case "destroyer":
                return 2;
        }
        return 0;
    }

    /**
     * Shows up the water board and asks where to place the boats.
     *
     * @param player     the turn of the player
     * @param type       is the boat type.
     * @param boards     the boards and coordinates.
     * @param difficulty if greater than 0 means that is a bot.
     */
    public static void boat(int player, String type, char[][][][] boards, int difficulty) {

        //Prepares the screen layout.
        Screen.clear();
        Screen.display(player, 0, boards);
        System.out.print("Player " + (player + 1) + ", place your " + type + ": ");

        //Ask coordinates, or the bot.
        String cor;
        if (player == 1 && difficulty > 0) {
            cor = Bot.place();
            System.out.println(cor);
        } else cor = Scans.askCor();

        int size = boatType(type);

        //Check if the boats got placed out of bounds.
        if (cor.charAt(2) == 'V') {
            if (cor.charAt(0) - '0' + size > 9) {
                cor = fix(cor, false, size);
            }
        } else if (cor.charAt(1) - 'A' + size > 9) {
            cor = fix(cor, true, size);
        }

        //Check if a boat segment are colliding with another already placed segment, if so,
        //asks to try again to place said boat.
        boolean collision;
        for (int i = 0; i < size; i++) {
            if (cor.charAt(2) == 'V')
                collision = boards[player][0][cor.charAt(0) - '0' + i][cor.charAt(1) - 'A'] != '~';
            else if (cor.charAt(2) == 'H')
                collision = boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A' + i] != '~';
            else collision = true;
            if (collision) {
                boat(player, type, boards, difficulty);
                return;
            }
        }

        //Placing said boat Vertically o Horizontally.
        for (int i = 0; i < size; i++) {
            if (cor.charAt(2) == 'V')
                boards[player][0][cor.charAt(0) - '0' + i][cor.charAt(1) - 'A'] = type.charAt(2);
            else boards[player][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A' + i] = type.charAt(2);
        }
    }

    /**
     * Corrects the coordinates to place them back correctly.
     *
     * @param fx   takes the cords string.
     * @param vh   Vertical if false, Horizontal if true.
     * @param size the boat size.
     * @return the fixed cords
     */
    public static String fix(String fx, boolean vh, int size) {
        char[] temp = fx.toCharArray();
        if (vh) temp[0] = (char) ('9' - (size - 1));
        else temp[1] = (char) ('J' - (size - 1));
        fx = new String(temp);
        return fx;
    }

}
