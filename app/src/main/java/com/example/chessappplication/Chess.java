package com.example.chessappplication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
/**
 * Main class with chess game controls
 *
 * @author Gauravkumar Patel gjp81
 * @author Sami Munir sm2246
 */

import static java.lang.System.arraycopy;

public class Chess {

    /**
     * Main method starts the game, prints chess game board, takes user input
     * and call available methods accordingly to fulfill the user input
     * @param args
     */

        // initialize white player's pieces
        public ArrayList<Piece> whitePlayer = iniWhite();

        // initialize black player's pieces
        public ArrayList<Piece> blackPlayer = iniBlack();
        public ArrayList<String> history = new ArrayList<>();

        //initialize board with both players
        public Piece[][] board = iniBoard(whitePlayer, blackPlayer);


        public boolean gameOn = true;
        public boolean whiteTurn = true;
        public boolean blackTurn = false;
        public boolean drawOffered = false;
        public int currentX, currentY, destinationX, destinationY;
        public String promotionPiece = "";
        public boolean enpassantWhite = false, enpassantBlack = false;
        public int enpassantWhiteLocationX = 0, enpassantBlackLocationX = 0;
        public int enpassantWhiteLocationY = 0, enpassantBlackLocationY = 0;
        public int castleRookX, castleRookY;
        public int castleDestinationX, castleDestinationY;
        int lastMovedPieceX;
        int lastMovedPieceY;
        Piece lastMovedPiece;
        Piece lastCaputredPiece;

        int counter = 0;

    public static int getKingIndex(ArrayList<Piece> playersPieces, boolean isWhite){
        for(Piece pieces : playersPieces){
            //if looking for white king
            if(pieces.isWhite){
                //if piece is king then return its index
                if(pieces.name.equalsIgnoreCase("wK")){
                    return playersPieces.indexOf(pieces);
                }
            }
            //if looking for black king
            else{
                //if piece is king then return its index
                if(pieces.name.equalsIgnoreCase("bK")){
                    return playersPieces.indexOf(pieces);
                }
            }
        }
        return -1;
    }

