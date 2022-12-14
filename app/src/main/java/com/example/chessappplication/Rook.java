package com.example.chessappplication;

/**
 * Class represents Piece Rook, extends generic Piece class
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 *
 */
public class Rook extends Piece {

    public Rook(String name) {
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
        // rooks can move both up/down, unlike the pawns which can only advance forward.
        // unlike pawns, the same rules can be applied for black & white rooks
        // 1. check if the rook is even movable (a.k.a. is it surrounded by its own pieces one spot away)
        
        // this method checks if the destination could be a possible-valid move from the initial spot...
        if (this.xPosition == x && (y > this.yPosition || y < this.yPosition)) {
            //System.out.println("rook moving left/right");
            // rook is trying to move left/right
            // check if we can actually move left/right
            return canMoveLeftRight(x, y, board);
        } else if (this.yPosition == y && (x > this.xPosition || x < this.xPosition)) {
            // rook trying to move up/down
            //System.out.println("rook moving up/down");
            // check if we can actually move up/down
            return canMoveUpDown(x, y, board);
        } else {
            // rook is trying to move in invalid manner
            return false;
        }
    }


    /**
     * Helper method to check if piece can move left/right
     * @param x
     * @param y
     * @param board
     * @return
     */
    private boolean canMoveLeftRight(int x, int y, Piece[][] board) {
        if (this.isWhite) {
            // trying to go left...
            if (y < this.yPosition) {
                // check if the destination spot is empty...
                if (board[this.xPosition][y] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {

                    return true;
                // check if the destination spot is not empty and the piece in the destination spot is a white piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    // add additional logic... for pieces in the way maybe...
                    return false;
                // check if the destination spot is not empty and the piece in the destination spot is a black piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                      // add additional logic... for pieces in the way maybe...
                    return true;
                } else {
                    // ***NOTE***
                    // some other bullshit case that we may not hit...
                    return false;
                }
            // trying to go right
            } else {
                if (board[this.xPosition][y] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                // check if the destination spot is not empty and the piece in the destination spot is a white piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    // add additional logic... for pieces in the way maybe...
                    return false;
                // check if the destination spot is not empty and the piece in the destination spot is a black piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                      // add additional logic... for pieces in the way maybe...
                    return true;
                } else {
                    // ***NOTE***
                    // some other bullshit case that we may not hit...
                    return false;
                }
            }
        } else {
            if (y < this.yPosition) {
                if (board[this.xPosition][y] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                // check if the destination spot is not empty and the piece in the destination spot is a black piece    
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    // add additional logic... for pieces in the way maybe...
                    return false;
                // check if the destination spot is not empty and the piece in the destination spot is a white piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    // add additional logic... for pieces in the way maybe...
                    return true;
                } else {
                    return false;
                }
            // trying to go right
            } else {
                if (board[this.xPosition][y] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                // check if the destination spot is not empty and the piece in the destination spot is a black piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    // add additional logic... for pieces in the way maybe...
                    return false;
                // check if the destination spot is not empty and the piece in the destination spot is a white piece
                } else if (board[this.xPosition][y] != null && board[this.xPosition][y].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    // add additional logic... for pieces in the way maybe...
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * Helper method to check if the piece can move up/down
     * @param x
     * @param y
     * @param board
     * @return
     */
    private boolean canMoveUpDown(int x, int y, Piece[][] board) {
        if (this.isWhite) {
            // rook trying to move up...
            if (x > this.xPosition) {
                if (board[x][this.yPosition] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return false;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // rook trying to move down...
                if (board[x][this.yPosition] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return false;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (x > this.xPosition) {
                if (board[x][this.yPosition] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return false;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                // rook trying to move down...
                if (board[x][this.yPosition] == null && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite == false && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return false;
                } else if (board[x][this.yPosition] != null && board[x][this.yPosition].isWhite && isPathClear(this.xPosition, this.yPosition, x, y, board) == null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * Helper method check for the clear path between current Piece and destination
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @return
     */
    private Piece isPathClear(int currentX, int currentY, int finalX, int finalY, Piece[][] board) {
        // check if we checking path up, path down, path left, or path right...
        //System.out.println("current y " + currentY + " final Y " + finalY);
        if (currentX == finalX && currentY < finalY) {
            // checking if moving right...
                //System.out.println("white rook moving right");
                int currentYPointer = currentY + 1;
                while (currentYPointer < finalY) {
                    if (board[currentX][currentYPointer] != null) {
                        return board[currentX][currentYPointer];
                    }
                    currentYPointer++;
                }
                return null;

        } else if (currentX == finalX && currentY > finalY) {
            // checking if moving left...
                //System.out.println("white rook moving left");
                int currentYPointer = currentY - 1;
                while (currentYPointer > finalY) {
                    if (board[currentX][currentYPointer] != null) {
                        return board[currentX][currentYPointer];
                    }
                    currentYPointer--;
                }
                return null;

        } else if (currentY == finalY && currentX < finalX) {
            // checking if moving up...
                //System.out.println("white rook moving up");
                int currentXPointer = currentX + 1;
                while (currentXPointer < finalX) {
                    if (board[currentXPointer][currentY] != null) {
                        return board[currentXPointer][currentY];
                    }
                    currentXPointer++;
                }
                return null;


        } else {
            // checking if moving down...
                //System.out.println("white rook moving down");
                int currentXPointer = currentX -1;
                while (currentXPointer > finalX) {
                    if (board[currentXPointer][currentY] != null) {
                        return board[currentXPointer][currentY];
                    }
                    currentXPointer--;
                }
                return null;
        }
    }
}