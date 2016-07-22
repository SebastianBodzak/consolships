package pl.com.bottega.ships.domain;

import org.junit.Test;

import java.util.Scanner;

import static pl.com.bottega.ships.app.Utils.aiShoot;
import static pl.com.bottega.ships.app.Utils.askShotParameters;
import static pl.com.bottega.ships.app.Utils.playerShoot;

/**
 * Created by Dell on 2016-07-21.
 */
public class GameControllerTest {


    @Test
    public void sinkShip() {
        Initializer initializer = new Initializer();

        Board board = initializer.initBlankBoard();
        board.getGrid()[3][3] = 'U';
        board.getGrid()[4][3] = 'U';
        board.getGrid()[5][3] = 'U';
        board.getGrid()[6][3] = 'U';

        showBoard(board);

        Player player = new HumanPlayer("Janusz", initializer.initRandomBoardWithShips(), initializer.initBlankBoard());
        Player aIPlayer = new AIPlayer("AI", board, initializer.initBlankBoard());

        GameController gc = new GameController(player, aIPlayer);

        gc.humanPlayerShoot("c2");
        gc.humanPlayerShoot("c3");
        gc.humanPlayerShoot("c4");
        gc.humanPlayerShoot("c5");

        showBoard(gc.getHumanPlayer().getEnemyBoard());
    }

    @Test
    public void sinkShipFromMiddle() {
        Initializer initializer = new Initializer();

        Board board = initializer.initBlankBoard();
        board.getGrid()[3][3] = 'U';
        board.getGrid()[4][3] = 'U';
        board.getGrid()[5][3] = 'U';
        board.getGrid()[6][3] = 'U';

        showBoard(board);

        Player player = new HumanPlayer("Janusz", initializer.initRandomBoardWithShips(), initializer.initBlankBoard());
        Player aIPlayer = new AIPlayer("AI", board, initializer.initBlankBoard());

        GameController gc = new GameController(player, aIPlayer);

        gc.humanPlayerShoot("c3");
        gc.humanPlayerShoot("c4");
        gc.humanPlayerShoot("c5");
        gc.humanPlayerShoot("c2");

        showBoard(gc.getHumanPlayer().getEnemyBoard());
    }

    private static void showBoard(Board board) {
        char[][] grid = board.getGrid();
        for (int row = 0; row <= 11; row++) {
            for (int column = 0; column <= 11; column++)
                System.out.print(grid[row][column] + " ");
            System.out.println();
        }
    }

}
