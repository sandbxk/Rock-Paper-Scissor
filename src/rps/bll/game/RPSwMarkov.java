package rps.bll.game;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RPSwMarkov {

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

        private static DecimalFormat DECIMAL_FORMATTER = new DecimalFormat(".##");
        public static final Random RANDOM = new Random();
        //Game statistics - player,tie and computer
        private int[] stats = new int[] {0, 0, 0};
        //Markov chain for previous throw to current throw prediction
        private int[][] markovChain;
        private int nbThrows = 0;
        private Item last = null;

        public static void main(String[] args) {
            RPSwMarkov rps = new RPSwMarkov();
            rps.play();
        }

        private void init() {
            int length = Item.values().length;
            markovChain = new int[length][length];

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    markovChain[i][j] = 0;
                }
            }
        }
        //The ordinal() function gives the position of the item in its enum declaration
        private void updateMarkovChain(Item prev, Item next) {
            markovChain[prev.ordinal()][next.ordinal()]++;
        }

        private Item nextMove(Item prev) {
            if (nbThrows < 1) {
                //Random function on item list for the first move
                return Item.values()[RANDOM.nextInt(Item.values().length)];
            }

            //Predicting next item chosen by the user - reading data in our Markov chain/matrix
            //Done by looking into our previous item
            int nextIndex = 0;

            for (int i = 0; i < Item.values().length; i++) {
                int prevIndex = prev.ordinal();

                if (markovChain[prevIndex][i] > markovChain[prevIndex][nextIndex]) {
                    nextIndex = i;
                }
            }

            //Next item played by the user is in nextIndex
            Item predictedNext = Item.values()[nextIndex];

            //Choosing amongst items for which this next item would lose
            List<Item> losesTo = predictedNext.losesTo;
            return losesTo.get(RANDOM.nextInt(losesTo.size()));
        }

        public void play() {
            init();

            Scanner in = new Scanner(System.in);
            System.out.print("Make your choice : ");

            while (in.hasNextLine()) {
                String input = in.nextLine();

                if ("STOP".equals(input))
                    break;
                Item choice;

                try {
                    choice = Item.valueOf(input.toUpperCase());
                } catch (Exception e) {
                    System.out.println("Invalid choice");
                    continue;
                }

                Item AIChoice = nextMove(last);
                nbThrows++;

                //Update Markov chain
                if (last != null) {
                    updateMarkovChain(last, choice);
                }

                last = choice;

                System.out.println("Computer choice : " + AIChoice);

                if (AIChoice.equals(choice)) {
                    System.out.println(" ==> Tie !\n");
                    stats[1]++;
                } else if(AIChoice.losesTo(choice)) {
                    System.out.println(" ==> You win !\n");
                    stats[0]++;
                } else {
                    System.out.println(" ==> You lose !\n");
                    stats[2]++;
                }

                System.out.print("Make your choice : ");
            }

            in.close();
            System.out.println("\n");
            System.out.println("Statistics:");
            int total = stats[0] + stats[1] + stats[2];
            System.out.println("You : " + stats[0] + " - " +
                    DECIMAL_FORMATTER.format(stats[0] / (float) total * 100f) + "%");
            System.out.println("Tie : " + stats[1] + " - " +
                    DECIMAL_FORMATTER.format(stats[1] / (float) total * 100f) + "%");
            System.out.println("Computer : " + stats[2] + " - " +
                    DECIMAL_FORMATTER.format(stats[2] / (float) total * 100f) + "%");
        }
}
