package com.example.chessappplication;

/**
 * Class represents Piece King, extends generic Piece class
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 *
 */
public class King extends Piece {
    public boolean isKingInCheck;
    public boolean castledBefore;
    public King(String name) {
        super(name);
    }

    /**
     * Method to check if the intended move is legal
     * returns true if the path between current piece and its destination is clear
     * and the if there is a piece on the destination square, then it is of opposite color
     * @param x intended x position
     * @param y intended y position
     * @param board chess board
     * @return
     */
    @Override
    public boolean isInputMoveValid(int x, int y, Piece[][] board) {
        if (x < 1 || x > 8 || y < 1 || y > 8) {
            return false;
        } else {
            //System.out.println("King X and Y is " + x + y);
            //System.out.println("King Current x and y is "+ this.xPosition + this.yPosition);
            // king is trying to  move up 1 spot...
            if (x - this.xPosition == 1 && y == this.yPosition) {
                //System.out.println("King moving up");
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move 1 spot diagonally to the right (up/right)
            } else if (x - this.xPosition == 1 && y - this.yPosition == 1) {
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving up/right ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move right 1 spot...
            } else if (x == this.xPosition && y - this.yPosition == 1) {
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving right ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move one spot diagonally to the right (down/right)
            } else if (x + 1 == this.xPosition && y - this.yPosition == 1) {
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving down/right ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move 1 spot down
            } else if (x + 1 == this.xPosition && y == this.yPosition) {
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving down ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move 1 spot diagonally down to the left (down/left)
            } else if (x + 1 == this.xPosition && y + 1 == this.yPosition) {
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving down/left ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move 1 spot left
            } else if (x == this.xPosition && y + 1 == this.yPosition) {
                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving left ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            // king is trying to move 1 spot diagonally up to the left (up/left)
            } else if (x - this.xPosition == 1 && y + 1 == this.yPosition) {

                Piece obstacle = isPathClear(x, y, board);
                //System.out.println("King moving up/left ");
                //System.out.println("obstacle is " + obstacle);
                if (obstacle == null) {
                    return true;
                } else {
                    if (this.isWhite ^ obstacle.isWhite) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
         }
    }

    /**
     * Helper method to check if the path is clear between current piece and destination square
     * @param x
     * @param y
     * @param board
     * @return
     */
    private Piece isPathClear(int x, int y, Piece[][] board) {
        return board[x][y];
    }
}