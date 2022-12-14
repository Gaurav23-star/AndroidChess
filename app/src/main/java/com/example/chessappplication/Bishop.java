package com.example.chessappplication;

/**
 * Class represents Piece Bishop, extends generic Piece class
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 *
 */
public class Bishop extends Piece {
    public Bishop(String name) {
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
        // bishops can move only diagonally in any direction.
        // this means it can move up and to left/right one spot OR it can move one down and to the left/right.
        // this process maybe repeated as many times to reach a specific final tile that is on a diagonal path
        // as mentioned above from the initial tile.
        
        // no pieces can be in the way of your initial and final tiles or it will have to kill it first.
        // the destination tile cannot have the same colored piece . (must be null or enemy piece)

        // here, we are checking if the destination coordinates are even within bounds of our board.
        if (x < 1 || x > 8 || y < 1 || y > 8) {
            return false;
        // if we are in bounds, then proceed...
            //check if the destination is diagonal
        } else if(Math.abs(x - this.xPosition) != Math.abs(y - this.yPosition)){
            return false;
        }else {
            //System.out.println("Checking bishop move");
            // because the bishop moves diagonally like an x, we have to determine which direction it's going,
            //  given the initial position of our bishop and final coordinates passed in.
            // we will deal with wB & bB separately... *** I DONT THINK SO AHHAAH ****
            
            // bishop is trying to move up/right diagonally...
            if (x > this.xPosition && y > this.yPosition) {
                //System.out.println("Checking bishop move 1");
                Piece obstacle = isPathClear(this.xPosition, this.yPosition, x, y, board, "up/right");
                if (obstacle == null) {
                    return true;
                } else {
                    return false;
                }
            // bishop is trying to move down/right diagonally...
            } else if (x < this.xPosition && y > this.yPosition) {
                //System.out.println("x is " + x + "y is " + y);
                //System.out.println("cuurentX is " + this.xPosition + "currentY is " + this.yPosition);

                //System.out.println("Checking bishop move 2");
                Piece obstacle = isPathClear(this.xPosition, this.yPosition, x, y, board, "down/right");
                if (obstacle == null) {
                    return true;
                } else {
                    return false;
                }
            // bishop is trying to move down/left diagonally...
            } else if (x < this.xPosition && y < this.yPosition) {
                //System.out.println("Checking bishop move 3");
                Piece obstacle = isPathClear(this.xPosition, this.yPosition, x, y, board, "down/left");
                if (obstacle == null) {
                    return true;
                } else {
                    return false;
                }
            // bishop is trying to move up/left diagonally...
            } else if (x > this.xPosition && y < this.yPosition) {
                //System.out.println("Checking bishop move 4");
                Piece obstacle = isPathClear(this.xPosition, this.yPosition, x, y, board, "up/left");
                if (obstacle == null) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    /**
     * Helper method to figure out the direction of Bishop movement
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @param direction
     * @return
     */
    private Piece isPathClear(int currentX, int currentY, int finalX, int finalY, Piece[][] board, String direction) {
        if (direction.equals("up/right")) {
            return isPathClearUpRight(currentX, currentY, finalX, finalY, board);
        } else if (direction.equals("down/right")) {
            return isPathClearDownRight(currentX, currentY, finalX, finalY, board);
        } else if (direction.equals("down/left")) {
            return isPathClearDownLeft(currentX, currentY, finalX, finalY, board);
        } else if (direction.equals("up/left")) {
            return isPathClearUpLeft(currentX, currentY, finalX, finalY, board);
        }
        return null;
    }

    /**
     * Helper method to check for clear path Up/right
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @return
     */
    private Piece isPathClearUpRight(int currentX, int currentY, int finalX, int finalY, Piece[][] board) {
        // 1:1 ratio for up 1 and right 1 move.... verifying
        if (Math.abs(finalX - currentX) == Math.abs(finalY - currentY)) {
            int currentXPointer = currentX + 1;
            int currentYPointer = currentY + 1;
            while (currentXPointer < finalX && currentYPointer < finalY) {

                if (board[currentXPointer][currentYPointer] != null) {
                    //System.out.println("Piece is " + board[currentXPointer][currentYPointer].name);
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer++;
                currentYPointer++;
            }
        }
        return null;
    }

    /**
     * Helper method to check for clear path down/right
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @return
     */
    private Piece isPathClearDownRight(int currentX, int currentY, int finalX, int finalY, Piece[][] board) {
        // 1:1 ratio for down 1 and right 1 move... verifying
        if (Math.abs(currentX - finalX) == Math.abs(finalY - currentY)) {
            int currentXPointer = currentX - 1;
            int currentYPointer = currentY + 1;
            //System.out.println("Current x1 " + currentXPointer);
            //System.out.println("Current Y1 " + currentYPointer);
            while (currentXPointer > finalX && currentYPointer < finalY) {
                //System.out.println("Current x " + currentXPointer);
                //System.out.println("Current Y " + currentYPointer);
                if (board[currentXPointer][currentYPointer] != null) {
                    //System.out.println("Piece is " + board[currentXPointer][currentYPointer].name);
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer--;
                currentYPointer++;
            }
        }
        return null;
    }

    /**
     * Helper method to check for clear path down/left
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @return
     */
    private Piece isPathClearDownLeft(int currentX, int currentY, int finalX, int finalY, Piece[][] board) {
        // 1:1 ratio for down 1 and left 1 move... verifying
        if (Math.abs(currentX - finalX) == Math.abs(currentY - finalY)) {
            int currentXPointer = currentX -1;
            int currentYPointer = currentY -1;
            while (currentXPointer > finalX && currentYPointer > finalY) {
                if (board[currentXPointer][currentYPointer] != null) {
                    //System.out.println("Piece is " + board[currentXPointer][currentYPointer].name);
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer--;
                currentYPointer--;
            }
        }
        return null;
    }

    /**
     * Helper method to check for clear path up/left
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @return
     */
    private Piece isPathClearUpLeft(int currentX, int currentY, int finalX, int finalY, Piece[][] board) {
        // 1:1 ratio for down 1 and left 1 move... verifying
        if (Math.abs(currentX - finalX) == Math.abs(currentY - finalY)) {
            int currentXPointer = currentX + 1;
            int currentYPointer = currentY - 1;
            while (currentXPointer < finalX && currentYPointer > finalY) {
                if (board[currentXPointer][currentYPointer] != null) {
                    //System.out.println("Piece is " + board[currentXPointer][currentYPointer].name);
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer++;
                currentYPointer--;
            }
        }
        return null;
    }
}