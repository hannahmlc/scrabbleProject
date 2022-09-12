# Scrabble
scrabble game with simple local server/client implementation developed as project for univeristy course

## Java version:
Java SE 11

## Commandline arguments
READY, 
QUIT, 
MOVE, 
SWAP

### Setup
    1) run gameServer.java file:
        -provide ip of server you want to create
        -provide port number of server you want to create

    2) run gameClient.java file:
        - provide ip of server you want to connect to
        - provide port number of server you want to connect to
        - provide a chosen username (username of clients must be unique, server may ask u to imput username again if its taken)
        - type "READY" to indicate you're ready to start a game

    *after second client joins server and indicate "READY" game starts*

    3) play game over server:
        - choose one of two possible moves:
            a)MOVE -  to place letter on board:
                 - example: MOVE;B2;CAT;HOR
                 - MOVE - command,
                   B2 - position on board (must be in format LetterNumber, ),
                   "CAT" - word of your choice,
                   HOR - way to place the word (2 possibilities: HOR / VER (horizontally, vertically)
            b) SWAP - to swap letter you currently have
                 - example:  SWAP;ABCD
                 - SWAP - command
                   "ABCD" - letter you want to swap
                 - SWAP; would be considered as skipping the move
        - continue until game ends

    *NOTES*:
    - if word u provide is incorrect you will lose your turn
    - if the MOVE  command is incorrectly written you will lose your turn
    - *after game starts* if command is different then MOVE/SWAP you will not lose your turn, but you will be asked to input command again
    - all commands must be written in uppercase letters