    public int whiteMove(int currentx, int currenty, int destinationx, int destinationy){
        lastCaputredPiece = null;
        lastMovedPiece = null;
        //System.out.println();
        //get the white player's move
        //System.out.print("White's move: ");


        if(drawOffered){
            /*
            TO-DO
            while(!input.equalsIgnoreCase("draw")){
                System.out.print("White's move: ");
                input = scanner.nextLine();
            }
             */
            whiteTurn = false;
            gameOn = false;
            return 0;
        }

       /*
        //check if player wants to resign
        if(input.equalsIgnoreCase("resign")){
            //System.out.println("Black wins");
            whiteTurn = false;
            gameOn = false;
            return;
        }

        */
        //decode the move

            ////System.out.println("x y and x y" + currentX + currentY + " " + destinationY + destinationX);
        currentX = currentx;
        currentY = currenty;
        destinationX = destinationx;
        destinationY = destinationy;


            if(board[currentX][currentY] != null && board[currentX][currentY].isWhite){
                //check if the move would put the player's king under check
                int kingIndex = getKingIndex(whitePlayer, true);
                boolean isMyKingInCheck = ((King)whitePlayer.get(kingIndex)).isKingInCheck;

                if(isCastling(board[currentX][currentY], destinationX, destinationY)){
                    castle(whitePlayer, blackPlayer, board, board[currentX][currentY], destinationX, destinationY);
                    //check if opposite player's king is put under check after this move
                    int oppositePlayersKingIndex = getKingIndex(blackPlayer, false);
                    boolean isOppositePlayersInCheck = false;
                    isOppositePlayersInCheck = isCheck(whitePlayer, blackPlayer.get(oppositePlayersKingIndex), board, blackPlayer);
                    if(isOppositePlayersInCheck){

                        ((King)blackPlayer.get(oppositePlayersKingIndex)).isKingInCheck = true;
                        //boolean checkmate = isCheckmate(board, blackPlayer);
                        if(isCheckmate(board, blackPlayer, whitePlayer)){

                            //printBoard(board);
                            //System.out.println();
                            //System.out.println("Checkmate");
                            //System.out.println("White wins");
                            whiteTurn = false;
                            gameOn = false;
                            return 1;
                        }
                        //System.out.println("Check");
                    }

                    if(isMyKingInCheck){
                        ((King)blackPlayer.get(kingIndex)).isKingInCheck = false;
                    }
                    //if move is successfully, then print updated board and
                    // black player's turn is true, white's move is over
                    //printBoard(board);
                    whiteTurn = false;
                    blackTurn = true;
                    counter++;
                    return 2;
                }else if(enpassantBlack == true && board[currentX][currentY].name.equalsIgnoreCase("wp")){
                    ////System.out.println("enpassant in white on");
                    ////System.out.println("black location is x and y " + enpassantBlackLocationX + enpassantBlackLocationY);
                    ////System.out.println("destination location x and y "+ destinationX + destinationY);
                    if(enpassantBlackLocationX == destinationX && enpassantBlackLocationY == destinationY){
                        ////System.out.println("enpassant in white on");
                        movePiece(destinationX, destinationY,board[currentX][currentY], board, blackPlayer, kingIndex, whitePlayer);

                        int blackPawnX = destinationX -1;
                        int blackPawnY = destinationY;
                        blackPlayer.remove(board[blackPawnX][blackPawnY]);
                        board[blackPawnX][blackPawnY] = null;
                    }
                    //printBoard(board);
                    whiteTurn = false;
                    blackTurn = true;
                    counter++;
                    enpassantBlack = false;
                    return 3;
                }else if (isValidMove(destinationX, destinationY, board[currentX][currentY], board, whitePlayer, blackPlayer)) {

                    ////System.out.println("King's index " + kingIndex);
                    if(kingIndex != -1){
                        if(movePiece(destinationX, destinationY, board[currentX][currentY], board, blackPlayer, kingIndex, whitePlayer)){
                            ////System.out.println("Piece is move");
                            kingIndex = getKingIndex(whitePlayer, true);
                            isMyKingInCheck = ((King)whitePlayer.get(kingIndex)).isKingInCheck;
                            //check for promotion
                            if(board[destinationX][destinationY].name.equalsIgnoreCase("wp") && destinationX == 8) {
                                promotion(whitePlayer, board[destinationX][destinationY], board, promotionPiece);

                            }
                            //check for enpassant
                            if(board[destinationX][destinationY].name.equalsIgnoreCase("wp") && Math.abs(destinationX - currentX) == 2){
                                if(destinationY< 8 && board[destinationX][destinationY+1] != null) {
                                    if (!(board[destinationX][destinationY + 1].isWhite) && board[destinationX][destinationY+1].name.equalsIgnoreCase("bp")) {
                                        enpassantWhite = true;
                                        enpassantWhiteLocationX = destinationX -1;
                                        enpassantWhiteLocationY = destinationY;

                                    }
                                }
                                if(destinationY> 1 && board[destinationX][destinationY-1] != null) {
                                    if (!(board[destinationX][destinationY - 1].isWhite) && board[destinationX][destinationY - 1].name.equalsIgnoreCase("bp")) {
                                        enpassantWhite = true;
                                        enpassantWhiteLocationX = destinationX -1;
                                        enpassantWhiteLocationY = destinationY;
                                    }
                                }
                            }
                            //check if opposite player's king is put under check after this move
                            int oppositePlayersKingIndex = getKingIndex(blackPlayer, false);
                            boolean isOppositePlayersInCheck = false;
                            isOppositePlayersInCheck = isCheck(whitePlayer, blackPlayer.get(oppositePlayersKingIndex), board, blackPlayer);
                            if(isOppositePlayersInCheck){

                                ((King)blackPlayer.get(oppositePlayersKingIndex)).isKingInCheck = true;
                                //boolean checkmate = isCheckmate(board, blackPlayer);
                                if(isCheckmate(board, blackPlayer, whitePlayer)){
                                    //printBoard(board);
                                    //System.out.println();
                                    //System.out.println("Checkmate");
                                    //System.out.println("White wins");
                                    whiteTurn = false;
                                    gameOn = false;
                                    return 4;
                                }
                                //System.out.println("Check");
                                //printBoard(board);
                                whiteTurn = false;
                                blackTurn = true;
                                counter++;
                                enpassantBlack = false;
                                return 6;
                            }



                            if(isMyKingInCheck){
                                ((King)whitePlayer.get(kingIndex)).isKingInCheck = false;
                            }
                            //if move is successfully, then print updated board and
                            // black player's turn is true, white's move is over
                            //printBoard(board);
                            whiteTurn = false;
                            blackTurn = true;
                            counter++;
                            enpassantBlack = false;
                            return 5;
                        }
                        else{
                            //System.out.println("Illegal move, try again");
                            counter++;
                            return -1;

                        }
                    }

                }
                else{
                    //System.out.println("Illegal move, try again");
                    counter++;
                    return -1;
                }
            }
            else{
                //System.out.println("Illegal move, try again");
                counter++;
                return -1;
            }
            return -1;
        }

