package pl.com.bottega.ships.api;

import pl.com.bottega.ships.domain.Board;
import pl.com.bottega.ships.domain.Initializer;
import pl.com.bottega.ships.domain.Player;

/**
 * Created by Dell on 2016-07-19.
 */
public class GameManager {

    private PlayerManager playerManager = new PlayerManager();
    private Initializer initializer = new Initializer();

    public Board createOwnPlayerBoard() {
        return initializer.initRandomBoardWithShips();
    }

    public Board createAIPlayerBoard() {
        return initializer.initBlankBoard();
    }

    public Board createOwnAIBoard() {
        return initializer.initRandomBoardWithShips();
    }

    public Board createPlayerAIBoard() {
        return initializer.initBlankBoard();
    }

}
