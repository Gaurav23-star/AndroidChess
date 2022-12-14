package com.example.chessappplication;

/**
 * Class represents Piece Queen, extends generic Piece class
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 *
 */
public class Queen extends Piece {

    /**
     *
     * @param name name of the queen
     */
    public Queen(String name) {
        super(name);
    }

    /**
     * Method to check if the intended move is legal
     * returns true if the path between current piece and its destination is clear
     * and the if there is a piece on the destination square, then it is of opposite color
     * @param x intended x position
     * @param y intended y position
     * @param board chess board
     * @return returns true if move is legal, false otherwise
     */

    //returns true if the path between current piece and its destination is clear
    //and the if there is a piece on the destination square, then it is of opposite color
    @Override
    public boolean isInputMoveValid(int x, int y, Piece[][] board) {
        if(x > 8 || x < 1 || y > 8 || y < 1){
            return false;
        }
        else{
            // queen is trying to move up/right diagonally...
            if (x > this.xPosition && y > this.yPosition) {
                Piece obstacle = isPathClearDiagonally(this.xPosition, this.yPosition, x, y, board, "up/right");
                if (obstacle == null) {
                    return isOppositePiecesAtDestination(x, y, board);
                } else {
                    return false;
                }
            }
            // queen is trying to move horizontally right or left
            else if(x == this.xPosition && (y > this.yPosition || y < this.yPosition)) {
                return canMoveLeftRight(x, y, board);
            }
            //queen is trying to move vertically up or down
            else if(y == this.yPosition && (x > this.xPosition || x < this.xPosition)){
                return canMoveUpDown(x, y, board);
            }
            // queen is trying to move down/right diagonally...
            else if (x < this.xPosition && y > this.yPosition) {
                Piece obstacle = isPathClearDiagonally(this.xPosition, this.yPosition, x, y, board, "down/right");
                if (obstacle == null) {
                    return isOppositePiecesAtDestination(x, y, board);
                } else {
                    return false;
                }
                // queen is trying to move down/left diagonally...
            } else if (x < this.xPosition && y < this.yPosition) {
                Piece obstacle = isPathClearDiagonally(this.xPosition, this.yPosition, x, y, board, "down/left");
                if (obstacle == null){
                    //path is clear and destination piece is opposite color
                    return isOppositePiecesAtDestination(x, y, board);
                } else {
                    return false;
                }
                // queen is trying to move up/left diagonally...
            } else if (x > this.xPosition && y < this.yPosition) {
                Piece obstacle = isPathClearDiagonally(this.xPosition, this.yPosition, x, y, board, "up/left");
                if (obstacle == null) {
                    return isOppositePiecesAtDestination(x, y, board);
                } else {
                    return false;
                }
            }
            return false;
        }
        }

    /**
     * Checks if the destination square has the opposite players piece, or it is empty
     * returns boolean value
     * @param x
     * @param y
     * @param board
     * @return
     */

    //check if the destination square has the opposite players piece
    private boolean isOppositePiecesAtDestination(int x , int y, Piece[][] board){
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

    /**
     * Helper method to check for clear path diagonally
     * @param currentX
     * @param currentY
     * @param finalX
     * @param finalY
     * @param board
     * @param direction
     * @return
     */
    private Piece isPathClearDiagonally(int currentX, int currentY, int finalX, int finalY, Piece[][] board, String direction) {
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
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer++;
                currentYPointer++;
            }
            return null;
        }
        else{
            return board[currentX][currentY];
        }
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
            while (currentXPointer > finalX && currentYPointer < finalY) {
                if (board[currentXPointer][currentYPointer] != null) {
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer--;
                currentYPointer++;
            }
            return null;
        }
        else{
            return board[currentX][currentY];
        }
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
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer--;
                currentYPointer--;
            }
            return null;
        }
        else{
            return board[currentX][currentY];
        }

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
            int currentXPointer = currentX +1;
            int currentYPointer = currentY -1;
            while (currentXPointer < finalX && currentYPointer > finalY) {
                if (board[currentXPointer][currentYPointer] != null) {
                    return board[currentXPointer][currentYPointer];
                }
                currentXPointer++;
                currentYPointer--;
            }
            return null;
        }
        else{
            return board[currentX][currentY];
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
        if (currentX == finalX && currentY < finalY) {
            // checking if moving right...
            //System.out.println("white queen moving right");
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
            //System.out.println("white queen moving left");
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
            //System.out.println("white queen moving up");
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
            //System.out.println("white queen moving down");
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
}