    public int blackMove(int currentx, int currenty, int destinationx, int destinationy){
        lastCaputredPiece = null;
        lastMovedPiece = null;
                //get the white player's move
                //System.out.println();
                //System.out.print("Black's move: ");

                /*
                if(drawOffered){
                    while(!input.equalsIgnoreCase("draw")){
                        System.out.print("Black's move: ");
                        input = scanner.nextLine();
                    }
                    blackTurn = false;
                    gameOn = false;
                    return;
                }

                 */

        /*
                if(input.equalsIgnoreCase("resign")){
                    //System.out.println("White wins");
                    blackTurn = false;
                    gameOn = false;
                    return;
                }

         */
                //decode the move
        currentX = currentx;
        currentY = currenty;
        destinationX = destinationx;
        destinationY = destinationy;
                    ////System.out.println("x y and x y" + currentX + currentY + " " + destinationY + destinationX);
                    if(board[currentX][currentY] != null && !board[currentX][currentY].isWhite){
                        int kingIndex = getKingIndex(blackPlayer, false);
                        boolean isMyKingInCheck = ((King)blackPlayer.get(kingIndex)).isKingInCheck;

                        if(isCastling(board[currentX][currentY], destinationX, destinationY)){
                            castle(blackPlayer, whitePlayer, board, board[currentX][currentY], destinationX, destinationY);
                            //check if opposite player's king is put under check after this move
                            int oppositePlayersKingIndex = getKingIndex(whitePlayer, true);
                            boolean isOppositePlayersInCheck = false;
                            isOppositePlayersInCheck = isCheck(blackPlayer, whitePlayer.get(oppositePlayersKingIndex), board, whitePlayer);
                            if(isOppositePlayersInCheck){
                                ((King)whitePlayer.get(oppositePlayersKingIndex)).isKingInCheck = true;
                                if(isCheckmate(board, whitePlayer, blackPlayer)){

                                    //printBoard(board);
                                    //System.out.println();
                                    //System.out.println("Checkmate");
                                    //System.out.println("Black Wins");
                                    blackTurn = false;
                                    gameOn = false;
                                    return 1;
                                }
                                //System.out.println("Check");
                            }
                            if(isMyKingInCheck){
                                ((King)blackPlayer.get(kingIndex)).isKingInCheck = false;
                            }
                            //if move is successfully, then print updated board and
                            // black player's turn is true, white's move is over
                            //printBoard(board);
                            whiteTurn = true;
                            blackTurn = false;
                            counter++;
                            return 2;
                        }else if(enpassantWhite == true && board[currentX][currentY].name.equalsIgnoreCase("bp")){
                            ////System.out.println("enpassant in black on");
                            if(enpassantWhiteLocationX == destinationX && enpassantWhiteLocationY == destinationY){
                                movePiece(destinationX, destinationY, board[currentX][currentY], board, whitePlayer, kingIndex, blackPlayer);

                                int whitePawnX = destinationX +1;
                                int whitePawnY = destinationY;
                                whitePlayer.remove(board[whitePawnX][whitePawnY]);
                                board[whitePawnX][whitePawnY] = null;
                            }
                            //printBoard(board);
                            whiteTurn = true;
                            blackTurn = false;
                            counter++;
                            enpassantWhite = false;
                            return 3;
                        }
                        else if (isValidMove(destinationX, destinationY, board[currentX][currentY], board, blackPlayer, whitePlayer)) {

                            //check if the move would put the player's king under check
                            ////System.out.println("King's index " + kingIndex);
                            if(kingIndex != -1){
                                if(movePiece(destinationX, destinationY, board[currentX][currentY], board, whitePlayer, kingIndex, blackPlayer)){
                                    kingIndex = getKingIndex(blackPlayer, false);
                                    isMyKingInCheck = ((King)blackPlayer.get(kingIndex)).isKingInCheck;
                                    if(board[destinationX][destinationY].name.equalsIgnoreCase("bp") && destinationX == 1) {
                                        promotion(blackPlayer, board[destinationX][destinationY], board, promotionPiece);

                                    }

                                    //check for enpassant
                                    if(board[destinationX][destinationY].name.equalsIgnoreCase("bp") && Math.abs(destinationX - currentX) == 2){
                                        if(destinationY< 8 && board[destinationX][destinationY+1] != null) {
                                            if (board[destinationX][destinationY + 1].isWhite && board[destinationX][destinationY+1].name.equalsIgnoreCase("wp")) {
                                                enpassantBlack = true;
                                                enpassantBlackLocationX = destinationX +1;
                                                enpassantBlackLocationY = destinationY;

                                            }
                                        }
                                        if(destinationY> 1 && board[destinationX][destinationY-1] != null) {
                                            if (board[destinationX][destinationY - 1].isWhite && board[destinationX][destinationY - 1].name.equalsIgnoreCase("wp")) {
                                                enpassantBlack = true;
                                                enpassantBlackLocationX = destinationX +1;
                                                enpassantBlackLocationY =destinationY;
                                            }
                                        }
                                    }
                                    //check if opposite player's king is put under check after this move
                                    int oppositePlayersKingIndex = getKingIndex(whitePlayer, true);
                                    boolean isOppositePlayersInCheck = false;
                                    isOppositePlayersInCheck = isCheck(blackPlayer, whitePlayer.get(oppositePlayersKingIndex), board, whitePlayer);
                                    if(isOppositePlayersInCheck){
                                        ((King)whitePlayer.get(oppositePlayersKingIndex)).isKingInCheck = true;
                                        if(isCheckmate(board, whitePlayer, blackPlayer)){
                                            //printBoard(board);
                                            //System.out.println();
                                            //System.out.println("Checkmate");
                                            //System.out.println("Black wins");
                                            blackTurn = false;
                                            gameOn = false;
                                            return 4;
                                        }
                                        //System.out.println("Check");
                                        //printBoard(board);
                                        whiteTurn = true;
                                        blackTurn = false;
                                        counter++;
                                        enpassantWhite = false;
                                        return 6;
                                    }
                                    if(isMyKingInCheck){
                                        ((King)blackPlayer.get(kingIndex)).isKingInCheck = false;
                                    }
                                    //if move is successfully, then print updated board and
                                    // black player's turn is true, white's move is over
                                    //printBoard(board);
                                    whiteTurn = true;
                                    blackTurn = false;
                                    counter++;
                                    enpassantWhite = false;
                                    return 5;
                                }
                                else{
                                    //System.out.println("Illegal move, try again");
                                    counter++;
                                    return -1;
                                }
                            }
                        }
                        else{
                            //System.out.println("Illegal move, try again");
                            counter++;
                            return -1;
                        }
                    }
                    //if illegal move, then continue asking for white's move
                    else {
                        //System.out.println("Illegal move, try again");
                        counter++;
                        return -1;
                    }
                    return -1;
                }




