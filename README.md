# AI Set1, Exercise 6

This is an algorithm that uses backtracking to solve cryptarithmetic puzzles.

## Installation

Create a java project and add external jar "AI_Ex6.jar". 
In Eclipse:
Right click on project --> Build Path --> Configure Build Path --> Libraries --> Add External JARs... -> Select "AI_Ex6.jar" --> run executable as Java application

## Usage

Upon running 
```java
Enter domain: 
```
Enter domain p, such that summation is in Zp. 

After that, you are asked to insert the cryptarithmetic puzzle. 
Puzzle specification:
	-- puzzle's first line has to be 2 or 3 letters
	-- puzzle's second line has to have same length as first
	-- puzzle's third line has to be 3 letters

User is asked to input line:
```java
Enter first line: 
```
Program prints puzzle:
```java
Cryptarithmetic given: 
TO
TO
FOR
```
Finally, program either finds solution and prints the assignment of each letter, or informs the user that the input puzzle has no solution.
```java
Results:
F = 1
T = 4
R = 0
O = 3
```