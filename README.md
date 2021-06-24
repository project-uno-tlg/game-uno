# game-uno

UNO Mas – we develop the best UNO game you could ever play.

This is a command line application that let’s you play UNO with certain numbers of AIs.

## How to use

### for Mac / Linux Users:

download the project, run```$sh build.sh```  then run ```$sh run.sh ``` in terminal. Game will lunch. 

### for Windows users:

Warning: Without these steps the color of the cards text will not show, but the game will still run,
what you will see is the card name surrounded by color code ex(***[93mYellow93m]***)

To get the ANSI color code:

1. Right click on command prompt and run as administrator. then type the following:
   
   ```reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f```

2. Then change add_colors.txt to add_colors.bat, double click to run it,  to have the cmd prompt understand the 
   ANSI code.
   If you get a warning just click continue anyway.

3. After those two steps your command prompt should be ready to see the colors of the UNO MAS game.

4. Then double click ```build.cmd```  to build it, then double click ```run.cmd```


## Goals 

### MVP goals:

1. Start by only implementing the regular numbered cards (0-9 with 4 colors) and create a working game logic.

2. Create a simple UI, a conversation based CLI, application, show the human player their current cards, the cards the computer players played,  and keep the game running unless the human player quits or someone wins.

### Stretch goals:

1. Add all action cards to the game logic for a full gaming experience.

2. Make a better looking UI with color text to improve user experience.

3. Make the colored text work with both Windows and Mac machines.

## UNO game info:

How to Play:
1. Enter how many computer players you want to play with.
   You can only enter 1-5. If you enter more than 5 the system will enter 5 for you.
   0 or less will exit the game.

2. Dealer will shuffles the cards, and randomly pick a player to start with,
   and the send cards to the players one by one. ( 7 for each player)

3. Then Dealer opens one card in deck as an indicator for the starter.

4. The picked player’s turn started (Computer or Human).  Player must play a card that matches either a number or color, or play an allowed action card.
   If player could not play any card, the system will draw one from the deck for you and place it in your deck.
   If the drawn card is a match, then the system will automatically play it; Or move to the next player.

5. All players follow rule #4 and game keeps going and going.

6. When any player is out of cards, that player wins the game.

7. At any time, the human player can call “quit” to stop the game.

