public class Menus {
    //Main menu with rules
    public static int mainMenu() {
        String selection;
        Screen.clear();
        System.out.println("* * * * * * * * * * * * * * * * *");
        System.out.println("*    Welcome to BattleShip!     *");
        System.out.println("* * * * * * * * * * * * * * * * *");
        System.out.println("* Rules:                        *");
        System.out.println("* Place your boats using:       *");
        System.out.println("* 0-9/A-J/H-V (J1H or 2cv...)   *");
        System.out.println("* Vertical if third char empty. *");
        System.out.println("* Shoot enemy boats using:      *");
        System.out.println("* 0-9/A-J (A9 or 1b...)         *");
        System.out.println("* Scroll screen to debug/cheat. *");
        System.out.println("* * * * * * * * * * * * * * * * *");
        System.out.println("* Select a game mode:           *");
        System.out.println("* 1- Player vs Player.          *");
        System.out.println("* 2- Player vs Computer.        *");
        System.out.println("* * * * * * * * * * * * * * * * *");
        System.out.println();
        System.out.print("Select 1 to 2: ");
        selection = Scans.askMenu();
        if (Integer.parseInt(selection) > 3 || Integer.parseInt(selection) < 1) {
            return mainMenu();
        }
        return Integer.parseInt(selection);
    }

    //Difficulty menu
    public static int difMenu() {
        String selection;
        Screen.clear();
        System.out.println("* * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* Select difficulty:                    *");
        System.out.println("* 1- Easy.                              *");
        System.out.println("* Randomized shoots.                    *");
        System.out.println("* 2- Normal.                            *");
        System.out.println("* Randomized shoots, chance of AimBot.  *");
        System.out.println("* 3- Hard.                              *");
        System.out.println("* Higher chances, improved pattern.     *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * *");
        System.out.println();
        System.out.print("Select 1, 2 or 3: ");
        selection = Scans.askMenu();
        if (Integer.parseInt(selection) > 3 || Integer.parseInt(selection) < 1) {
            return difMenu();
        }
        return Integer.parseInt(selection);
    }
}
