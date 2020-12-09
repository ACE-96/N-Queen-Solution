/*
Author: Sasha Eldemir
Build tool: Gradle
Compatability-- Gradle 6, java 13.01
 */
package queenBuild;

import java.util.ArrayList;
import java.util.Arrays;


public class App {

    private static void nQueens(String[] numberOfQueens) {
        /*private main method that takes the board size argument from the command line/*
        The nQueens method:
            1) Initializes the arraylist of solutions
            2) Checks that the input is indeed a number
            3) Casts the input from a string input to an integer of size n
            4) Constructs an array of size n
            5) Passes the placeQueen method which generates an arraylist containing every solution that
               satisfies the constraints
            6) The solution set is displayed in the terminal
         */

        ArrayList<String> solutionSet = new ArrayList<>();

        checkArgumentPassed(numberOfQueens);
        int n = Integer.parseInt(numberOfQueens[0]);

        int[] board = new int[n];
        placeQueen(board, 0, n, solutionSet);

        displaySolutions(solutionSet);
    }

    private static void checkArgumentPassed(String[] argument) {
        /*Throws an exception if the argument passed in the terminal is invalid*/
        try {
            int n = Integer.parseInt(argument[0]);

        } catch (NumberFormatException e) {

            System.out.println("Format template: gradle run --args='board size'");
            System.out.println("\n");
        }
    }


    private static boolean isSafe(int[] board, int currentColumnOfQueen) {
        /*Determines whether a board satisfies the traditional NxN queen constrains*/
        for(int i = 0; i < currentColumnOfQueen; ++i) {
        	
            if (board[i] == board[currentColumnOfQueen]) {
                return false;
            }

            if (currentColumnOfQueen - i == Math.abs(board[currentColumnOfQueen] - board[i])) {
                return false;
            }
        }

        return true;
    }


    /*Takes an array that satisfies the horizontal, vertical, and diagonal constraints and applies
    an exhaustive search for collinear queens on the board.  If none are found, the board is added
    to the solutionSet arraylist.*/

    private static void isCollinear(int[] array, int n, int baseCol, ArrayList<String> solutionSets) { 

        ArrayList<Double> ratios = new ArrayList<>();

        int[] temp = Arrays.copyOfRange(array, baseCol, n);
        double run;
        double rise;
        double m;

        ++baseCol;

        if (baseCol == n - 1) {
            solutionSets.add(Arrays.toString(array));

        } else {
            for(int i = 0; i < temp.length - 1; ++i) {
                run = i + 1;
                rise = temp[0] - temp[i + 1];
                m = rise / run;

                if (m == 0.0D) {
                    //checks rows.  Usage: Determining the validity of row placements for a given row.
                    //              Obsolete: Generating the arraylist of solutions that satisfy all three constraints.
                    return;
                }

                if (ratios.contains(m)) {
                    return;
                }

                ratios.add(m);
            }

            isCollinear(array, n, baseCol, solutionSets);
        }
    }

    private static void placeQueen(int[] board, int current, int numberOfQueens, ArrayList<String> solutionSets) {

        if (current == numberOfQueens) {
            generateValidBoards(board, solutionSets);

        } else {
            for(int i = 0; i < numberOfQueens; ++i) {

                board[current] = i;

                if (isSafe(board, current)) {
                    placeQueen(board, current + 1, numberOfQueens, solutionSets);
                }
            }

        }
    }

    private static void generateValidBoards(int[] board, ArrayList<String> solutionSets) {

        int chunk = board.length;

        for(int i = 0; i < board.length; i += chunk) {

            int[] chess = Arrays.copyOfRange(board, i, Math.min(board.length, i + chunk));
            isCollinear(chess, chunk, 0, solutionSets);

        }

    }

    private static void displaySolutions(ArrayList<String> solutionSets) {

        for (String solutionSet : solutionSets) {

            System.out.println(solutionSet);
        }
    }

    public static void main(String[] args) {

        nQueens(args);

    }
}


