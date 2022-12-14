package com.example.chessappplication;

/**
 * Class represents Piece Pawn, extends generic Piece class
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 *
 */
public class Pawn extends Piece {
    public Pawn(String name) {
        super(name);
    }

    /**
     * Method to check if the intended move is legal
     * @param x intended x position
     * @param y intended y position
     * @param board chess board
     * @return
     */
    @Override
    public boolean isInputMoveValid(int x, int y, Piece[][] board) {
        if (this.isWhite) {
          //System.out.println("Pawn is White");
            if (this.xPosition == 2){

                if(x > this.xPosition && x - this.xPosition == 2 && this.yPosition == y){
                  //System.out.println("start positon Pawn moving forward 2 squares");
                    return canMoveForward2(x, y, board);
                }
                else if(x > this.xPosition && x - this.xPosition == 1 && this.yPosition == y){
                  //System.out.println("start position Pawn moving forward 1 square");
                    return canMoveForward1(x, y, board);
                }
                else if(x == this.xPosition + 1 && (y == this.yPosition - 1 || y == this.yPosition + 1)){
                  //System.out.println("start positon Pawn going diagonal");
                    return canMoveDiagonal(x, y, board);
                }
                else return false;
            } else if (this.xPosition > 2 && this.yPosition == y) {
                // pawn has been moved already once, so now it can only proceed one spot at a time...
              //System.out.println("Pawn moving forward 1");
                return canMoveForward1(x, y, board);
            } else if (x == this.xPosition + 1 && (y == this.yPosition - 1 || y == this.yPosition + 1)) {
                // pawn is attempting to kill (and move diagonally)
              //System.out.println("Pawn going diagonal");
                return canMoveDiagonal(x, y, board);
            } else {
                // we cannot make this move PERIOD.
                return false;
            }
        } else {
          //System.out.println("Pawn is balck");
            if (this.xPosition == 7)
            {
                if(x < this.xPosition && Math.abs(x - this.xPosition) == 2 && this.yPosition == y){
                  //System.out.println("Pawn going 2 forward from start");
                    return canMoveForward2(x, y, board);
                }
                else if(x < this.xPosition && Math.abs(x - this.xPosition) == 1 && this.yPosition == y){
                  //System.out.println("Pawn going 1 forward from start");
                    return canMoveForward1(x, y, board);
                }
                else if(x == this.xPosition - 1 && (y == this.yPosition - 1 || y == this.yPosition + 1)){
                  //System.out.println("Pawn going diagonal  from start");
                    return canMoveDiagonal(x, y, board);
                }
                else return false;
            }
            else if (this.xPosition < 7 && this.yPosition == y) {
                // pawn has been moved already once, so now it can only proceed one spot at a time...
              //System.out.println("Pawn going 1 forward");
                return canMoveForward1(x, y, board);
            } else if (x == this.xPosition - 1 && (y == this.yPosition - 1 || y == this.yPosition + 1)) {
                // pawn has been moved already once, and is looking to move one spot diagonally...
              //System.out.println("Pawn going diagonal");
                return canMoveDiagonal(x, y, board);
            } else {
                // we cannot make this move PERIOD.
                return false;
            }
        }
    }

    /**
     * Helper method for isInputMoveValid() for pawns
     * Checks if Pawn can move forward 1 square
     * @param x
     * @param y
     * @param board
     * @return
     */
    // helper method for isInputMoveValid() for pawns
    // pawns moving only one space forward...
    private boolean canMoveForward1(int x, int y, Piece[][] board) {
        if (this.isWhite) {
            if (board[this.xPosition + 1][this.yPosition] == null && x - this.xPosition == 1) {
                return isOppositePiecesAtDestination(x, y, board);
            } else {
                return false;
            }
        } else {
          //System.out.println("This executed");
            if (board[this.xPosition - 1][this.yPosition] == null && this.xPosition - x == 1) {
              //System.out.println("This executed");
                return isOppositePiecesAtDestination(x, y, board);
            } else {
                return false;
            }
        }
    }

    /**
     * Helper method to check if pawn can move 2 squares
     * @param x
     * @param y
     * @param board
     * @return
     */
    // helper method for isInputMoveValid() for pawns
    // pawns moving only two spaces forward in the beginning...
    private boolean canMoveForward2(int x, int y, Piece[][] board) {
        if (this.isWhite) {
            if (this.xPosition == 2 && board[this.xPosition + 2][this.yPosition] == null && board[this.xPosition+1][this.yPosition] == null) {
                return isOppositePiecesAtDestination(x, y, board);
            } else {
                return false;
            }
        } else {
            if (this.xPosition == 7 && board[this.xPosition - 2][this.yPosition] == null && board[this.xPosition-1][this.yPosition] == null) {
                return isOppositePiecesAtDestination(x, y, board);
            } else {
                return false;
            }
        }
    }

    // helper method for isInputMoveValid() for pawns
    // pawns moving only one space diagonally to kill...
    private boolean canMoveDiagonal(int x, int y, Piece[][] board) {
        if(board[x][y] != null){
            return isOppositePiecesAtDestination(x, y, board);
        }
        else{
            return false;
        }

    }

    /**
     * Helper method to check if the destination square satisfies the move conditions
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