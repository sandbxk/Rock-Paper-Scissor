package rps.bll.player;

import rps.bll.game.GameState;
import rps.bll.game.IGameState;
import rps.bll.game.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Purrminator implements IPlayer
{
    List<Integer> moveChance;

    public void setMoveChance(Move moveType, int value)
    {
        // validate + clamp total to 100 [0] < [1] < [2]
        moveChance.set(moveType.ordinal(), value);
    }

    public Purrminator()
    {
        setMoveChance(Move.Rock, 33);
        setMoveChance(Move.Paper, 33);
        setMoveChance(Move.Scissor, 33);
    }

    @Override
    public String getPlayerName() {
        return "Miaow";
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
        int percent = Math.round(new Random().nextFloat() * 100);

        if (percent < moveChance.get(Move.Rock.ordinal()))
            return Move.Rock;

        if (percent < moveChance.get(Move.Paper.ordinal()))
            return Move.Paper;

        return Move.Scissor;
    }
}
