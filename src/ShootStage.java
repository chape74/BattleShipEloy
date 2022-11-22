public class ShootStage {
    public static void turn(int player, char[][][][] boards, String[] hit, int difficulty) {
        Screen.clear();
        Screen.display(player, 0, boards);
        Screen.display(player, 1, boards);
        int rival = 0;
        if (player == 0) rival = 1;

        if (!hit[player].equals("")) System.out.println("HIT! you hit a " + hit[player] + "!");
        if (!hit[rival].equals("")) System.out.println("Your " + hit[rival] + " got hit!");

        System.out.print("Player " + (player + 1) + ", where do you shoot?: ");

        String cor;
        if (player == 1 && difficulty > 0) {
            cor = Bot.shoot(difficulty);
        } else cor = Scans.askCor();

        if (boards[player][1][cor.charAt(0) - '0'][cor.charAt(1) - 'A'] == '~')
            hit[player] = torpedo(player, cor, boards);
        else turn(player, boards, hit, difficulty);
    }

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
