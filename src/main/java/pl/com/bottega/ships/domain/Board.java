package pl.com.bottega.ships.domain;

import com.google.common.base.Preconditions;

/**
 * Created by Dell on 2016-07-19.
 */
public class Board {

    private static final int VICTORY_ALL_HITS = 20;
    private boolean victory;
    private char[][] grid;
    private int shots;
    private int missed;
    private int hits;
    private boolean hitAndSink;

    public Board(char[][] grid) {
        this.grid = grid;
    }

    public void checkShot(String shot) throws IllegalArgumentException {
        Preconditions.checkNotNull(shot);
        Preconditions.checkArgument(checkIfShotIsCorrect(shot), "You typed wrong!");
    }

    public boolean checkNeighbourhood(char field, int row, int column) {
        return grid[row - 1][column] == field || grid[row][column - 1] == field || grid[row][column + 1] == field || grid[row + 1][column] == field;
    }

    public boolean checkNeighbourhood(char field, String data) {
        int[] dataOfShot = parseShot(data);
        int row = dataOfShot[0];
        int column = dataOfShot[1];
        return grid[row - 1][column] == field || grid[row][column - 1] == field || grid[row][column + 1] == field || grid[row + 1][column] == field;
    }

    private void markHit(int row, int column) {
        grid[row][column] = 'X';
        if (hits == VICTORY_ALL_HITS)
            victory = true;
    }

    private void markMissed(int row, int column) {
        grid[row][column] = '*';
    }

    public boolean takeShot(String shot) {
        hitAndSink = false;
        int[] dataOfShot = parseShot(shot);
        int row = dataOfShot[0];
        int column = dataOfShot[1];
        if (grid[row][column] == 'U') {
            markHit(row, column);
            if (!checkNeighbourhood('U', row, column)) {
                if (checkLeftSide('X', row, column) || checkRightSide('X', row, column))
                    hitAndSink = checkHorizontalLine(row, column);;
                if (checkDownSide('X', row, column) || checkUpSide('X', row, column))
                    hitAndSink = checkVerticalLine(row, column);
            }
            if (!(checkNeighbourhood('U', row, column) || checkNeighbourhood('X', row, column)))
                hitAndSink = true;
            return true;
        }
        else {
            markMissed(row, column);
            return false;
        }
    }

    private boolean checkHorizontalLine(int row, int column) {
        for (int loop = 0, c = ++column; loop <= 3; loop++, c++) {
            if (grid[row][c] == 'U')
                return false;
            if (grid[row][c] != 'X')
                break;
        }

        for (int loop = 0, c = --column; loop <= 3 ; loop++, c--) {
            if (grid[row][c] == 'U')
                return false;
            if (grid[row][c] != 'X')
                break;
        }
        return true;
    }

    private boolean checkVerticalLine(int row, int column) {
        for (int loop = 0, r = ++row; loop <= 3; loop++, r++) {
            if (grid[r][column] == 'U')
                return false;
            if (grid[r][column] != 'X')
                break;
        }

        for (int loop = 0, r = --row; loop <=3 ; loop++, r--) {
            if (grid[r][column] == 'U')
                return false;
            if (grid[r][column] != 'X')
                break;
        }
        return true;
    }

    private int parseLetterToInt(String shot) {
        String[] array = "ABCDEFGHIJ".split("");
        int loop = 0;
        String firstLetter = shot.substring(0, 1);
        for (String s : array) {
            loop++;
            if(firstLetter.toLowerCase().equals(s.toLowerCase()))
                break;
        }
        return loop;
    }

    public void markEmptyBoard(String shot, boolean successfulHit, boolean hitAndSink) {
        addShot();
        int[] dataOfShot = parseShot(shot);
        int row = dataOfShot[0];
        int column = dataOfShot[1];
        if (successfulHit) {
            addHits();
            markHit(row, column);
            if (hitAndSink)
                markAroundSinkedShip(row, column);
        }
        else {
            addMissed();
            markMissed(row, column);
        }
    }

    private void markAroundSinkedShip(int row, int column) {
        markSeeAroundOneTile(row, column);
        checkLeftSideForShip(row, column);
        checkRightSideForShip(row, column);
        checkUpSideForShip(row, column);
        checkDownSideForShip(row, column);
    }

