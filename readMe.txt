README.txt
Name: Danielle Cai
UNI: dc3347
Assignment: Project 4, dc3347_pp4

=======================================================================================
Project 4: P7.9 in Big Java
=======================================================================================
P7.9: Video Poker

Instructions for playing the game:

In the normal version of the game, the user gets 10 tokens initially 
for betting. They are then asked how many tokens they want to bet and 
whether or not they want to redraw cards. If they say yes to redrawing 
cards, the code will ask them how many cards they want to redraw and
prompt them to choose the card they would like to redraw, for however 
times equal to the number of cards they want to redraw. The hand is then
evaluated and the balance is printed out.

For the args version of the game, the user inputs the hands as a string 
and the game immediately will print out what the hand evaluates to.
I used System.exit(0) to end the args version of the game, found at
https://www.geeksforgeeks.org/system-exit-in-java/. The game does not
continue directly onto the normal version. You will have to restart
the game. There is no betting, balance, redrawing, etc for the args
version as I assumed it was created soley for testing purposes.

Please Note: Intelligent user input is assumed (Pizza @465). The player
is also allowed to go into debt (Piazza @616).

NOTE: When the game asks: "How many cards do you want to redraw", enter in 
the number of cards that you want to redraw. Then, when it prompts: "Which
card do you want to redraw", enter in the order number of the card that you 
want to redraw, and not the actual number of the card. Do this for each card 
that you want to redraw. Also, the terminal can get a little wordy if you
play too many consescutive games just fyi. But the easy fix is to just clear
the terminal and start a new game.
=======================================================================================
Explanations of code design:

I had several classes working together to create this Poker Game. I had
a Card class, a Deck class, a Game class, a Player class, and a given 
PokerTest class. The purpose of the card class is mainly to provide
a way to return the suits and ranks in the proper format. The toString()
method allows for a Card object to be easily printed from numbers to 
words. The compareTo() method compares cards in a way that I define it so
they may be easily sorted. The code first compares the ranks, then uses
the suit to break any ties. The order of the suits is clubs, diamonds, hearts,
spades, from lowest to highest ranking. The purpose of the Deck class is to
create a deck of cards. I first created an array of Card objects called cards.
This is the 52 card deck. I then initialized this deck by using an object of class 
Card called c which then was put into position index of the deck of cards.
I used an instance variable called top in order to keep track of the index
of the top of the deck. I set it equal to 0 at first because before any
cards are dealt, the index should be at the first value in the array.
The shuffle() method randomly chooses a card and swaps it with the first, 
second, third, etc all the way up to 52nd card, choosing a random pos
to swap each consecutive card with each time. The deal() method im the
Deck class returns something of type Card. If the deck has run out, meaning
the index (top) is equal to 52, the deck is reset. Otherwise, top is 
incremented and the previous top card is dealt (returned). The purpose of
the Player class is to add and remove cards from the hand. In the addCard()
and removeCard() methods, the code is simple: hand.add(c) and hand.remove(c).
This is because hand is an arraylist so I can use the methods add() and remove()
to alter elements in the arraylist. This works in the Game class when I call 
the methods after the user input. The Player class also allows for betting
and for keeping track of the winnings and the amount of tokens the player
has left. It interacts with the Game class when p.getBankroll() is used
the get the number of tokens left. Bankroll() is the accessor method that returns
the bankroll after the bets and the winnings. The reset() method creates a new 
player and a new hand, then shuffles the deck so that the player can play again.
I used arraylist.clear() method to delete the arraylist of card objects.
The purpose of the Game class is to play the game. I have two versions of the
game. In the args version, the user enters a string and a game is created
using that string called testHand. The argsHand method() takes in the testHand
as an argument and converts the input, which is in s1, c1, d3, h5, etc format
into numbers that correspond with each suit. The rank is also converted from a 
string to an int. Using the two integers (suit and rank), I made a new hand for
the player. The checkHand()method is then used to evaluate the hand.
In the normal version of the game, the addCard() and removeCard() methods from
the Player class are called in order to modify the arraylist of cards that
that is the player's hand. The getBankroll method is also called to return
the tokens that the player has. The hand is also evaluated using checkHand.
Before evaluating the hand, the method called sortedHand() sorts the arraylist
using Collections.sort, which has a sorting style that is defined by the 
compareTo method. The arraylist hand, of type Card, is then returned so that the
hand is now sorted. To keep the checkHand method() short, I decided to create more
methods for each type of hand that could be checked. I then called each type
of hand checking in order from highest to lowest ranking hand because I knew that
if a higher hand is true, then that is what the player wins no matter if
multiple lower ranking classes are also true. In the checkHand method, I also 
changed the payoff to be associated with each type of hand. The player class 
winnings then uses the payoff of the hand that was won to update the bankroll.
In these ways, the classes Card, Deck, Game, and Player work together to create
the Poker Game. The PokerTest class is then used to create a game and play the game.

=========================================================================================