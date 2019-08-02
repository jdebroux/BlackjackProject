## Blackjack Project -----NEEDS TO BE EDITED FOR BLACKJACK.

Week 3 homework for Skill Distillery Java bootcamp.

### Overview

Welcome to Jets!

I have created a fleet of jets which all have different capabilities.  Upon starting the jets application, the application reads a text document with each jets information already written.  This information is then used in the different jet objections constructors to create the fleet.  

Of course this is all happening behind the scenes.  The user is first presented with a menu with 9 selections.  The user can:

1. View the entire fleet
2. Make every jet take off and show how long they can stay in the air
3. View the fastest jet(s)
4. View the jet with the longest range
5. Have the jets that implement the cargo carrying class load their cargo
6. Have the jets that implement the fighting class load weapons and fight
7. Add a new jet to the fleet
8. Remove a jet from the fleet
9. Quit the application

Test it out!

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

Writing this program had many hurdles that had to be crossed.  Adding the buffered reader and file readers were a bit of a challenge.  At first, I accidentally instantiated multiple air fields, so different classes methods wouldn't be putting the jets in the correct list, or in some cases adding the jets twice to the same list.  Luckily I was able to utilize the debugger, as well as strategically add different system out lines of code to narrow the code down and eventually fix this.

The menu options became more difficult to write with the idea that any jet that is created along the way would need to be added to those equations and included in any searches (fastest jet/longest range).  The added jets would also have to possibly implement different interfaces as well.  These hurdles were not easy to overcome with only 3 weeks of coding experience, however, I was able to focus and come up with solutions through some abstract thought.

### Attack Plan

I began working on this program by creating an UML diagram, and then translating it into code across various classes.  Once I had gotten the basic blueprint figured out, I created a test jets.txt file to get the buffered reader and file reader taken care of.  Once I could see that I had the text file being read correctly and the jets had all been correctly constructed, I was able to look at the menu.

The menu was very easy to make.  I used a basic switch, and then wrote the code for each switch option in a separate menu switch class to keep code organized.  I then went down the list of options and wrote code for them, keeping in mind that adding a jet to the fleet was eventually going to be a possibility.  After that I just wrote the equations for what each menu options desired output was.

### Wishlist Additions

* Adding pilots to pair with jets and the ability to create a new pilot
* Create a mini-game within the dogfight menu option where a user can actually fight an enemy with hit points
* Allow the user to save their file to a new text file
* Add a question for the user in the beginning to tell the application which text file they wanted to read which would add the possibility of having numerous air fields
