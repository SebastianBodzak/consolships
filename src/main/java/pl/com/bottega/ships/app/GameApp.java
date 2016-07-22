package pl.com.bottega.ships.app;

import pl.com.bottega.ships.domain.*;

import java.util.Scanner;

import static pl.com.bottega.ships.app.Utils.*;

/**
 * Created by Dell on 2016-07-19.
 */
public class GameApp {
    public static void main(String[] args){

        Initializer initializer = new Initializer();
        Scanner scanner = new Scanner(System.in);
        String placeHolder;

        Player player = new HumanPlayer("DarkLena", initializer.initRandomBoardWithShips(), initializer.initBlankBoard());
        Player aIPlayer = new AIPlayer("EvilCreature", initializer.initRandomBoardWithShips(), initializer.initBlankBoard());

        GameController gc = new GameController(player, aIPlayer);

        showBoard(gc.getAIPlayer().getBoard());

        showEnthusiasticGreeting(gc.getHumanPlayer());
        informAboutNewBoard(gc.getHumanPlayer());
        askWhoShouldPlayFirst();
        chooseWhoIsFirst(scanner.next(), gc);

        boolean victory = false;
        while (!victory) {
            if (!gc.getPlayerTurn()) {
                victory = aiShoot(gc);
                System.out.println();
            } else {
                try {
                    askShotParameters();
                    victory = playerShoot(scanner.next(), gc);
                    if (!playerWasSuccessful(gc)) {
                        askIfReady();
                        scanner.next();
                    }
                } catch (IllegalArgumentException ex) {
                    continue;
                }
            }
        }

        endGame();
        showStatistics(gc);
        System.out.println("Your board");
        showBoard(gc.getHumanPlayer().getBoard());
        System.out.println("\nEnemy board");
        showBoard(gc.getAIPlayer().getBoard());
    }




}