    /**
     * Takes character, and return its integer value for X
     * @param alphaBet
     * @return Integer
     */
    public int getX(char alphaBet) {
        return Integer.parseInt(Character.toString(alphaBet));
    }

    /**
     * Takes character, and return its integer value for Y
     * @param alphaBet
     * @return
     */
    public int getY(char alphaBet) {
        int number = 0;
        switch (Character.toLowerCase(alphaBet))
        {
            case 'a':
                number = 1;
                break;
            case 'b':
                number = 2;
                break;
            case 'c':
                number = 3;
                break;
            case 'd':
                number = 4;
                break;
            case 'e':
                number = 5;
                break;
            case 'f':
                number = 6;
                break;
            case 'g':
                number = 7;
                break;
            case 'h':
                number = 8;
                break;
        }
        return number;
    }

    // methods to implement
    // valid move checker
    // check checker
    // checkmate checker
    // castling
    // Enpassant
    // Promotion
    // Draw
    // display drawboard
    
    // plus implement valid move checker for individual pieces in their own class
    
    // takes two arraylist with 2 players pieces and puts them on correct positon on board
    
    /* board stored as
    0 -row null not used
    1-white player pieces
    2-white player pawns
    3-null
    4-null
    5-null
    6-null
    7-black player pawns
    8-black player piece
    */
    /* displayed as
    8-black player pieces
    7-black player pawns
    6-null
    5-null
    4-null
    3-null
    2-white player pawns
    1-white player pieces
    0-not displayed
    */
    
    // can access board elements at index indicated by the user
    // does not need to perform any arithmetic on user input to find the correct piece
    // assigns their position x, y and their color to pieces

