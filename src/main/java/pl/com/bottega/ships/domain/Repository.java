package pl.com.bottega.ships.domain;

/**
 * Created by Dell on 2016-07-19.
 */
public interface Repository {

    void save(Board board);
    Board load(String number);
}
