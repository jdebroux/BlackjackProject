## Blackjack Project

Week 4 homework for Skill Distillery Java bootcamp.

### Overview

Welcome to Blackjack!

In this app, you will be able to run a standard game of Blackjack.  You will first be prompted to enter your name, how many players you would like to play against, and how many decks you would like to play with.  The dealer then deals out cards to everyone playing in correct order.  Just like in the casino, you will only be able to see one of the dealer's cards.  You will then get the option to hit or stay based on your hand total.  

If Aces are present at all, they will be able to have the value 1 or 11, whichever is better for a players hand.  After you bust or decide to stay, the rest of the players get an option to hit or stay.  If the dealer is dealt a soft 17, the dealer must hit.

After a game is completed, you will see who all wins and who loses.  If you're feeling lucky, give it a go!

### Technologies Used

* Java
* Eclipse
* Atom
* Git
* Terminal
* Abstraction
* Poly-Morphism
* Inheritance
* Encapsulation

### Lessons Learned

This has been by far the hardest project I have worked on.  That being said, there were plenty of learning opportunities.  I can tell that my understanding of Abstraction, Poly-Morphism, Inheritance, and Encapsulation is more complete because of this project.  I also figured out a way to reduce code lines by instantiating multiple things all in the same line (lines 68-75 of the BlackjackGameDriver class).

This was also the first project where I included Enums as well as creating certain variables as constants such as the max value a hand can have without busting and the limit where the dealer would hit or stay as static final.  This allows an owner of this application to change these constants all in one place if they wish to.

Also, through using the debugging utility, I was able to strengthen one of my weaker skills.  Up until now I have avoided the debugger, however, I now understand how useful it can be, and how many hours it can save!

### Attack Plan

I began working on this program by creating an UML diagram, and then translating it into code across various classes.  Once I had gotten the basic blueprint figured out, I created the hit or stay methods for the dealer and player classes.  I then created the menus, and the basic game logic to get a minimum viable project created.  Once this was created and debugged, I could focus more on more complex goals.

The complex goals I worked on were:
 * Adding multiple computer players
 * Having computer players have different hitting personalities
 * Letting the player choose how many decks were used
 * Having the dealer automatically discard the current deck and shuffle the same amount of decks that the user originally stated they wanted to play with once half of those cards had been used
 * Adding the logic for Aces to be 1 or 11 depending on situation.
 * Having the dealer hit on soft 17's

### Wishlist Additions

* Adding a money system for users to bet with and have the computer plays bet as well
* Allowing the player and computer players to split
* Allowing the player and computer players to double down
* Use the majority of this project to create other games such as poker and spades
