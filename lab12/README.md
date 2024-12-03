# Project 1: Trivia Challenge - Space Edition

## Trivia Challenge - Space Edition
Trivia Challenge - Space Edition is an interactive trivia game that asks five questions related to space.  The player inputs thier answer and the game calculates the number of correct answers.

This game reinforces a variety of programming concepts including choosing and validating data types (integer, string, boolean, float, and character), and player input case-insensitivity.

## Project Guide

### Dependencies
This program was developed and testing using OpenJDK 17.0.13.  To ensure compatibility, you should run this program on OpenJDK 17.0.13 or Oracle JDK 17.0.13 or higher.

### How to Run the Project
Extract the contents of project1.zip.  Run project1.java located in the src folder from your favorite java supported IDE.

### How to Play the Game
Start the game and answer the questions given to you.  If specificed, your input must match the requested data type.  For example, if the question asks you to enter a number to two decimal places, you must follow that requirement (i.e. 23.45) or you won't receive credit for a correct answer.  Your inputs are also case-insensative so capitalization is ignored.

After each question you will be told if your answer is correct.  At the end of the game you will be given a score out of 5 and the percentage of correct answers you entered.

## Lessons Learned
 
Reading in a character from a scanner is not as straight forward as it should be.  Scanner class in Java supports nextInt(), nextLong(), nextDouble() etc. but there is no nextChar(), you have to add the .charAt() method to the scanner.