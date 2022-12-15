package com.example.chessappplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class game {
    private String title;
    private Calendar date;
    private ArrayList<String> gameMoves = new ArrayList<>();

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getGameMoves() {
        return gameMoves;
    }

    public void setGameMoves(ArrayList<String> gameMoves) {
        this.gameMoves = gameMoves;
    }
}
