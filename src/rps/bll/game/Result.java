package rps.bll.game;

// Project imports
import rps.bll.player.IPlayer;

import java.io.Serializable;

/**
 * Defines a Result in the game
 *
 * @author smsj
 */
public class Result implements Serializable {

    private ResultType type;
    private Move winnerMove;
    private IPlayer winnerPlayer;
    private Move loserMove;
    private IPlayer loserPlayer;
    private int roundNumber;


    /**
     * Initializes a new Result with a winner, loser, their moves and a type
     * @param winnerPlayer
     * @param winnerMove
     * @param loserPlayer
     * @param loserMove
     * @param type
     * @param roundNumber
     */
    public Result(IPlayer winnerPlayer, Move winnerMove, IPlayer loserPlayer, Move loserMove, ResultType type, int roundNumber) {
        this.winnerPlayer = winnerPlayer;
        this.winnerMove = winnerMove;
        this.loserPlayer = loserPlayer;
        this.loserMove = loserMove;
        this.type = type;
        this.roundNumber = roundNumber;
    }
    public Result()
    {

    }

    public Move getWinnerMove() {
        return winnerMove;
    }

    public IPlayer getWinnerPlayer() {
        return winnerPlayer;
    }

    public Move getLoserMove() {
        return loserMove;
    }

    public IPlayer getLoserPlayer() {
        return loserPlayer;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ResultType getType() {
        return type;
    }

    public void setType(ResultType type) {
        this.type = type;
    }

    public void setWinnerMove(Move winnerMove) {
        this.winnerMove = winnerMove;
    }

    public void setWinnerPlayer(IPlayer winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public void setLoserMove(Move loserMove) {
        this.loserMove = loserMove;
    }

    public void setLoserPlayer(IPlayer loserPlayer) {
        this.loserPlayer = loserPlayer;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public String toString() {
        return "Result{" +
                "type=" + type +
                ", winnerMove=" + winnerMove +
                ", winnerPlayer=" + winnerPlayer +
                ", loserMove=" + loserMove +
                ", loserPlayer=" + loserPlayer +
                ", roundNumber=" + roundNumber +
                '}';
    }
}
