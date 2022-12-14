package com.example.chessappplication;


/**
 * Class represents Piece Knight, extends generic Piece class
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 *
 */
public class Knight extends Piece {
    public Knight(String name) {
        super(name);
    }

    /**
     * Method to check if the intended move is legal
     * @param x intended x position
     * @param y intended y position
     * @param board chess board
     * @return returns true if move is legal, false otherwise
     */
    @Override
    public boolean isInputMoveValid(int x, int y, Piece[][] board) {
        //if move would put piece out of the board return false
        if (x > 8 || x < 1 || y > 8 || y < 1) {
            return false;
        }
        //checks for white knight's move
        if (this.isWhite) {
            return canWhiteKnightMove(x, y, board);
        }
        //checks for black knight's move
        else {
            return canBlackKnightMove(x, y, board);
        }
    }

    /**
     * Helper method to check if the white knight can move
     * @param x
     * @param y
     * @param board
     * @return
     */
    private boolean canWhiteKnightMove(int x, int y, Piece[][] board) {
        //knight moves two squares vertically and 1 square horizontally (2 x, 1 y)
        if (Math.abs(x - this.xPosition) == 2 && Math.abs(y - this.yPosition) == 1) {
            //can not move if destination piece is white, return false
            return isOppositePiecesAtDestination(x, y, board);
        }
        //knight moves two square horizontally and 1 square vertically(2 y, 1 x)
        else if (Math.abs(y - this.yPosition) == 2 && Math.abs(x - this.xPosition) == 1) {
            //can not move if destination piece is white, return false
            return isOppositePiecesAtDestination(x, y, board);
        }
        else {
            return false;
        }
    }

    /**
     * helper method to check if black knight can move
     * @param x
     * @param y
     * @param board
     * @return
     */
    private boolean canBlackKnightMove(int x, int y, Piece[][] board) {
        //knight moves two square vertically and 1 square horizontally (2 x, 1 y)
        if (Math.abs(x - this.xPosition) == 2 && Math.abs(y - this.yPosition) == 1) {
            //can not move if destination is not empty and has black piece, return false
            return isOppositePiecesAtDestination(x, y, board);
        }
        //knight move two square horizontally and 1 square vertically (2 y, 1 x)
        else if (Math.abs(y - this.yPosition) == 2 && Math.abs(x - this.xPosition) == 1) {
            //can not move if destination is not empty and has black piece, return false
            return isOppositePiecesAtDestination(x, y, board);
        } else {
            return false;
        }

    }

    /**
     * Helper method to check if piece can be moved to destination square
     * if the conditions are satisfied
     * @param x
     * @param y
     * @param board
     * @return
     */
    //check if the destination square has the opposite players piece
    private boolean isOppositePiecesAtDestination(int x , int y, Piece[][] board) {
        if(board[x][y] == null){
            return true;
        }
        else if(this.isWhite && !board[x][y].isWhite)
        {
            return true;
        }
        else if(!this.isWhite && board[x][y].isWhite){
            return true;
        }
        else{
            return false;
        }
    }
}