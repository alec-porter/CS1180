# Catacomb Crawler
 
## Catacomb Crawler Description
Catacomb Crawler is a rogue-lite game where the player generates a catacomb size N-by-N of their choosing filled with monsters.
 
The player moves through the catacomb using simple keyboard commands and auto fights monsters they encounter.  If the player makes it to the exit without dying they win a treasure.

The number of monsters spawned is based on the catacomb's size and are randomly placed in the catacomb.  The player looses health while fighting monsters and looses health for every move they make through the catacomb.

This game reinforces a variety of programming concepts including object oriented programming, storing and accessing data in an array, verifying playing input, and displaying characters and information to the screen for the player to make decisions.

## Project Guide

### Dependencies

This program was developed and testing using OpenJDK 17.0.13.  To ensure compatibility, you should run this program on OpenJDK 17.0.13 or Oracle JDK 17.0.13 or higher.
This program consists of four classes that must be present for the program to run:  Project4.java, Catacombs.java, Hero.java, and Monster.java.

### How to Run the Project
Extract the contents of project4.zip.  Run Project4.java located in the src folder from your favorite java supported IDE.

### How to Play the Game
<ul>
<li>Start the program and enter your Hero's name.</li>
<li>Enter a catacomb size between 4 and 11.</li>
<li>The number of monsters in the catacomb is generated based on the catacomb size.  The larger the catacomb the more monsters to face.</li>
<li>The Hero always starts in the upper left corner of the catacomb and must navigate to the lower right side of the catacomb designated by the &#x2716; symbol.</li>
<li>The Hero starts with 100 health.</li>
<li>The Hero's current location is indicated by the &#x2656; symbol.</li>
<li>The Hero can move north (up), east (right), south (down), or west (left) depending on thier current location.  For example, if the Hero is along the top edge of the catacomb then they can only move west, east, and south.</li>
<li>The game will promp you for a valid direction to move.</li>
<li>There is poison in the catacomb and each move reduces the Hero's health by 2 points.</li>
<li>The Hero can smell monsters in adjacent rooms and that information is displayed to you.  Adjacent rooms are defined as the rooms directly north, east, south, and west of the Hero.</li>
<li>There can be more than one monster in a room.</li>
<li>If the hero enters a room with one or more monsters they will automatically engage the monsters in battle.  All monsters have 25 health.  The Hero can deal up to 10 points of damage and for each monster they defeat their max potential damage increases by 1.  Monsters can deal up to 5 points of damage.</li>
<li>The status of the battle is displayed to you.  If the Hero survives the battle(s) they can continue their journey through the catacomb.</li>
<li>Once monsters are defeated they de-spawn from the room.  Rooms that have been vanquished of any monsters by the Hero are indicated by the &#x2620; symbol on the map.</li>
<li>If the Hero makes it to the exit they claim the treasure.
</ul>
Good luck and enjoy <b>Catacomb Crawler</b>.

## Lessons Learned
<ul>
<li> Developing a UML with the classes, attributes, methods, etc. I anticipated needing was useful in getting started but I ended up straying from the initial design as I developed the code and never updated the UML (probably should have).</li>
<li> Unless I'm mistaken, you can't use the indexOf() method (or other direct method) to find the index of an array inside of another array.  You need to use a for-each loop and the equals() method. I figured there would be a direct method to do this.</li>
<li> The classes were helpful in developing the code and with some additional planning at the start I think I could have made my code easier to develop an more efficient.</li>
<li> Using an arraylist to store arrays is very useful but can be both more complex and simpler to implement/access than using multiple arrays.
</ul>