    private void checkDownSideForShip(int row, int column) {
        int r = row + 1;

        while (grid[r][column] == 'X') {
            markSeeAroundOneTile(r, column);
            r++;
        }
    }

    private void checkUpSideForShip(int row, int column) {
        int r = row - 1;

        while (grid[r][column] == 'X') {
            markSeeAroundOneTile(r, column);
            r--;
        }
    }

    private void checkRightSideForShip(int row, int column) {
        int c = column + 1;

        while (grid[row][c] == 'X') {
            markSeeAroundOneTile(row, c);
            c++;
        }
    }

    private void checkLeftSideForShip(int row, int column) {
        int c = column - 1;

        while (grid[row][c] == 'X') {
            markSeeAroundOneTile(row, c);
            c--;
        }
    }

    private void markSeeAroundOneTile(int row, int column) {
        for (int i = 0; i <= 2; i++) {
            if (grid[row - 1][column - 1 + i] == '~')
                grid[row - 1][column - 1 + i] = '*';
            if (grid[row + 1][column - 1 + i] == '~')
                grid[row + 1][column - 1 + i] = '*';
            if (grid[row][column - 1 + i] == '~')
                grid[row][column - 1 + i] = '*';
        }
    }

    public boolean checkIfShotIsCorrect(String shot) {
        return (shot.length() == 2 && shot.substring(0, 1).matches("[a-jA-J]") && shot.substring(1).matches("\\d"));
    }

    public void addShot() {
        this.shots++;
    }

    public void addMissed() {
        this.missed++;
    }

    public void addHits() {
        this.hits++;
    }

    public int getShots() {
        return shots;
    }

    public int getMissed() {
        return missed;
    }

    public int getHits() {
        return hits;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean getVictory() {
        return victory;
    }

    public boolean getHitAndSink() {
        return hitAndSink;
    }

    public char checkTile(String data) {
        int[] dataOfShot = parseShot(data);
        switch (grid[dataOfShot[0]][dataOfShot[1]]) {
            case 'U':
                return 'U';
            case '*':
                return '*';
            case 'X':
                return 'X';
            default:
                return '~';
        }
    }

    public int[] parseShot(String shot) {
        return new int[] {Integer.parseInt(shot.substring(1)) + 1, parseLetterToInt(shot)};
    }

    public boolean checkLeftSide(char c, String shot) {
        int[] dataOfShot = parseShot(shot);
        return grid[dataOfShot[0]][dataOfShot[1] - 1] == c;
    }

    public boolean checkLeftSide(char c, int row, int column) {
        int col = --column;
        return grid[row][col] == c;
    }

    public int[] getDataOfLeftSide(String shot) {
        int[] dataOfShot = parseShot(shot);
        return new int[] {dataOfShot[0], dataOfShot[1] - 1};
    }

    public boolean checkRighttSide(char c, String shot) {
        int[] dataOfShot = parseShot(shot);
        return grid[dataOfShot[0]][dataOfShot[1] + 1] == c;
    }

    public boolean checkRightSide(char c, int row, int column) {
        int col = ++column;
        return grid[row][col] == c;
    }


    public int[] getDataOfRightSide(String shot) {
        int[] dataOfShot = parseShot(shot);
        return new int[] {dataOfShot[0], dataOfShot[1] + 1};
    }

    public boolean checkDownSide(char c, String shot) {
        int[] dataOfShot = parseShot(shot);
        return grid[dataOfShot[0] + 1][dataOfShot[1]] == c;
    }

    public boolean checkDownSide(char c, int row, int column) {
        int r = ++row;
        return grid[r][column] == c;
    }


    public int[] getDataOfDownSide(String shot) {
        int[] dataOfShot = parseShot(shot);
        return new int[] {dataOfShot[0] + 1, dataOfShot[1]};
    }

    public boolean checkUpSide(char c, String shot) {
        int[] dataOfShot = parseShot(shot);
        return grid[dataOfShot[0] - 1][dataOfShot[1]] == c;
    }

    public boolean checkUpSide(char c, int row, int column) {
        int r = --row;
        return grid[r][column] == c;
    }


    public int[] getDataOfUpSide(String shot) {
        int[] dataOfShot = parseShot(shot);
        return new int[] {dataOfShot[0] - 1, dataOfShot[1]};
    }
}
