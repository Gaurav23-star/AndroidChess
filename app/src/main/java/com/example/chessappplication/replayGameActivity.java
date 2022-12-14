package com.example.chessappplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class replayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_game);

        ListView recordGameListView = findViewById(R.id.recordGameListView);

        if(MainActivity.recordedGames != null){
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.recordedGames.getAllGamesName());
            recordGameListView.setAdapter(arrayAdapter);
        }
        recordGameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position at clicked " + position);
                Intent intent = new Intent(replayGameActivity.this, replayChessActivity.class);
                intent.putExtra("gameReplayArray", MainActivity.recordedGames.getAllGames().get(position).getGameMoves());
                startActivity(intent);

            }
        });

    }
}