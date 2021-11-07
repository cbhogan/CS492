

# BLACKJACK README
## Authors and Intdended Purpose
This project was designed, developed, and packaged Brandon Rias and Collin Hogan. It was completed for Western Illinois University, class CS492 Fall 2021 for our primary project.

This game is meant to be played by one human player and the A.I. Dealer. It is a standard game of Blackjack, where the goal is to hit a value of 21 or beat the dealer's hand without ever going over 21 (otherwise known as a bust).

## DIRECTORIES

`src`: This directory contains all the Java source files (known as classes) that are detailed below.

`images`: This directory contains images that will be displayed to the player. Examples include cards, chips, and the background image

`sounds`: This directory contains sounds that will add in a casino ambience, further improving the experience.

## CLASSES AND DESCRIPTIONS

`Card.java`->Card Class: This class is for the card itself. The printCard method prints the cards which from a sprite sheet, storing them in a 2D array.

`Deck.java`->Deck Class: With the deck class, a deck that consists of 52 cards is created. There are also methods to shuffle, add, and remove cards.

`Game.java`->Game Class: This class calculates the Blackjack game itself. It starts the game, forms the hands, checks the hands for a blackjack or bust. 

`Board.java`->Board Class: This class is for the overall atmosphere of our game. When the user clicks the play button in BoardRunner (menu), this component opens with the poker table, poker chip, the cards, etc.

`BoardRunner.java`->BoardRunner Class: This class is for the main menu in our program.

`Tester.java`->Tester Class: The tester class is the main class of our program. It will run when you click run in your IDE of choice that runs Java.
