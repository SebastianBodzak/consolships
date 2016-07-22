package pl.com.bottega.ships.domain;

import java.util.Random;

/**
 * Created by Dell on 2016-07-19.
 */
public class Initializer {

    private char[][] grid = new char[12][12];
    private static final int AIRCRAFT_CARRIER_LENGTH = 4;
    private static final int BATTLESHIP_LENGTH = 3;
    private static final int DESTROYER_LENGTH = 2;
    private static final int SUBMARINE_LENGTH = 1;

    public Initializer() {
    }

    public Board initBlankBoard() {

        labelGrid();
        createSea();
        char[][] newGrid = gridFactory(grid);
        return new Board(newGrid);
    }

    public Board initRandomBoardWithShips() {
        labelGrid();
        createSea();
        initRandomShips();
        char[][] newGrid = gridFactory(grid);
        return new Board(newGrid);
    }

    public static char[][] gridFactory(char[][] grid) {
        char[][] newGrid = new char[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }

    private void createSea() {
        for(char row = 1; row <= 10; row++)
            for (char column = 1; column <= 10; column++)
                grid[row][column] = '~';
    }

    private void initRandomShips() {
        initAircraft();
        for (int i = 1; i <=2; i++)
            initBattleShip();
        for (int i = 1; i <=3; i++)
            initDestroyer();
        for (int i = 1; i <=4; i++)
            initSubmarine();
    }

    private void initAircraft() {
        boolean verticallyPosition = randomBoolean();
        if (verticallyPosition) {
            int row = generateRandom(1, 7);
            int column = generateRandom();
            grid[row][column] = 'U';
            fillGrid(3, row, column, verticallyPosition);
        } else {
            int row = generateRandom();
            int column = generateRandom(1, 7);
            grid[row][column] = 'U';
            fillGrid(3, row, column, verticallyPosition);
        }
    }

    private void initBattleShip() {
        boolean verticallyPosition = randomBoolean();
        int[] array = checkPossibilities(verticallyPosition, 8, BATTLESHIP_LENGTH);
        if (verticallyPosition) {
            grid[array[0]][array[1]] = 'U';
            fillGrid(2, array[0], array[1], verticallyPosition);
        } else {
            grid[array[0]][array[1]] = 'U';
            fillGrid(2, array[0], array[1], verticallyPosition);
        }
    }

    private void initDestroyer() {
        boolean verticallyPosition = randomBoolean();
        int[] array = checkPossibilities(verticallyPosition, 9, DESTROYER_LENGTH);
        if (verticallyPosition) {
            grid[array[0]][array[1]] = 'U';
            fillGrid(1, array[0], array[1], verticallyPosition);
        } else {
            grid[array[0]][array[1]] = 'U';
            fillGrid(1, array[0], array[1], verticallyPosition);
        }
    }

    private void initSubmarine() {
        int[] array = checkPossibilities(true, 10, SUBMARINE_LENGTH);
            grid[array[0]][array[1]] = 'U';
    }

    private int[] checkPossibilities(boolean verticallyPosition, int lastRowOrColumn, int shipLength) {
        int[] array = new int[2];
        boolean continuation = true;

        do {
            if (verticallyPosition) {
                int row = generateRandom(1, lastRowOrColumn);
                int column = generateRandom();
                    if (checkIfAvailable(row, column, verticallyPosition, shipLength)) {
                        array[0] = row;
                        array[1] = column;
                        continuation = false;
                    }
            } else {
                int row = generateRandom();
                int column = generateRandom(1, lastRowOrColumn);
                    if (checkIfAvailable(row, column, verticallyPosition, shipLength)) {
                        array[0] = row;
                        array[1] = column;
                        continuation = false;
                    }
            }
        } while (continuation);
        return array;
    }

    private boolean checkIfAvailable(int potentialRow, int potentialColumn, boolean verticallyPosition, int shipLength) {
        if (verticallyPosition) {
            for (int i = 0, row = potentialRow; i < shipLength; i++, row++) {
                if (checkIfWouldHaveNeighbours(row, potentialColumn))
                    return false;
            }
            return true;
        } else {
            for (int i = 0, column = potentialColumn; i < shipLength; i++, column++) {
                if (checkIfWouldHaveNeighbours(potentialRow, column))
                    return false;
            }
            return true;
        }
    }

    private boolean checkIfWouldHaveNeighbours(int row, int column) {
        return grid[row][column] == 'U' || grid[row - 1][column - 1] == 'U' || grid[row - 1][column] == 'U' || grid[row - 1][column + 1] == 'U'
                || grid[row][column - 1] == 'U' || grid[row][column + 1] == 'U' || grid[row + 1][column - 1] == 'U' || grid[row + 1][column] == 'U' || grid[row + 1][column + 1] == 'U';
    }

    private void fillGrid(int shipLengthWithoutStartedPoint, int row, int column, boolean fillSide) {
        for (int i = 1; i <= shipLengthWithoutStartedPoint; i++) {
            if (fillSide)
                grid[row + i][column] = 'U';
            else
                grid[row][column + i] = 'U';
            }
    }

    private Boolean randomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private int generateRandom() {
        Random random = new Random();
        return random.nextInt((10 - 1) + 1) + 1;
    }

    private int generateRandom(int minValue, int maxValue) {
        Random random = new Random();
        return random.nextInt((maxValue - minValue) + 1) + minValue;
    }

    private void labelGrid() {
        for(char row = 1, number = '0'; row <= 10; row++, number++)
            grid[row][0] = number;

        for(char row = 1, number = '0'; row <= 10; row++, number++)
            grid[row][11] = number;

        for (char i = 1, column = 'A'; column <= 'J'; i++, column++)
                grid[0][i] = column;

        for (char i = 1, column = 'A'; column <= 'J'; i++, column++)
                grid[11][i] = column;
    }
}