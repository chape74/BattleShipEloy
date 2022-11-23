public class Screen {
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

    //Prints a lot of empty lines.
    public static void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
