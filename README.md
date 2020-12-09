## Challenge Description
Place N queens on an N x N chessboard so that none of them attack each other.  Additionally, make sure that no three queens are in a straight 
line at any angle.<h1>

## System
Mac OS Catalina Version 10.15.6 <h1>

## Software

Java version 1.8.0_144
Gradle version 6.6.1 <h1>

## How to read the displayed solution set

Each line represents a board that satisfies the three constraints. The indices correspond to the columns ranging from 0 to n - 1. The values correspond to the row placement from 0 to n - 1.


Board representation example for n = 8.

[2, 6, 1, 7, 4, 0, 3, 5]

  0 1 2 3 4 5 6 7<br />
0 * * * * * Q * *<br />
1 * * Q * * * * *<br />
2 Q * * * * * * *<br />
3 * * * * * * Q *<br />
4 * * * * Q * * *<br />
5 * * * * * * * Q<br />
6 * Q * * * * * *<br />
7 * * * Q * * * *<br /> <h1>



## Grid Collinearity

A chessboard is a grid with a set of positions ranging from (0, 0) to (n - 1, n - 1) where n is the size of the board. 

Each square on the chessboard represents a grid position.

The rise is the number of rows separating any two given positions on the grid.
The run is the number of columns separating any two given positions on the grid.


Let a, b, and c be three unique grid positions.

If the rise divided by the run of a and b is equivalent to the rise divided by the run of a and c, then the line ac passes through b. <h1>


## The isCollinear method

The isCollinear method is an application of the Grid Collinearity Theorem.  The method iterates over all solutions to the classic N x N queen boards.

The method takes the grid position of the queen in the 0th column, q0.  Then calculates the rise divided by the run of q0 and q1, q0 and q2, q0 and q3, ... , q0 and q(n-1) where qn where n is the column is the queen is located and stores them in an empty arraylist data structure.
By the principle of grid collinearity if the arraylist contains any duplicate values, then the board contains at least three queens that form a straight line at any angle.  

If the arraylist contains no duplicate values, the method recurses and proceeds to calculate the rise divided by the run of q1 and q2, q1 and q3, q1 and q4, ... , q1 and q(n-1) and stores them in an empty arraylist data structure. The arraylist is once again checked for duplicate values.

The process continues to recurse until a duplicate value is found.  If the method finds no duplicate values, the board has no queen placements that form a straight line at any angle and is added to the solution set. <h1>
