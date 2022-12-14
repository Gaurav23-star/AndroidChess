package com.example.chessappplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class replayChessActivity extends AppCompatActivity {

    ImageView br;
    ImageView bb;
    ImageView bk;
    ImageView bq;
    ImageView bn;
    ImageView br1;
    ImageView bn1;
    ImageView bb1;
    ImageView bp1;
    ImageView bp2;
    ImageView bp3;
    ImageView bp4;
    ImageView bp5;
    ImageView bp6;
    ImageView bp7;
    ImageView bp8;


    ImageView wr;
    ImageView wb;
    ImageView wk;
    ImageView wq;
    ImageView wn;
    ImageView wr1;
    ImageView wn1;
    ImageView wb1;
    ImageView wp1;
    ImageView wp2;
    ImageView wp3;
    ImageView wp4;
    ImageView wp5;
    ImageView wp6;
    ImageView wp7;
    ImageView wp8;
    TextView turnLable;
    TextView gameOverLable;
    Button nextButton;
    private static int startingX;
    private static int startingY;
    private static int differenceX;
    private static int differenceY;
    int counter = 0;
    private ImageView[] whitePieces;
    private ImageView[] blackPieces;
    boolean whitesMove = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_chess);
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> moves = bundle.getStringArrayList("gameReplayArray");
        System.out.println(moves.toString());

        turnLable = findViewById(R.id.turnLable);
        nextButton = findViewById(R.id.nextButton);
        gameOverLable = findViewById(R.id.gameOverLable);

        br = findViewById(R.id.br);
        bb = findViewById(R.id.bb);
        bk = findViewById(R.id.bk);
        bq = findViewById(R.id.bq);
        bn = findViewById(R.id.bn);
        br1 = findViewById(R.id.br1);
        bn1 = findViewById(R.id.bn1);
        bb1 = findViewById(R.id.bb1);
        bp1 = findViewById(R.id.bp1);
        bp2 = findViewById(R.id.bp2);
        bp3 = findViewById(R.id.bp3);
        bp4 = findViewById(R.id.bp4);
        bp5 = findViewById(R.id.bp5);
        bp6 = findViewById(R.id.bp6);
        bp7 = findViewById(R.id.bp7);
        bp8 = findViewById(R.id.bp8);


        wr = findViewById(R.id.wr);
        wb = findViewById(R.id.wb);
        wk = findViewById(R.id.wk);
        wq = findViewById(R.id.wq);
        wn = findViewById(R.id.wn);
        wr1 = findViewById(R.id.wr1);
        wn1 = findViewById(R.id.wn1);
        wb1 = findViewById(R.id.wb1);
        wp1 = findViewById(R.id.wp1);
        wp2 = findViewById(R.id.wp2);
        wp3 = findViewById(R.id.wp3);
        wp4 = findViewById(R.id.wp4);
        wp5 = findViewById(R.id.wp5);
        wp6 = findViewById(R.id.wp6);
        wp7 = findViewById(R.id.wp7);
        wp8 = findViewById(R.id.wp8);
        blackPieces = new ImageView[]{
                br,
                bb,
                bk,
                bq,
                bn,
                br1,
                bn1,
                bb1,
                bp1,
                bp2,
                bp3,
                bp4,
                bp5,
                bp6,
                bp7,
                bp8,
        };

        whitePieces= new ImageView[]{
                wr,
                wb,
                wk,
                wq,
                wn,
                wr1,
                wn1,
                wb1,
                wp1,
                wp2,
                wp3,
                wp4,
                wp5,
                wp6,
                wp7,
                wp8,
        };


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter == moves.size()){
                    nextButton.setVisibility(View.INVISIBLE);
                    gameOverLable.setVisibility(View.VISIBLE);
                    return;
                }
                String currentMove = moves.get(counter);
                int x = Character.getNumericValue(currentMove.charAt(0));
                int y = Character.getNumericValue(currentMove.charAt(1));
                int destinationX = Character.getNumericValue(currentMove.charAt(2));
                int destinationY = Character.getNumericValue(currentMove.charAt(3));
                Log.i("xy xy", x + " " + y + " " + destinationX + " " + destinationY);

                x = x * differenceX + startingX;
                y = y * differenceY + startingY;
                //y -=160;
                y -= differenceY*2;
                Log.i("xy xy", x + " "+ y + " " + destinationX + " "+ destinationY);

                destinationX = destinationX * differenceX + startingX;
                destinationY = destinationY * differenceY + startingY;
                //destinationY -= 160;
                destinationY -= differenceY*2;
                Log.i("xy xy", x + " " + y + " " + destinationX + " " + destinationY);
                ImageView piece;
                if(whitesMove){
                    piece = getPieceAt(whitePieces, x, y);
                    piece.setX(destinationX);
                    piece.setY(destinationY);
                    whitesMove = false;
                    turnLable.setText("Black's Move");
                }else{
                    piece = getPieceAt(blackPieces, x, y);
                    piece.setX(destinationX);
                    piece.setY(destinationY);
                    whitesMove = true;
                    turnLable.setText("White's Move");
                }
                counter++;
                if(counter == moves.size()){
                    nextButton.setVisibility(View.INVISIBLE);
                    gameOverLable.setVisibility(View.VISIBLE);
                }

            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            int[] location = new int[2];
            br.getLocationOnScreen(location);
            startingX = location[0];
            startingY = location[1];

            bn.getLocationOnScreen(location);
            differenceX = location[0] - startingX;
            bp1.getLocationOnScreen(location);
            differenceY = location[1]-startingY;

            Log.i("start x", startingX + " ");
            Log.i("start y", startingY + " ");

            Log.i("difference x", differenceX + " ");
            Log.i("difference y", differenceY + " ");
            //Log.i("imageViewxy", imageView.getX() + " "+ imageView.getY());

        }
    }
    private ImageView getPieceAt(ImageView[] pieces, int x, int y){
        for(ImageView piece: pieces){
            Log.i("piece xy ", " " + piece.getX() + " " + piece.getY() + piece.getContentDescription());
            if(piece.getX() == x && piece.getY() == y){
                return piece;
            }
        }
        return null;
    }
}