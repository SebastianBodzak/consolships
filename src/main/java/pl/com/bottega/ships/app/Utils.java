package pl.com.bottega.ships.app;

import pl.com.bottega.ships.domain.Board;
import pl.com.bottega.ships.domain.GameController;
import pl.com.bottega.ships.domain.Player;

/**
 * Created by Dell on 2016-07-21.
 */
public class Utils {

    public static void showEnthusiasticGreeting(Player player) {
        System.out.println("Heeeeeloooo " + player.getName() + "!");
    }

    public static void informAboutNewBoard(Player player) {
        System.out.println("\nThis is your board:");
        showBoard(player.getBoard());
    }

    public static void showBoard(Board board) {
        char[][] grid = board.getGrid();
        for (int row = 0; row <= 11; row++) {
            for (int column = 0; column <= 11; column++)
                System.out.print(grid[row][column] + " ");
            System.out.println();
        }
    }

    public static void chooseWhoIsFirst(String name, GameController gc) {
        gc.chooseWhoIsFirst(name);
    }

    public static void askWhoShouldPlayFirst() {
        System.out.println("\nWho should start first? (type your or AI Player name and press enter)");
    }

    public static boolean aiShoot(GameController gc) {
        boolean victory = gc.aIPlayerShoot();
        System.out.println("Player " + gc.getAIPlayer().getName() + " shot at " + gc.getAIPlayer().getLastShot());
        System.out.println("This is your board:");
        showBoard(gc.getHumanPlayer().getBoard());
        if (gc.getSuccessfulHit() && !gc.getHumanPlayer().getBoard().getHitAndSink())
            System.out.println("\n" + gc.getAIPlayer().getName() + " has infected your sailors by Pokemon Go. The disease has spread.");
        if (gc.getHumanPlayer().getBoard().getHitAndSink())
            System.out.println("\nThe Apocalypse is near, human");
        return victory;
    }

    public static boolean playerShoot(String shot, GameController gc) throws IllegalArgumentException {
        boolean victory = gc.humanPlayerShoot(shot);
        showBoard(gc.getHumanPlayer().getEnemyBoard());
        if (gc.getSuccessfulHit() && !gc.getAIPlayer().getBoard().getHitAndSink())
            System.out.println("\nYou hit the ship and murdered innocent people!");
        if (gc.getAIPlayer().getBoard().getHitAndSink())
            System.out.println("\nThe ship is sunk and there is no hope for all sailors, psychopath. You might have killed Popeye, also");
        return victory;
    }

    public static void askShotParameters() {
        System.out.println("\nChoose field which you would like to strike (between A-J and 0-9, e.g. B0 or J9):");
    }

    public static void askIfReady() {
        System.out.println("\nAre you ready for next turn? (type sth and press enter)");
    }

    public static void endGame() {
        System.out.println("\nThanks for play!");
    }

    public static void showStatistics(GameController gc) {
        System.out.println(gc.showStatistics());
    }

    public static boolean playerWasSuccessful(GameController gc) {
        return gc.getSuccessfulHit();
    }
}
