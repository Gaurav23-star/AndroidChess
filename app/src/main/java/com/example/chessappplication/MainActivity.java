package com.example.chessappplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static recordedGames recordedGames = new recordedGames();
    Button closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        closeButton = findViewById(R.id.saveCloseButton);
        getData();
        System.out.println(recordedGames);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
    }
        public void playGame (View view){
            Intent playGame = new Intent(this, playGameActivity.class);
            startActivity(playGame);
        }

        public void replayGames (View view){
            Intent replayGames = new Intent(this, replayGameActivity.class);
            startActivity(replayGames);
        }

        public void saveData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

            Gson gson = new Gson();
            String json = gson.toJson(recordedGames.getAllGames());
            editor.putString("chess_data", json);
        }
        public void getData(){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Data", MODE_PRIVATE);

            Gson gson = new Gson();
            String json = sharedPreferences.getString("chess_data", null);
            Type type = new TypeToken<ArrayList<game>>(){
            }.getType();
            ArrayList<game> tempList = gson.fromJson(json, type);
            System.out.println("data is " + tempList);
            if(tempList != null){
                recordedGames.allGames = tempList;
            }
        }
    }
