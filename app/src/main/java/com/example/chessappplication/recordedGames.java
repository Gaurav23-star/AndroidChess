package com.example.chessappplication;

import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class recordedGames {
    private ArrayList<game> allGames = new ArrayList<>();

    public ArrayList<game> getAllGames() {
        return allGames;
    }
    public ArrayList<String> getAllGamesName(){
        ArrayList<String> names = new ArrayList<>();
        for(game game : allGames){
            Calendar date = game.getDate();
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(date.getTime());
            names.add(game.getTitle() + "\t" + currentDate);
        }
        return names;
    }

    public void setAllGames(ArrayList<game> allGames) {
        this.allGames = allGames;
    }

    public void addGame(game game){
        allGames.add(game);
    }
}