    /**
     * Initilizes the Chess Board with all the Piece
     * @param white
     * @param black
     * @return Chess Board
     */
    public Piece[][] iniBoard(ArrayList<Piece> white, ArrayList<Piece> black) {
        Piece[][] board = new Piece[9][9];
        for(Piece[] array : board) Arrays.fill(array, null);
        int warrayListCounter = 0;
        int barrayListCounter = 0;
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (i == 1 || i == 2) {
                    board[i][j] = white.get(warrayListCounter);
                    board[i][j].xPosition = i;
                    board[i][j].yPosition = j;
                    board[i][j].isWhite = true;
                    warrayListCounter++;
                } else if (i == 7 || i == 8) {
                    board[i][j] = black.get(barrayListCounter);
                    board[i][j].xPosition = i;
                    board[i][j].yPosition = j;
                    board[i][j].isWhite = false;
                    barrayListCounter++;
                }
            }
        }
        return board;
    }

    // creates a list of white players pieces and gives them name
    // puts all pieces into list and passes back to main

    /**
     * Initilize the white Pieces for white player
     * @return Arraylist containing White Pieces
     */
    public static ArrayList<Piece> iniWhite() {
        ArrayList<Piece> whitePlayer = new ArrayList<>();
        King wK = new King("wk");
        wK.castledBefore = false;
        Rook wR1 = new Rook("wR");
        Rook wR2 = new Rook("wR");
        Queen wQ = new Queen("wQ");
        Knight wN1 = new Knight("wN");
        Knight wN2 = new Knight("wN");
        Bishop wB1 = new Bishop("wB");
        Bishop wB2 = new Bishop("wB");
        Pawn wP1 = new Pawn("wp");
        Pawn wP2 = new Pawn("wp");
        Pawn wP3 = new Pawn("wp");
        Pawn wP4 = new Pawn("wp");
        Pawn wP5 = new Pawn("wp");
        Pawn wP6 = new Pawn("wp");
        Pawn wP7 = new Pawn("wp");
        Pawn wP8 = new Pawn("wp");
        whitePlayer.add(wR1);
        whitePlayer.add(wN1);
        whitePlayer.add(wB1);
        whitePlayer.add(wQ);
        whitePlayer.add(wK);
        whitePlayer.add(wB2);
        whitePlayer.add(wN2);
        whitePlayer.add(wR2);
        whitePlayer.add(wP1);
        whitePlayer.add(wP2);
        whitePlayer.add(wP3);
        whitePlayer.add(wP4);
        whitePlayer.add(wP5);
        whitePlayer.add(wP6);
        whitePlayer.add(wP7);
        whitePlayer.add(wP8);
        return whitePlayer;
    }
    
    // creates a list of black players pieces and gives them name
    // puts all pieces into a list and passes it back to main

    /**
     * Initilize the black Pieces for black player
     * @return Arraylist contaiting Black Pieces
     */
    public ArrayList<Piece> iniBlack() {
        ArrayList<Piece> blackPlayer = new ArrayList<>();
        King bK = new King("bK");
        bK.castledBefore = false;
        Rook bR1 = new Rook("bR");
        Rook bR2 = new Rook("bR");
        Queen bQ = new Queen("bQ");
        Knight bN1 = new Knight("bN");
        Knight bN2 = new Knight("bN");
        Bishop bB1 = new Bishop("bB");
        Bishop bB2 = new Bishop("bB");
        Pawn bP1 = new Pawn("bp");
        Pawn bP2 = new Pawn("bp");
        Pawn bP3 = new Pawn("bp");
        Pawn bP4 = new Pawn("bp");
        Pawn bP5 = new Pawn("bp");
        Pawn bP6 = new Pawn("bp");
        Pawn bP7 = new Pawn("bp");
        Pawn bP8 = new Pawn("bp");
        blackPlayer.add(bP1);
        blackPlayer.add(bP2);
        blackPlayer.add(bP3);
        blackPlayer.add(bP4);
        blackPlayer.add(bP5);
        blackPlayer.add(bP6);
        blackPlayer.add(bP7);
        blackPlayer.add(bP8);
        blackPlayer.add(bR1);
        blackPlayer.add(bN1);
        blackPlayer.add(bB1);
        blackPlayer.add(bQ);
        blackPlayer.add(bK);
        blackPlayer.add(bB2);
        blackPlayer.add(bN2);
        blackPlayer.add(bR2);
        return blackPlayer;
    }

    // prints the chess board

    /**
     * Prints the board to the termianl
     * @param board
     */
    public void printBoard(Piece[][] board) {
        //System.out.println();
        for (int i = 8; i > 0; i--) {
            for (int j = 1; j < board[1].length; j++) {
                if (i % 2 == 0 && j % 2 == 0 && board[i][j] == null) {
                    System.out.print("## ");
                } else if (i % 2 == 1 && j % 2 == 1 && board[i][j] == null) {
                    System.out.print("## ");
                } else if (board[i][j] == null) {
                    System.out.print("   ");
                } else {
                    System.out.print(board[i][j].name + " ");
                }
            }
            System.out.print(i);
            //System.out.println();
        }
        char arr[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + arr[i] + " ");
        }
        //System.out.println();
    }


    //helper in testing
    //will be removed in final code
    /*
       public static void (Piece[][] board){
           //System.out.println();
           for (int i = 8; i > 0; i--) {
               for (int j = 1; j < board[1].length; j++) {
                   if(board[i][j] == null){
                       System.out.print("null" + " ");
                   }else{
                       System.out.print(board[i][j].name + "   ");
                   }
                                                                                   
               }
               System.out.print(i);
               //System.out.println();
           }
           char arr[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
           for (int i = 0; i < 8; i++) {
               System.out.print(" " + arr[i] + "   ");
           }
           //System.out.println();
                                                                                   
                                                                                   
       }
    */

    /**
     * Determines whether the Piece is trying to move legally
     * Takes an destination, and current piece
     * Calls the appropriate Valid move checker for the piece
     * Return true if move is legal
     * @param x
     * @param y
     * @param currentPiece
     * @param board
     * @param currentPlayer
     * @param oppositePlayer
     * @return returns true if move is legal, false otherwise
     */
    // controller method to make final decision on whether input move is legal or not...
    public boolean isValidMove(int x, int y, Piece currentPiece, Piece[][] board, ArrayList<Piece> currentPlayer, ArrayList<Piece> oppositePlayer) {
        ////System.out.println(currentPiece.name);
        ////System.out.println(board[x][y].name);

        boolean initialFlag =  currentPiece.isInputMoveValid(x, y, board);
        ////System.out.println("ini flag of " + currentPiece.name + initialFlag);
        if (initialFlag){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method to check if the current move is attempting to do Castling
     * @param currentPiece
     * @param x
     * @param y
     * @return returns true if move is castling, false otherwise
     */
    //check if the current move is castle move
    public boolean isCastling(Piece currentPiece, int x, int y ){
        if(currentPiece.hasMovedBefore == false){
            //king is trying to castle
            if(currentPiece.isWhite && currentPiece.name.equalsIgnoreCase("wk") && Math.abs(y - currentPiece.yPosition) == 2 && x == currentPiece.xPosition){
                if( ((King)currentPiece).isKingInCheck == false){
                    ////System.out.println("castle white player");
                    return true;
                }


            }else if( !currentPiece.isWhite && currentPiece.name.equalsIgnoreCase("bk") && Math.abs(y - currentPiece.yPosition) == 2 && x == currentPiece.xPosition){
                if( ((King)currentPiece).isKingInCheck == false){
                    ////System.out.println("castle black player");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to move the pieces to the destination square, if the move is legal
     * and the move would not put player's king in check
     * @param x
     * @param y
     * @param currentPiece
     * @param board
     * @param oppositePlayerPieces
     * @param kingIndex
     * @param currentPlayer
     * @return True is Move was successful
     */
    //if move is validated, them move the pieces
    public boolean movePiece(int x, int y, Piece currentPiece, Piece[][] board, ArrayList<Piece> oppositePlayerPieces, int kingIndex, ArrayList<Piece> currentPlayer){
        ////System.out.println("x is " + x + "y is " + y);
        ////System.out.println(currentPiece);
        int currentX = currentPiece.xPosition;
        int currentY = currentPiece.yPosition;
        Piece captured = board[x][y];
        lastCaputredPiece = captured;
        lastMovedPieceX = currentX;
        lastMovedPieceY = currentY;
        lastMovedPiece = currentPiece;
        if(captured != null){
            //removes the piece that is captured
            oppositePlayerPieces.remove(captured);
            board[x][y] = currentPiece;
            currentPiece.setXPosition(x);
            currentPiece.setYPosition(y);
            board[currentX][currentY] = null;

            //check for if move would put king under attack
            //if yes, then reverse last move and return false
            ////System.out.println("King index is and color" + currentPlayer.get(kingIndex) + currentPiece.isWhite);
            if(isCheck(oppositePlayerPieces, currentPlayer.get(kingIndex), board, currentPlayer)){
                ////System.out.println("reverserig move 1");
                oppositePlayerPieces.add(captured);
                board[x][y] = captured;
                board[currentX][currentY] = currentPiece;
                currentPiece.setXPosition(currentX);
                currentPiece.setYPosition(currentY);
                lastCaputredPiece = null;
                lastMovedPiece = null;
                return false;
            }
            //if piece moving for first time then mark it as moved beofre
            if(!currentPiece.hasMovedBefore) currentPiece.hasMovedBefore = true;
            return true;
        }
        else{
            board[x][y] = currentPiece;
            currentPiece.setXPosition(x);
            currentPiece.setYPosition(y);
            board[currentX][currentY] = null;
            ////System.out.println("King index is and color" + currentPlayer.get(kingIndex) + currentPiece.isWhite);

            //check for if move would put king under attack
            //if yes, then reverse last move and return false
            if(isCheck(oppositePlayerPieces, currentPlayer.get(kingIndex), board, currentPlayer)){
                ////System.out.println("reverserig move 2");
                board[x][y] = captured;
                board[currentX][currentY] = currentPiece;
                currentPiece.setXPosition(currentX);
                currentPiece.setYPosition(currentY);
                lastCaputredPiece = null;
                lastMovedPiece = null;
                return false;
            }
            //if piece moving for first time then mark it as moved beofre
            if(!currentPiece.hasMovedBefore) currentPiece.hasMovedBefore = true;
            return true;
        }

    }


    /**
     * Method to check if the King of current player is in check
     * @param OppositeplayerPieces
     * @param king
     * @param board
     * @param currentPlayer
     * @return returns true if king is in check, false otherwise
     */
    //check if the opponent's piece could reach players king and put it under attack(check)
    public boolean isCheck(ArrayList<Piece> OppositeplayerPieces, Piece king, Piece[][] board, ArrayList<Piece> currentPlayer){
        int kingX = king.xPosition;
        int kingY = king.yPosition;
        ////System.out.println("King x and y " + kingX + kingY);
        for(Piece pieces : OppositeplayerPieces){
            ////System.out.println(pieces.name);
            ////System.out.println("attacking piece name" + pieces.name + pieces.xPosition + pieces.yPosition );
            if(isValidMove(kingX, kingY, pieces, board, currentPlayer, OppositeplayerPieces)){
                ////System.out.println("King x and y is " + kingX + kingY);
                ////System.out.println("attacking piece name" + pieces.name + pieces.xPosition + pieces.yPosition);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to promote the eligible piece to its choice of piece
     * if no choice is indicated, then promote it to Queen
     * @param cuurentPlayerPieces
     * @param pawn
     * @param board
     * @param promotionPiece
     */
    public void promotion(ArrayList<Piece> cuurentPlayerPieces, Piece pawn, Piece[][] board, String promotionPiece){
        int currentX = pawn.xPosition;
        int currentY = pawn.yPosition;
        Piece newPiece;
        boolean isWhite = pawn.isWhite;

        if(promotionPiece.equalsIgnoreCase("n")){
            if(isWhite){
                newPiece = new Knight("wN");
            }else {
                newPiece = new Knight("bN");
            }
        }
        else if(promotionPiece.equalsIgnoreCase("r")){
            if(isWhite){
                newPiece = new Rook("wR");
            }else {
                newPiece = new Rook("bR");
            }
        }
        else if(promotionPiece.equalsIgnoreCase("b")){
            if(isWhite){
                newPiece = new Bishop("wB");
            }else {
                newPiece = new Bishop("bB");
            }
        }
        else {
            if(isWhite){
                newPiece = new Queen("wQ");
            }else {
                newPiece = new Queen("bQ");
            }
        }

        newPiece.setXPosition(currentX);
        newPiece.setYPosition(currentY);
        newPiece.isWhite = isWhite;
        board[currentX][currentY] = newPiece;
        cuurentPlayerPieces.remove(pawn);
        cuurentPlayerPieces.add(newPiece);

    }


    /**
     * Method to check if the king is in checkmate
     *
     * @param board
     * @param currentPlayer
     * @param oppositePlayer
     * @return returns true if checkmate, false otherwise
     */
    public boolean isCheckmate(Piece[][] board, ArrayList<Piece> currentPlayer, ArrayList<Piece> oppositePlayer){
        Piece[][] tempBoard = new Piece[9][9];
        for(int i = 0; i < board.length; i++){
            tempBoard[i] = board[i].clone();
        }


        int kingIndex = getKingIndex(currentPlayer, currentPlayer.get(0).isWhite);
        ////System.out.println("King index " + kingIndex);



        for(Piece pieces : currentPlayer){
            for(int i = 1; i < tempBoard.length; i++){
                for(int j = 1; j < tempBoard.length; j++){
                    if(isValidMove(i, j , pieces, tempBoard, currentPlayer, oppositePlayer)){
                        ////System.out.println("from outer i is " + i + "j is " + j + "piece name is " + pieces.name);
                        int x = pieces.xPosition;
                        int y = pieces.yPosition;
                        if(movePiece(i, j, pieces, tempBoard, oppositePlayer, kingIndex, currentPlayer)){
                            pieces.xPosition = x;
                            pieces.yPosition = y;
                            ////System.out.println("i is " + i + "j is " + j + "piece name is " + pieces.name);
                            return false;
                        }
                    }

                }
            }
        }
        return true;


    }

    /**
     * Method checks for castling, and checks if it is permmited
     * If the piece is eligible for it, then it calles the movePiece
     * and moves the pieces accordingly
     * @param currentPlayer
     * @param oppositePlayer
     * @param board
     * @param currentKing
     * @param x
     * @param y
     * @return returns true if castling was success, false otherwise
     */
    public boolean castle(ArrayList<Piece> currentPlayer, ArrayList<Piece> oppositePlayer, Piece[][]  board, Piece currentKing, int x, int y){
        int kingX = currentKing.xPosition;
        int kingY = currentKing.yPosition;
        int rookX = 0, rookY = 0;

        //if player is white
        if(currentKing.isWhite){
            //king wants to castle to right
            if(y > kingY){
                for(Piece piece : currentPlayer){
                    if(piece.xPosition == kingX && piece.yPosition > kingY && piece.name.equalsIgnoreCase("wr")){
                        rookX = piece.xPosition;
                        rookY = piece.yPosition;
                    }
                }
            }
            //king wants to castle to left
            else{
                for(Piece piece : currentPlayer){
                    if(piece.xPosition == kingX && piece.yPosition < kingY && piece.name.equalsIgnoreCase("wr")){
                        rookX = piece.xPosition;
                        rookY = piece.yPosition;
                    }
                }
            }
        }
        else{
            //king wants to castle to left
            if(y > kingY){
                for(Piece piece : currentPlayer){
                    if(piece.xPosition == kingX && piece.yPosition > kingY && piece.name.equalsIgnoreCase("br")){
                        rookX = piece.xPosition;
                        rookY = piece.yPosition;
                    }
                }
            }
            //king wants to castle to right
            else{
                ////System.out.println("black king castle left");
                for(Piece piece : currentPlayer){
                    ////System.out.println(piece.name);
                    if(piece.xPosition == kingX && piece.yPosition < kingY && piece.name.equalsIgnoreCase("br")){
                        rookX = piece.xPosition;
                        rookY = piece.yPosition;
                    }
                }
            }

        }
        ////System.out.println("rook x and y is " + rookX + rookY);
        //there is no rook of same color
        if(rookX == 0){
            return false;
        }

        //check if rook has previously moved
        if(board[rookX][rookY].hasMovedBefore){
            return false;
        }

        //check if the path between king and rook is clear
        boolean isPathClear = isValidMove(kingX, kingY, board[rookX][rookY],board, currentPlayer,oppositePlayer);
        ////System.out.println(isPathClear);

        int middleSquare = (y + kingY) / 2;
        ////System.out.println("Middle is " + middleSquare);
        //check if the square between the move is attacked by enemy
        if(isPathClear){
            for(Piece piece : oppositePlayer){
               if(isValidMove(kingX, middleSquare, piece, board, oppositePlayer, currentPlayer)){
                   return false;
                }
            }
        }
        ////System.out.println("current piece here is " + currentKing.name);

        if(movePiece(x, y, currentKing, board, oppositePlayer, getKingIndex(currentPlayer, currentKing.isWhite), currentPlayer)){
            int currentRookX, currentRookY;
            currentRookX = x;

            if(currentKing.isWhite){
                if(y > kingY){
                    board[x][y-1] = board[rookX][rookY];
                    board[x][y-1].setXPosition(x);
                    board[x][y-1].setYPosition(y-1);
                    board[rookX][rookY] = null;
                    currentRookY = y-1;
                    castleDestinationY = y-1;

                }
                else{
                    board[x][y+1] = board[rookX][rookY];
                    board[x][y+1].setXPosition(x);
                    board[x][y+1].setYPosition(y+1);
                    board[rookX][rookY] = null;
                    currentRookY = y+1;
                    castleDestinationY = y+1;
                }
            }
            else{
                if(y > kingY){
                    board[x][y-1] = board[rookX][rookY];
                    board[x][y-1].setXPosition(x);
                    board[x][y-1].setYPosition(y-1);
                    board[rookX][rookY] = null;
                    currentRookY = y-1;
                    castleDestinationY = y-1;
                }
                else{
                    board[x][y+1] = board[rookX][rookY];
                    board[x][y+1].setXPosition(x);
                    board[x][y+1].setYPosition(y+1);
                    board[rookX][rookY] = null;
                    currentRookY = y+1;
                    castleDestinationY = y+1;
                }
            }
            //System.out.println("rook x and y is " + rookX + rookY);
            castleRookX = rookX;
            castleRookY = rookY;
            castleDestinationX = x;
            //System.out.println("rook current x and y is " + currentRookX + currentRookY);
            return true;
        }
        else{
            return false;
        }

    }

    public void undoMove(){
        if(lastMovedPiece == null){
            return;
        }
        int currentX = lastMovedPiece.xPosition;
        int currentY = lastMovedPiece.yPosition;
        lastMovedPiece.setXPosition(lastMovedPieceX);
        lastMovedPiece.setYPosition(lastMovedPieceY);
        //System.out.println("current x is " + currentX);
        //System.out.println("current y is " + currentY);
        //System.out.println("last x is " + lastMovedPieceX);
        //System.out.println("last y is " + lastMovedPieceY);
        //System.out.println("last moved piece " + lastMovedPiece.name);
        board[lastMovedPieceX][lastMovedPieceY] = lastMovedPiece;
        board[currentX][currentY] = null;

        whiteTurn = !whiteTurn;
        if(lastCaputredPiece != null){
            if (lastCaputredPiece.isWhite){
                whitePlayer.add(lastCaputredPiece);
                board[currentX][currentY] = lastCaputredPiece;
            }else{
                blackPlayer.add(lastCaputredPiece);
                board[currentX][currentY] = lastCaputredPiece;
            }
        }
        //printBoard(board);

    }

    public String getRandomMove(){
        ArrayList<Piece> currentPlayer;
        ArrayList<Piece> oppositePlayer;
        if(whiteTurn){
            currentPlayer = whitePlayer;
            oppositePlayer = blackPlayer;
        }else {
            currentPlayer = blackPlayer;
            oppositePlayer = whitePlayer;
        }
        Piece[][] tempBoard = new Piece[9][9];
        for(int i = 0; i < board.length; i++){
            tempBoard[i] = board[i].clone();
        }
        ArrayList<String> possibleMoves = new ArrayList<>();

        boolean currentPlayerColor = currentPlayer.get(0).isWhite;
        int kingIndex = getKingIndex(currentPlayer, currentPlayer.get(0).isWhite);

        for(Piece pieces : currentPlayer){
            for(int i = 1; i < tempBoard.length; i++){
                for(int j = 1; j < tempBoard.length; j++){
                    int prevX = pieces.xPosition;
                    int prevY = pieces.yPosition;
                    if(isValidMove(i, j , pieces, tempBoard, currentPlayer, oppositePlayer)){
                    String possibleMove;
                        if(board[i][j] != null && board[i][j].isWhite != currentPlayerColor){
                            possibleMove = "" + prevX + "" +prevY + "" + i + "" + j;
                            possibleMoves.add(possibleMove);
                            //System.out.println("prev position is " + prevX + "  " +prevY);
                            //System.out.println("random move is " + pieces.name + "  " + i + j);
                        }
                        else if(board[i][j] == null){
                            possibleMove = "" + prevX + "" +prevY + "" + i + "" + j;
                            possibleMoves.add(possibleMove);
                            //System.out.println("prev position is " + prevX + "  " +prevY);
                            //System.out.println("random move is " + pieces.name + "  " + i + j);

                        }

                        //////System.out.println("from outer i is " + i + "j is " + j + "piece name is " + pieces.name);
                        //int x = pieces.xPosition;
                        //int y = pieces.yPosition;
                        //if(movePiece(i, j, pieces, tempBoard, oppositePlayer, kingIndex, currentPlayer)){
                        //    pieces.xPosition = x;
                        //    pieces.yPosition = y;
                        //    ////System.out.println("i is " + i + "j is " + j + "piece name is " + pieces.name);

                        }
                    }

                }
            }

        Random r = new Random();

        int randomitem = r.nextInt(possibleMoves.size());
        String randomElement = possibleMoves.get(randomitem);
        return randomElement;

        }
}