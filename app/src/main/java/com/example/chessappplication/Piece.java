package com.example.chessappplication;

/**
 * The class Piece represents generic Piece
 *
 * @author Gauravkumar Patel netid: gjp81
 * @author Sami Munir netid: sm2246
 */
public abstract class Piece {
    public String name;
    public int xPosition;
    public int yPosition;
    public boolean isWhite;
    public boolean hasMovedBefore = false;

    /**
     *
     * @param name name of the piece
     */
    public Piece(String name) {
        this.name = name;
        if (name.charAt(0) == 'w') {
            this.isWhite = true;
        } else {
            this.isWhite = false;
        }
    }

    /**
     * x position setter method
     * @param x x position of piece
     */
    public void setXPosition(int x) {
        this.xPosition = x;
    }

    /**
     * y position setter method
     * @param y
     */
    public void setYPosition(int y) {
        this.yPosition = y;
    }

    /**
     * Method to check if the intended move is legal
     * @param x intended x position
     * @param y intended y position
     * @param board chess board
     * @return returns true if move is legal, false otherwise
     */
    public abstract boolean isInputMoveValid(int x, int y, Piece[][] board);
}