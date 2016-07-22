package pl.com.bottega.ships.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dell on 2016-07-19.
 */
public class AIPlayer implements Player {

    private String name;
    private Board board;
    private Board enemyBoard;
    private String lastShot;
    private boolean hitAndSink;
    private Collection listOfShots = new LinkedList();

    public AIPlayer(String name, Board board, Board enemyBoard) {
        this.board = board;
        this.enemyBoard = enemyBoard;
        this.name = name;
    }

    public void shoot(String shot) {
        if (lastShot == null)
            firstShot();
        else
            checkPossibilities();
        listOfShots.add(lastShot);
    }

    private void firstShot() {
        lastShot = "" + generateRandom(65, 74) + generateRandom(48, 57) + "";
    }

    private void checkPossibilities() {
//        if (enemyBoard.checkTile(lastShot) == 'X' && hitAndSink == false) {
//            if (enemyBoard.checkLeftSide('~', lastShot)) {
//                int[] data = enemyBoard.getDataOfLeftSide(lastShot);
//
//            }
//
//        }

        do {lastShot = "" + generateRandom(65, 74) + generateRandom(48, 57) + "";}
        while (listOfShots.contains(lastShot) || enemyBoard.checkTile(lastShot) != '~');
    }

//    48-57 = (0-9); 65-74 = (A-J)
    private char generateRandom(int minValue, int maxValue) {
        Random random = new Random();
        int r = random.nextInt((maxValue - minValue) + 1) + minValue;
        return (char) r;
    }

    public boolean takeShot(String lastEnemyShot) {
        return board.takeShot(lastEnemyShot);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public String getLastShot() {
        return lastShot;
    }

    public void setHitAndSink(boolean hitAndSink) {
        this.hitAndSink = hitAndSink;
    }
}
