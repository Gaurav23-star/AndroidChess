package com.example.chessappplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public  static recordedGames recordedGames = new recordedGames();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<ArrayList<String>> allGames = new ArrayList<>();
        System.out.println("agaurav");
    }

    public void playGame(View view) {
        Intent playGame = new Intent(this, playGameActivity.class);
        startActivity(playGame);
    }

    public void replayGames(View view) {
        Intent replayGames = new Intent(this, replayGameActivity.class);
        startActivity(replayGames);
    }

}