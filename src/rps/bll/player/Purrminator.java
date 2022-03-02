package rps.bll.player;

import rps.bll.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Purrminator implements IPlayer
{
    // the state transition matrix
    float[][] markovMatrix = {
            // R    P     S
            { 0.33f, 0.33f, 0.33f}, // R
            { 0.33f, 0.33f, 0.33f}, // P
            { 0.33f, 0.33f, 0.33f}  // S
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

    private Move getPlayerMoveFrom(Result round)
    {
        if (round.getWinnerPlayer().getPlayerType() == this.getPlayerType())
        {
            // AI winner
            return round.getLoserMove(); // player move
        }
        else
        {
            // player winner
            return round.getWinnerMove(); // player move
        }
    }

    @Override
    public Move doMove(IGameState state)
    {
        List<Result> history = state.getHistoricResults().stream().toList();

        if (history.size() < 2)
            return RandomMove();

        return markovianMove(history);
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

    private static Move getMoveThatBeats(Move opponentMove)
    {
        return switch (opponentMove)
                {
                    case Rock -> Move.Paper;
                    case Paper -> Move.Scissor;
                    case Scissor -> Move.Rock;
                };
    }

    private Move markovianMove(List<Result> history)
    {
        if (history.size() < 2)
            return null;

        Move lastPlayerMove = getPlayerMoveFrom(history.get(history.size() - 1));

        Move prediction = predictStatisticalMove(lastPlayerMove);

        updateMarkovChain(history);

        return prediction;
    }

    private void updateMarkovChain(List<Result> history)
    {
        float weightStep = new Random().nextFloat();

        var TargetMove = getPlayerMoveFrom(history.get(history.size() - 1));
        var OriginMove = getPlayerMoveFrom(history.get(history.size() - 2));

        // update bias
        markovMatrix[OriginMove.ordinal()][TargetMove.ordinal()] += weightStep;

        // normalize updated transition matrix row
        markovMatrix[TargetMove.ordinal()][0] = (markovMatrix[TargetMove.ordinal()][0] / (1f + weightStep));
        markovMatrix[TargetMove.ordinal()][1] = (markovMatrix[TargetMove.ordinal()][1] / (1f + weightStep));
        markovMatrix[TargetMove.ordinal()][2] = (markovMatrix[TargetMove.ordinal()][2] / (1f + weightStep));
    }

    private Move predictWinningMove(Move lastPlayerMove)
    {
        //Predicting next item chosen by the user - reading data in our Markov chain/matrix
        //Done by looking into our previous item

        // initial guess
        Move predictedNextPlayerMove = RandomMove();

        for (int i = 0; i < Move.values().length; i++)
        {
            // find best fit
            if (markovMatrix[lastPlayerMove.ordinal()][i] > markovMatrix[lastPlayerMove.ordinal()][predictedNextPlayerMove.ordinal()])
            {
                predictedNextPlayerMove = Move.values()[i];
            }
        }

        // get the move that wins over the prediction
        return getMoveThatBeats(predictedNextPlayerMove);
    }

    private Move predictStatisticalMove(Move lastPlayerMove)
    {
        Move bestFitness = null;

        float optimalDistance = 0f;

        for (int moveType = 0; moveType < 3; moveType++)
        {
            if (markovMatrix[lastPlayerMove.ordinal()][moveType] > optimalDistance)
            {
                optimalDistance = markovMatrix[lastPlayerMove.ordinal()][moveType];
                bestFitness = Move.values()[moveType];
            }
        }

        System.out.println("predicted:" + bestFitness);

        return getMoveThatBeats(bestFitness);
    }

}
