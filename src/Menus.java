public class Menus {
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
}
