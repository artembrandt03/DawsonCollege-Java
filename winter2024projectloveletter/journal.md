Artem Brandt (2139953)
420-210-DW JAVA II
sect. 00002 - Eric Mayhew
DEVELOPMENT JOURNAL - CARD GAME PROJECT: LOVE LETTER
==============================================================================================================================================================================================================================================

April 23 (Tuesday), 2024 

- today's goals: 
    - create a development journal 
    - begin coding the project

- what was achieved (today and before the official coding phase):
    - CardValue enum class completed with CardValue(), getValue() and getName()
    - Card class created with Card(), getCardValue(), toString()
    - CardPile / Dynamic Array class
        - initially created with CardPile(), length(), addCard()
        - implemented classes that we made during the lecture: toString(), removeAtIndex(), contains(), shuffle()
    - Deck class implemented with next features:
        - Deck() constuctor that creates a deck of 21 cards, containing specific number of each type of card
        - printDeck() method implemented
        - shuffleDeck() method implemented 
    - All classes & methods have been tested in the main class and debugged
    - Figured out the development journal and submitted the first day
------------------------------------------------------------------------------------------------------------------------------------------------

April 25 (Thursday), 2024
- today's goals: 
    - begin implementing Player class
        - implement fields: name, hand, discard pile, favorTokens, targetTokens
        - implement methods: drawCard(), discardCard(), playCard(), getHand(), getDiscardPile()
    - test & debug the Player object & methods using the main method

- what was actually achieved:
    - CardPile
        - needed to change removeAtIndex() so that it returns a Card and then removes it from an array
    - Deck
        - added a getTopCard() that uses the updated removeAtIndex() and returns a Card
    - Player
        - Created new Player class and started implementing it
        - implemented fields: name, hand, discardPile, favorTokens, targetTokens
        - implemented Player() consturctor
        - imlemented getName() 
        - implemented drawCard() that uses getTopCard() from Deck and add() from CardPile and adds a card to the hand array
        - implemented printHand()
    - Main Class
        - tested and debugged all newly added methods
------------------------------------------------------------------------------------------------------------------------------------------------

April 26 (Friday), 2024
- today's goals: 
    - CardPile
        - implement sorting algorithm that we studied in class (could be useful for displaying cards in order based on value)
    - Player
        - keep implementing what I didn't finish last time

- what was actually achieved:
    - CardPile:
        - implemented the sort() method
    - Deck:
        - implemented sortDeck() that uses the sort() method
    - Player: 
        - getters: implemented getHand(), getFavorTokens(), getTargetTokens()
        - addFavorTokens() that increments the value of tokens in possesion of a player
    - tested in the main class and made sure the algorithm works correctly, as well as tested the new Player methods.