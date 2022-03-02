package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;

import java.io.Serializable;

/**
 * Defines a Player in the game including its strategy (doMove())
 *
 * @author smsj
 */
public interface IPlayer extends Serializable {

    /**
     * Returns the name of the player
     * @return
     */
    public String getPlayerName();


    /**
     * Returns the type of the player (AI or Human)
     * @return
     */
    public PlayerType getPlayerType();


    /**
     * Make a Move (Rock, Paper or Scissor)
     *
     * @return
     * @param state
     */
    public Move doMove(IGameState state);
}
