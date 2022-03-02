package rps.bll.game;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RPSwMarkov
{
        private final static float EPSILON = 0.001f;
        // todo: remove enum
        public enum Item {
            ROCK, PAPER, SCISSORS;
            public List<Item> losesTo;

            public boolean losesTo(Item other) {
                return losesTo.contains(other);
            }

            static {
                SCISSORS.losesTo = Arrays.asList(ROCK);
                ROCK.losesTo = Arrays.asList(PAPER);
                PAPER.losesTo = Arrays.asList(SCISSORS);
            }
        }

        // todo: convert to Move enum (Item)
        private static Item getMoveThatBeats(Item opponentMove)
        {
            return switch (opponentMove)
            {
                case ROCK -> Item.PAPER;
                case PAPER -> Item.SCISSORS;
                case SCISSORS -> Item.ROCK;
            };
        }

        private static DecimalFormat DECIMAL_FORMATTER = new DecimalFormat(".##");
        public static final Random RANDOM = new Random();

        //Game statistics - player,tie and computer
        private int[] stats = new int[] {0, 0, 0};

        //Markov chain for previous throw to current throw prediction
        private float[][] markovChain;
        private int round = 0;
        private Item lastPlayerMove = null;

        public static void main(String[] args)
        {
            RPSwMarkov rps = new RPSwMarkov();
            rps.play();
        }

        private void init()
        {
            int length = Item.values().length;
            markovChain = new float[length][length];

            for (int i = 0; i < length; i++)
            {
                for (int j = 0; j < length; j++)
                {
                    markovChain[i][j] = .33f;
                }
            }
        }

        //The ordinal() function gives the position of the item in its enum declaration
        private void updateMarkovChain(Item prev, Item next)
        {
            float weightStep = 1;

            markovChain[prev.ordinal()][next.ordinal()] += weightStep;

            System.out.println("row #" + prev.ordinal() + " total" + (1 + weightStep));

            markovChain[prev.ordinal()][0] = (markovChain[prev.ordinal()][0] / (1 + weightStep));
            markovChain[prev.ordinal()][1] = (markovChain[prev.ordinal()][1] / (1 + weightStep));
            markovChain[prev.ordinal()][2] = (markovChain[prev.ordinal()][2] / (1 + weightStep));
        }

        private Item predictWinningMove(Item lastPlayerMove)
        {
            // todo: if history.length == 0
            if (round == 0) {
                //Random function on item list for the first move
                return Item.values()[RANDOM.nextInt(Item.values().length)];
            }

            //Predicting next item chosen by the user - reading data in our Markov chain/matrix
            //Done by looking into our previous item

            // initial guess
            Item predictedNextPlayerMove = Item.ROCK;

            for (int i = 0; i < Item.values().length; i++)
            {
                // find best fit
                if (markovChain[lastPlayerMove.ordinal()][i] > markovChain[lastPlayerMove.ordinal()][predictedNextPlayerMove.ordinal()])
                {
                    predictedNextPlayerMove = Item.values()[i];
                }
            }

            // get the move that wins over the prediction
            return getMoveThatBeats(predictedNextPlayerMove);
        }

        private Scanner input = new Scanner(System.in);

        private Item getPlayerMove(String input)
        {
            try
            {
                return Item.valueOf(input.toUpperCase());
            }
            catch (Exception e)
            {
                System.out.println("Invalid choice");
                return null;
            }
        }

        public void play()
        {
            init();

            while (input.hasNextLine())
            {
                System.out.print("Make your choice : ");

                String cliInput = input.nextLine();

                if (cliInput.equals(""))
                    continue;

                if (cliInput.equals("q"))
                    break;

                Item currentPlayerMove = getPlayerMove(cliInput);
                Item currentAIMove = predictWinningMove(lastPlayerMove);


                printWinner(currentPlayerMove, currentAIMove);

                //Update Markov chain
                if (lastPlayerMove != null)
                {
                    updateMarkovChain(lastPlayerMove, currentPlayerMove);
                }

                lastPlayerMove = currentPlayerMove;
                round++;
            }

            input.close();

            printStats();
        }

        private void printWinner(Item PlayerChoice, Item AIChoice)
        {
            System.out.println("Computer choice : " + AIChoice);

            if (AIChoice.equals(PlayerChoice))
            {
                System.out.println(" ==> Tie !\n");
                stats[1]++;
            }
            else if(AIChoice.losesTo(PlayerChoice))
            {
                System.out.println(" ==> You win !\n");
                stats[0]++;
            }
            else
            {
                System.out.println(" ==> You lose !\n");
                stats[2]++;
            }

            System.out.print("Make your choice : ");
        }

        private void printStats()
        {
            System.out.println("\n");
            System.out.println("Statistics:");

            // don't give it an option to divide by 0
            int total = Math.max(stats[0] + stats[1] + stats[2], 1);

            System.out.println("You : " + stats[0] + " - " + DECIMAL_FORMATTER.format(stats[0] / (double) total * 100f) + "%");
            System.out.println("Tie : " + stats[1] + " - " + DECIMAL_FORMATTER.format(stats[1] / (double) total * 100f) + "%");

            System.out.println("Computer : " + stats[2] + " - " + DECIMAL_FORMATTER.format(stats[2] / (double) total * 100f) + "%");
        }
}
