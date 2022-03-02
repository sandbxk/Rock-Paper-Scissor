package rps.bll.player;

import rps.bll.game.*;

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

    private Move getLastPlayerMove(Result lastRound)
    {
        if (lastRound.getWinnerPlayer().getPlayerType() == this.getPlayerType())
        {
            // AI winner
            return lastRound.getLoserMove(); // player move
        }
        else
        {
            // player winner
            return lastRound.getWinnerMove(); // player move
        }
    }

    @Override
    public Move doMove(IGameState state)
    {
        List<Result> history = state.getHistoricResults().stream().toList();

        if (history.size() < 1)
            return RandomMove();

        Move lastPlayerMove = getLastPlayerMove(history.get(history.size() - 1));
        Move prediction = predictWinningMove(lastPlayerMove);

        updateMarkovChain(lastPlayerMove, prediction);

        return prediction;
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


    //The ordinal() function gives the position of the item in its enum declaration
    private void updateMarkovChain(Move prev , Move next)
    {
        float weightStep = 1;

        markovMatrix[prev.ordinal()][next.ordinal()] += weightStep;

        System.out.println("row #" + prev.ordinal() + " total" + (1 + weightStep));

        markovMatrix[prev.ordinal()][0] = (markovMatrix[prev.ordinal()][0] / (1 + weightStep));
        markovMatrix[prev.ordinal()][1] = (markovMatrix[prev.ordinal()][1] / (1 + weightStep));
        markovMatrix[prev.ordinal()][2] = (markovMatrix[prev.ordinal()][2] / (1 + weightStep));
    }

    private Move predictWinningMove(Move lastPlayerMove)
    {
        //Predicting next item chosen by the user - reading data in our Markov chain/matrix
        //Done by looking into our previous item

        // initial guess
        Move predictedNextPlayerMove = RandomMove();

        for (int i = 0; i < RPSwMarkov.Item.values().length; i++)
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

}
