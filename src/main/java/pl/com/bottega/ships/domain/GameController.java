package pl.com.bottega.ships.domain;

/**
 * Created by Dell on 2016-07-19.
 */
public class GameController {

    private Player humanPlayer;
    private Player aIPlayer;
    private boolean playerTurn;
    private boolean successfulHit;

    public GameController(Player humanPlayer, Player aIPlayer) {
        this.humanPlayer = humanPlayer;
        this.aIPlayer = aIPlayer;
    }

    public void chooseWhoIsFirst(String name) {
        playerTurn = name.toLowerCase().equals(humanPlayer.getName().toLowerCase());
    }

    public boolean aIPlayerShoot() {
        aIPlayer.shoot("");
        successfulHit = humanPlayer.takeShot(aIPlayer.getLastShot());
        if (!successfulHit)
            changeTurn();
        aIPlayer.getEnemyBoard().markEmptyBoard(aIPlayer.getLastShot(), successfulHit, humanPlayer.getBoard().getHitAndSink());
        aIPlayer.setHitAndSink(humanPlayer.getBoard().getHitAndSink());
        return aIPlayer.getEnemyBoard().getVictory();
    }

    public boolean humanPlayerShoot(String shot) throws IllegalArgumentException {
        humanPlayer.shoot(shot);
        successfulHit = aIPlayer.takeShot(humanPlayer.getLastShot());
        if (!successfulHit)
            changeTurn();
        humanPlayer.getEnemyBoard().markEmptyBoard(humanPlayer.getLastShot(), successfulHit, aIPlayer.getBoard().getHitAndSink());
        return humanPlayer.getEnemyBoard().getVictory();
    }

    public void changeTurn() {
        playerTurn = !playerTurn;
    }

    public String showStatistics() {
        Board board = humanPlayer.getEnemyBoard();
        Board board1 = aIPlayer.getEnemyBoard();
        return "Statistics: \n" + humanPlayer.getName() + ": hits - " + board.getHits() + ", missed - " + board.getMissed() + ", shots - " + board.getShots()
                + "\n" + aIPlayer.getName() + ": hits - " + board1.getHits() + ", missed - " + board1.getMissed() + ", shots - " + board1.getShots();
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public Player getAIPlayer() {
        return aIPlayer;
    }

    public boolean getPlayerTurn() {
        return playerTurn;
    }

    public boolean getSuccessfulHit() {
        return successfulHit;
    }

}
