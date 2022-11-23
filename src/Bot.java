public class Bot {
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

    public static String shoot(int difficulty, char[][][][] boards) {
        char[] auto = new char[2];
        auto[0] = (char) ('0' + Math.random() * 10);
        auto[1] = (char) ('A' + Math.random() * 10);
        if (difficulty == 3) {
            if ((auto[0] + auto[1]) % 2 == 0)
                return shoot(difficulty, boards);
        }
        double aimBot = Math.random();
        if (aimBot > (1 - (0.05 * difficulty)) && difficulty >= 2) {
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
