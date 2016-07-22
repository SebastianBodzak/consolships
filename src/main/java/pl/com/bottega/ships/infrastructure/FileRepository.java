package pl.com.bottega.ships.infrastructure;

import pl.com.bottega.ships.domain.Board;
import pl.com.bottega.ships.domain.Repository;

/**
 * Created by Dell on 2016-07-19.
 */
public class FileRepository implements Repository {

    private static final String TEMP_FILE_PATH = "tmp/board.csv";

    public void save(Board board) {

    }

    public Board load(String number) {
        return null;
    }
}
