package com.example.chessappplication;

import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class recordedGames implements Serializable {
    public ArrayList<game> allGames = new ArrayList<>();

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
