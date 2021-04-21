# AI Set 1, Exercise 6

This is an algorithm that uses backtracking to solve cryptarithmetic puzzles.

## Installation

Create a java project and add external jar _"AI_Ex6.jar"_. <br />
In Eclipse:<br />
Right click on project --> Build Path --> Configure Build Path --> Libraries --> Add External JARs... -> Select _"AI_Ex6.jar"_ --> run executable as Java application

## Usage

Upon running user is asked to domain p, such that summation is in Zp, e.g.: 
```java
Enter domain: 
6 
```

After that, you are asked to insert the cryptarithmetic puzzle. <br />
Puzzle specification:
- puzzle's first line has to be 2 or 3 letters
- puzzle's second line has to have same length as first
- puzzle's third line has to be 3 letters

User is asked to input line, e.g.:
```java
Enter first line: 
TO
Enter second line: 
TO
Enter third line: 
FOR
```
Program prints puzzle, e.g:
```java
Cryptarithmetic given: 
TO
TO
FOR
```
Finally, program either finds solution and prints the assignment of each letter, or informs the user that the input puzzle has no solution, e.g.
```java
Results:
F = 1
T = 4
R = 0
O = 3
```