package rps.bll.game;

/******************************************************************************
 *  Compilation:  javac MarkovChain.java
 *  Execution:    java MarkovChain
 *
 *  Computes the expected time to go from state N-1 to state 0
 *
 *  Data taken from Glass and Hall (1949) who distinguish 7 states
 *  in their social mobility study:
 *
 *   1. Professional, high administrative
 *   2. Managerial
 *   3. Inspectional, supervisory, non-manual high grade
 *   4. Non-manual low grade
 *   5. Skilled manual
 *   6. Semi-skilled manual
 *   7. Unskilled manual
 *
 *   See also Happy Harry, 2.39.
 *
 ******************************************************************************/


public class MarkovChain {

    public static void main(String[] args) {

        // the state transition matrix
        double[][] transition = {
                // R    P     S
                { 0.33, 0.33, 0.33}, // R
                { 0.33, 0.33, 0.33}, // P
                { 0.33, 0.33, 0.33}  // S
        };

        int N = 3;                        // number of states
        int state = N - 1;                // current state
        //int steps = 0;                    // number of transitions

        // run Markov chain
        while (state > 0) {
            System.out.println(state);
            //steps++;
            double r = Math.random();
            double sum = 0.0;

            // determine next state
            for (int j = 0; j < N; j++) {
                sum += transition[state][j];
                if (r <= sum) {
                    state = j;
                    break;
                }
            }

        }
    }
}