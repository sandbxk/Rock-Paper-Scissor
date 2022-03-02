package rps.bll.player;

import rps.bll.game.GameState;
import rps.bll.game.IGameState;
import rps.bll.game.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Purrminator implements IPlayer
{
    // the state transition matrix
    double[][] markovMatrix = {
            // R    P     S
            { 0.33, 0.33, 0.33}, // R
            { 0.33, 0.33, 0.33}, // P
            { 0.33, 0.33, 0.33}  // S
    };

    public Purrminator()
    {
        
    }

    @Override
    public String getPlayerName() {
        return "Cypurrdyne Systems Mewdel 77";
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.AI;
    }

    @Override
    public Move doMove(IGameState state)
    {
        var history = state.getHistoricResults();

        return RandomMove();
    }

    private Move RandomMove()
    {
        float percent = new Random().nextFloat();

        if (percent < .33f)
            return Move.Rock;

        if (percent < .66f)
            return Move.Paper;

        return Move.Scissor;
    }


}
