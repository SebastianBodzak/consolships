package pl.com.bottega.ships.domain;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Dell on 2016-07-19.
 */
public class HumanPlayer implements Player {

    private String name;
    private Board board;
    private Board enemyBoard;
    private String lastShot;
    private Collection listOfShots = new LinkedList();
    private boolean hitAndSink;

    public HumanPlayer(String name, Board board, Board enemyBoard) {
        this.name = name;
        this.board = board;
        this.enemyBoard = enemyBoard;
    }

    public void shoot(String shot) throws IllegalArgumentException {
        board.checkShot(shot);
        lastShot = shot;
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

    public Collection getListOfShots() {
        return listOfShots;
    }
}
