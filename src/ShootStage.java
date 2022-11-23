public class ShootStage {

    //Beginning of each turn, showing up the board of shoots and boats, and asking where to shoot a torpedo.
    public static void turn(int player, char[][][][] boards, String[] hit, int difficulty) {

        //Prepares the screen layout.
        Screen.clear();
        System.out.println("        *  OUR SEA FIELD  *");
        Screen.display(player, 0, boards);
        System.out.println("        * ENEMY SEA FIELD *");
        Screen.display(player, 1, boards);

        //Rival is the reverse of the player
        int rival = 0;
        if (player == 0) rival = 1;

        //Displays if player got hit or hit something.
        if (!hit[player].equals("")) System.out.println("HIT! you hit a " + hit[player] + "!");
        if (!hit[rival].equals("")) System.out.println("Your " + hit[rival] + " got hit!");

        //Ask coordinates, or the bot.
        System.out.print("Player " + (player + 1) + ", where do you shoot?: ");
        String cor;
        if (player == 1 && difficulty > 0) {
            cor = Bot.shoot(difficulty,boards);
            System.out.println(cor);
        } else cor = Scans.askCor();

        //Checks if a place was already picked and asks for correction if so.
        if (boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == '~')
            hit[player] = torpedo(player, cor, boards);
        else turn(player, boards, hit, difficulty);
    }

    //Check if the coordinates hit a boat or water, if got boat, saves the type of boat that got hit.
    public static String torpedo(int player, String cor, char[][][][] boards) {
        String hit = "";
        int rival = 0;
        if (player == 0) rival = 1;
        if (boards[rival][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == '~') {
            hit = "";
            boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = '|';
            if (player == 0) boards[1][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = '|';
            else boards[0][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = '|';
        } else {
            if (boards[rival][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 'r') hit = "carrier";
            if (boards[rival][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 't') hit = "battleship";
            if (boards[rival][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 'u') hit = "cruiser";
            if (boards[rival][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 'b') hit = "submarine";
            if (boards[rival][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == 's') hit = "destroyer";
            boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = 'X';
            if (player == 0) boards[1][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = 'X';
            else boards[0][0][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] = 'X';
        }
        return hit;
    }
}
