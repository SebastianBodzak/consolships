package pl.com.bottega.ships.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dell on 2016-07-21.
 */
public class BoardTest {

    @Test
    public void checkIfShotIsCorrect() {
        Board board = new Board(new char[1][]);
        String shot = "A7";

        boolean correct = board.checkIfShotIsCorrect(shot);

        Assert.assertTrue(correct);
    }

    @Test
    public void checkIfShotIsIncorrectBecauseOfLetter() {
        Board board = new Board(new char[1][]);
        String shot = "k1";

        boolean incorrect = board.checkIfShotIsCorrect(shot);

        Assert.assertFalse(incorrect);
    }

    @Test
    public void checkIfShotIsIncorrectBecauseOfNumber() {
        Board board = new Board(new char[1][]);
        String shot = "al";

        boolean incorrect = board.checkIfShotIsCorrect(shot);

        Assert.assertFalse(incorrect);
    }

    @Test
    public void checkIfShotIsIncorrectBecauseOfLength() {
        Board board = new Board(new char[1][]);
        String shot = "a10";

        boolean incorrect = board.checkIfShotIsCorrect(shot);

        Assert.assertFalse(incorrect);
    }

    @Test
    public void checkNeighberhoodChecking() {
        char[][] grid = new char[10][10];
        Board board = new Board(grid);
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
                grid[i][j] = '~';
        grid[5][5] = 'U';

        boolean checking = board.checkNeighbourhood('U', 5, 5);

        Assert.assertFalse(checking);
    }

    @Test
    public void checkNeighberhoodFailedTest() {
        char[][] grid = new char[10][10];
        Board board = new Board(grid);
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
                grid[i][j] = '~';
        grid[5][5] = 'U';
        grid[5][6] = 'U';

        boolean checking = board.checkNeighbourhood('U', 5, 5);

        Assert.assertTrue(checking);
    }
}
