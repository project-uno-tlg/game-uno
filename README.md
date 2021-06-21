# game-uno

UNO Mas – we develop the best UNO game you could ever play.

This is a command line application that let’s you play UNO with certain numbers of AIs.


## Goals 

### MVP goals:

1. We will start with regular cards set ( 0-9 with 4 colors).

2. The UI will be simple, a conversation based CLI application, shows your current cards, AI’s cards quantity, and 
   keeps the game going unless you quite.

3. A working game logic.



### Stretch goals:

1. Add all special cards to game logic

2. Make UI looks nicer, maybe show colored cards in hands, just something fancy.

## UNO game info:

1. Player runs client app; define how many AIs he wants to play with.

2. Dealer Bot shuffles the cards, randomly pick up a player to start with, send cards to players one by one. ( 7 for 
each player)

3. Then Dealer Bot opens one card in deck as an indicator for the starter.

4. The picked player’s turn started.  Player must play a card matches either number or color. If player does not have 
it, then draw one from the deck. If the drawn card is a match, automatically play it; Or move to the next player.

5. All players follow rule #4 and game keeps going and going.

6. When any player is out of card, that player wins the game.

7. In the end, someone win the game, then the Dealer Bots asks whether you want to play again? If yes, then game will 
start with the next player after who won the game.

8. At any time, the human player can call “quit” to stop the game.

