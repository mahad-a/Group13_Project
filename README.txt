# Uno Flip Card Game
# SYSC 3110
# Dr. Wafa Hasanain - Carleton University, Department of Systems and Computer Engineering.
# Software Development Project for Fall 2023.
# November 12, 2023

## Project Objective

The objective of the project is to create an UNO Flip card game. Uno Flip is a variation of the classic Uno card game, and we aim to provide an interactive and user-friendly experience for players.

## Project Milestones

### Milestone 1 (Completed)

In this first milestone, we successfully developed a playable text-based version of the UNO card game. Players can interact with the game through the console using keyboard input. This milestone includes the following features:

- Player Range: The ability to play the UNO game with 2 - 4 players.
- Card Visibility: The ability to display the cards in the current player's hand.
- Official Notation: The cards are all labeled using official UNO card notation as specified on the wikipedia page. (https://en.wikipedia.org/wiki/Uno_Flip!)
- Draw Method: The ability to draw a card if the player has no playable cards in their hand.
- Special Card Actions:
	- Reverse Card: Reverses the order of playing
	- Skip Card: Skips the turn of the player who is meant to play next.
	- Wild Card: Allows a player to choose the current colour of the playing cards.
	- Wild Draw Two Card: Allows a player to choose the current colour of the playing cards, and gives the player playing next two more cards.
	- Draw One Card: Allows a player to skip the next player's turn, in addition to making them pick up one card.
- Card State Observation: Display the resultant state of the cards in a text format.
- Card Placement Validation: Ensures that the placement of the cards is valid based on the rules of the UNO Flip game.
- Scoring: The ability to add and update the scores of each player currently playing.
- Testing: The program has been tested using JUnit tests, to ensure a functional program.

### Milestone 2 (Completed)

For this milestone, we successfully transitioned the UnoGame from a text-based version to a GUI-based version of the game. The updates include the addition of a View and a Controller, and they enable a graphical display within a JFrame. User interaction is now accepted through mouse input, ensuring a more user-friendly experience.

We've also designed and implemented unit tests Model, the tests focus on card placement, scoring, and penalty points. These tests are important to ensure proper game functionality.

## Deliverables:

- Readme file (updated to include Milestone 2)
- Design changes and explanations for UML and data structures from Milestone 1
- Corresponding unit tests
- Code (source + executable in a jar file)
- Documentation

### Milestone 3 (Completed)
In this third milestone, we have integrated the Uno Flip card features into the game, implementing specific rules and scoring rules. Additionally, we introduced a pickable number of computer players/AI, allowing the game to accommodate a play alone feature. The AI players implement different strategies at random. One being to play the first card it can and the other searching through the AIs hand and playing the card that scores the most.

New Features:
    Uno Flip added.
    Customizable amount of Computer/AI Players with each having its own difficulty.
    Player range changed from 4 to 6.
    More cards to deck added.
    Added new card methods and specialties 

## Deliverables:
    Readme file (updated to include Milestone 3)
    Design changes and explanations for UML and data structures from Milestone 2
    Corresponding unit tests
    Code (source + executable in a jar file)
    Documentation

### Milestone 4 (Completed)
In the final milestone, we incorporated redo capabilities and replay capabilities. Players now have the ability to redo/undo their moves within the game, and they can replay the game from the start at any given moment, allowing them to play the perfect match and enjoy multiple rounds. We also implemented save/load features using Java Serialization, enabling players to save their current game progress and later resume it. This can be done by using the Game jmenu on the unogames menubar.

New Features:
    Restart Game
    Undo Move
    Redo Move
    Save Game
    Load Game


## Deliverables

Deliverables are to be delivered all in one zip file.

- Readme file
- Code (source + executable in a jar file + Unit tests)
- UML diagrams
- Documentation

## Support

If there are any issues with the game, or you have questions, or need assistance with the Uno Flip card game project, please feel free to contact one of our team members via email:

- yusufibrahim3@cmail.carleton.ca
- hajarassim@cmail.carleton.ca
- hasibkhodayar@cmail.carleton.ca
- mahadahmed3@cmail.carleton.ca
- firaselezzi@cmail.carleton.ca

## Contributors

- Mahad Ahmed
- Firas El-Ezzi
- Hajar Assim
- Hasib Khodayar
- Yusuf Ibrahim