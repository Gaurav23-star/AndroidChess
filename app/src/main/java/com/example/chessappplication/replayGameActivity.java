package com.example.chessappplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class replayGameActivity extends AppCompatActivity {

    public static recordedGames recordedGames = new recordedGames();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_game);

        ListView recordGameListView = findViewById(R.id.recordGameListView);
        System.out.println(MainActivity.recordedGames.allGames.toString());

        Collections.sort(MainActivity.recordedGames.allGames, new Comparator<game>() {
            @Override
            public int compare(game o1, game o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });

        if(MainActivity.recordedGames != null){
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.recordedGames.getAllGamesName());
            recordGameListView.setAdapter(arrayAdapter);
        }
        recordGameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("position at clicked " + position);
                Intent intent = new Intent(replayGameActivity.this, replayChessActivity.class);
                intent.putExtra("gameReplayArray", MainActivity.recordedGames.getAllGames().get(position).getGameMoves());
                startActivity(intent);

            }
        });

    }
}