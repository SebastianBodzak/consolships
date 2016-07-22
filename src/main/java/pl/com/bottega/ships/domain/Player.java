package pl.com.bottega.ships.domain;

import java.util.Collection;

/**
 * Created by Dell on 2016-07-19.
 */
public interface Player {

    void shoot(String shot);
    boolean takeShot(String lastShot);
    public String getName();
    public Board getBoard();
    public Board getEnemyBoard();
    public String getLastShot();
    public void setHitAndSink(boolean hitAndSink);
}
