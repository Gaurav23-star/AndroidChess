package com.example.chessappplication;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class playGameActivity extends AppCompatActivity implements View.OnClickListener {

    EditText gameNameInput;

    private static final int SQUARE_SIZE = 40;
    private static final int BOARD_WIDTH = 8 * SQUARE_SIZE;
    private static final int BOARD_HEIGHT = 8 * SQUARE_SIZE;
    private static int startingX;
    private static int startingY;
    private static int differenceX;
    private static int differenceY;
    private ImageView lastClickedPiece = null;
    public ArrayList<String> gameHistory = new ArrayList<>();
    private static final int[] setOfPieces = {
            R.drawable.br,
            R.drawable.bn,
            R.drawable.bb,
            R.drawable.bq,
            R.drawable.bk,
            R.drawable.bb,
            R.drawable.bn,
            R.drawable.br,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.bp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wp,
            R.drawable.wr,
            R.drawable.wn,
            R.drawable.wb,
            R.drawable.wq,
            R.drawable.wk,
            R.drawable.wb,
            R.drawable.wn,
            R.drawable.wr,
    };
    Chess chessGame = new Chess();


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
    private ImageView[] whitePieces;
    private ImageView[] blackPieces;

    TextView turnLable;
    ScrollView blacksCapturedPieces;
    ScrollView whitesCapturedPieces;
    ImageView imageView;
    LinearLayout blacksCaputred;
    boolean whitesMove = true;
    Button aiButton;
    Button drawButton;
    Button resignButton;
    TextView warningLable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        chessGame.printBoard(chessGame.board);
        turnLable = findViewById(R.id.turnLable);
        //blacksCapturedPieces = findViewById(R.id.blacksCapturedPieces);
        aiButton = findViewById(R.id.aiButton);

        drawButton = findViewById(R.id.drawButton);
        resignButton = findViewById(R.id.resignButton);
        warningLable = findViewById(R.id.warningLable);
        warningLable.setVisibility(View.INVISIBLE);
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

        br.setOnClickListener(this);
        bb.setOnClickListener(this);
        bk.setOnClickListener(this);
        bq.setOnClickListener(this);
        bn.setOnClickListener(this);
        br1.setOnClickListener(this);
        bn1.setOnClickListener(this);
        bb1.setOnClickListener(this);
        bp1.setOnClickListener(this);
        bp2.setOnClickListener(this);
        bp3.setOnClickListener(this);
        bp4.setOnClickListener(this);
        bp5.setOnClickListener(this);
        bp6.setOnClickListener(this);
        bp7.setOnClickListener(this);
        bp8.setOnClickListener(this);


        wr.setOnClickListener(this);
        wb.setOnClickListener(this);
        wk.setOnClickListener(this);
        wq.setOnClickListener(this);
        wn.setOnClickListener(this);
        wr1.setOnClickListener(this);
        wn1.setOnClickListener(this);
        wb1.setOnClickListener(this);
        wp1.setOnClickListener(this);
        wp2.setOnClickListener(this);
        wp3.setOnClickListener(this);
        wp4.setOnClickListener(this);
        wp5.setOnClickListener(this);
        wp6.setOnClickListener(this);
        wp7.setOnClickListener(this);
        wp8.setOnClickListener(this);

        br.setOnTouchListener(onTouchListener);
        bb.setOnTouchListener(onTouchListener);
        bk.setOnTouchListener(onTouchListener);
        bq.setOnTouchListener(onTouchListener);
        bn.setOnTouchListener(onTouchListener);
        br1.setOnTouchListener(onTouchListener);
        bn1.setOnTouchListener(onTouchListener);
        bb1.setOnTouchListener(onTouchListener);
        bp1.setOnTouchListener(onTouchListener);
        bp2.setOnTouchListener(onTouchListener);
        bp3.setOnTouchListener(onTouchListener);
        bp4.setOnTouchListener(onTouchListener);
        bp5.setOnTouchListener(onTouchListener);
        bp6.setOnTouchListener(onTouchListener);
        bp7.setOnTouchListener(onTouchListener);
        bp8.setOnTouchListener(onTouchListener);


        wr.setOnTouchListener(onTouchListener);
        wb.setOnTouchListener(onTouchListener);
        wk.setOnTouchListener(onTouchListener);
        wq.setOnTouchListener(onTouchListener);
        wn.setOnTouchListener(onTouchListener);
        wr1.setOnTouchListener(onTouchListener);
        wn1.setOnTouchListener(onTouchListener);
        wb1.setOnTouchListener(onTouchListener);
        wp1.setOnTouchListener(onTouchListener);
        wp2.setOnTouchListener(onTouchListener);
        wp3.setOnTouchListener(onTouchListener);
        wp4.setOnTouchListener(onTouchListener);
        wp5.setOnTouchListener(onTouchListener);
        wp6.setOnTouchListener(onTouchListener);
        wp7.setOnTouchListener(onTouchListener);
        wp8.setOnTouchListener(onTouchListener);

        ImageView chessBoard = findViewById(R.id.chessBoard);
        Log.i("totalChild", constraintLayout.getChildCount() + " ");

        for(int i = 0; i < constraintLayout.getChildCount(); i++){
            Log.i("child at " + i, constraintLayout.getChildAt(i) + " ");

        }



        //View sample = findViewById(R.id.sampleBox);
        //ImageView sampleImageDrop = findViewById(R.id.sampleImageDrop);

        //wp2.setOnLongClickListener(longClickListener);
        //wp2.setOnDragListener(onDragListener);
        //sampleImageDrop.setOnDragListener(onDragListener);

        aiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String randomMove = chessGame.getRandomMove();
                int x = Character.getNumericValue(randomMove.charAt(1));
                int y = Character.getNumericValue(randomMove.charAt(0));
                int destinationX = Character.getNumericValue(randomMove.charAt(3));
                int destinationY = Character.getNumericValue(randomMove.charAt(2));
                int imgX, imgY, imgDesX, imgDesY;
                Log.i("string to add is from black savex ", y+"");
                Log.i("string to add is from black savey ", x+"");
                imgX = x-1 % 8;
                imgY = (8 - (y % 8))%8;
                Log.i("string to add is from black savex ", imgX+"");
                Log.i("string to add is from black savey ", imgY+"");
                imgX = imgX * differenceX + startingX;
                imgY = imgY * differenceY + startingY;
                //imgY -=160;
                imgY -= differenceY*2;


                imgDesX = destinationX;
                imgDesY = destinationY;
                Log.i("string to add is from black savedesx ", imgDesX+"");
                Log.i("string to add is from black savedesy ", imgDesY+"");
                imgDesX = imgDesX-1 % 8;
                imgDesY = (8 - (imgDesY % 8))%8;
                Log.i("string to add is from black savedesx ", imgDesX+"");
                Log.i("string to add is from black savedesy ", imgDesY+"");
                imgDesX = imgDesX * differenceX + startingX;
                imgDesY = imgDesY * differenceY + startingY;
                //imgDesY -= 160;
                imgDesY -= differenceY*2;
                Log.i("string to add is from black savex ", imgX+"");
                Log.i("string to add is from black savey ", imgY+"");
                Log.i("string to add is from black savedesx ", imgDesX+"");
                Log.i("string to add is from black savedesy ", imgDesY+"");

                int savex = Math.abs((int)((imgX - startingX)/differenceX));
                int savey = Math.abs((int)(((imgY+differenceY*2) - startingY)/differenceY));
                int desx = Math.abs((int)((imgDesX - startingX)/differenceX));
                int desy = Math.abs((int)(((imgDesY+differenceY*2) - startingY)/differenceY));

                if(whitesMove){
                    int answer = chessGame.whiteMove(y, x, destinationY, destinationX);

                    Log.i("answer is ", answer +" ");
                    if(answer != -1){
                        if(answer == 2){
                            castleRook(whitePieces);
                        }
                        String currentMove = savex + "" + savey + "" + desx + "" + desy;
                        Log.i("string to add is from white", currentMove);
                        gameHistory.add(currentMove);
                        ImageView piece = getPieceAt(whitePieces, imgX, imgY);
                        piece.setX(imgDesX);
                        piece.setY(imgDesY);
                        captureOpositePlayer(blackPieces, imgDesX, imgDesY, true);
                        if(answer == 4){
                            turnLable.setText("White Wins!");
                            saveGame("Checkmate, White Wins!");

                        }
                        if(answer == 6){
                            warningLable.setVisibility(View.VISIBLE);
                            warningLable.setText("Black is in Check");
                        }
                        whitesMove = false;
                        turnLable.setText("Black's Move");

                    }

                }else if(!whitesMove){
                    int answer = chessGame.blackMove(y, x, destinationY, destinationX);
                    Log.i("answer is ", answer +" ");
                    if(answer != -1){
                        if(answer == 2){
                            castleRook(blackPieces);
                        }
                        Log.i("string to add is from black savex ", savex+"");
                        Log.i("string to add is from black savey ", savey+"");
                        Log.i("string to add is from black savedesx ", desx+"");
                        Log.i("string to add is from black savedesy ", desy+"");
                        String currentMove = savex + "" + savey + "" + desx + "" + desy;
                        Log.i("string to add is from black ", currentMove);
                        gameHistory.add(currentMove);
                        ImageView piece = getPieceAt(blackPieces, imgX, imgY);
                        piece.setX(imgDesX);
                        piece.setY(imgDesY);
                        captureOpositePlayer(whitePieces, imgDesX, imgDesY, false);
                        if(answer == 4){
                            turnLable.setText("White Wins!");
                            saveGame("Checkmate, White Wins!");

                        }
                        if(answer == 6){
                            warningLable.setText("White is in Check");
                            warningLable.setVisibility(View.VISIBLE);
                        }
                        whitesMove = true;
                        turnLable.setText("White's Move");

                    }

                }



            }
        });

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGame("Draw!! Do you want to save this game");
            }
        });

        resignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGame("Player has resigned! Do you wan to save this game");
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


    @Override
    public void onClick(View v) {
        if(lastClickedPiece != null){
            lastClickedPiece.setBackground(null);
        }
        ImageView imageView = (ImageView) v;
        imageView.setBackgroundColor(Color.DKGRAY);
        int x, y;
        int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        x = (location[0] - startingX)/differenceX;
        y = (location[1] - startingY)/differenceY;
        x = x+1 %8;
        y = 8 - (y % 8);
        Log.i("X and Y coordinates are", x + " " +y);
        Log.i("X and Y coordinates", imageView.getX() + " " +imageView.getBottom());
        //int[] location = new int[2];
        imageView.getLocationOnScreen(location);
        Log.i("X and Y", location[0] + " " +location[1]);

        lastClickedPiece = imageView;

    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if(lastClickedPiece != null){
                lastClickedPiece.setBackground(null);
            }
            ImageView imageView = (ImageView)v;
            imageView.setBackgroundColor(Color.DKGRAY);
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder,v,0);
            return true;
        }
    };

    View.OnDragListener onDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            Log.i("DragEvent", "" + dragEvent);
            int x, y;
            x = ((int)event.getX() - startingX)/differenceX;
            y = ((int)event.getY() - startingY)/differenceY;

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.BLUE);
                    Log.i("DragEnterd", "Drag Entered");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("DragExited", "Drag Exited");
                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("DragDropped", "Drag Dropper");
                    Log.i("drop x and y",   event.getX() + " " + event.getY());
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("x and y",   event.getX() + "" + event.getY());
                    break;


            }

            return true;

        }
    };


    int currentX = 0, currentY = 0;
    int x = 0, y = 0;
    int saveX, saveY, saveDestinationX, saveDestinationY;
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            warningLable.setVisibility(View.INVISIBLE);

            ImageView imageView = (ImageView) v;
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                currentX = (int)((event.getRawX() - startingX)/differenceX);
                currentY = (int)((event.getRawY() - startingY)/differenceY);
                saveX = currentX;
                saveY = currentY;
                currentX = currentX+1 %8;
                currentY = 8 - (currentY % 8);

            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                x = (int)((event.getRawX() - startingX)/differenceX);
                y = (int)((event.getRawY() - startingY)/differenceY);


                int destinationX,destinationY;
                destinationX = x;
                destinationY = y;
                saveDestinationX = x;
                saveDestinationY = y;
                Log.i("destination x, y", destinationY +" " + destinationX);
                destinationX = destinationX+1 %8;
                destinationY = 8 - (destinationY % 8);

                Log.i("current x, y", currentY +" " + currentX);
                Log.i("destination x, y", destinationY +" " + destinationX);

                //returns 2 for castling
                //return 4 for checkmate

                if(whitesMove){
                    chessGame.getRandomMove();
                    disableAll(blackPieces);

                    int answer = chessGame.whiteMove(currentY, currentX, destinationY, destinationX);
                    Log.i("answer is ", answer +" ");
                    if(answer != -1){
                        String currentMove;

                        //castling
                        if(answer == 2){
                            castleRook(whitePieces);
                        }


                        currentMove = saveX + "" + saveY + "" + saveDestinationX + "" + saveDestinationY;
                        gameHistory.add(currentMove);
                        //move piece
                        x = x * differenceX + startingX;
                        y = y * differenceY + startingY;
                        //y -= 160;
                        y -= differenceY*2;
                        imageView.setX(x);
                        imageView.setY(y);
                        captureOpositePlayer(blackPieces, x, y, true);
                        whitesMove = false;
                        enableAll(blackPieces);
                        turnLable.setText("Black's Move");
                        chessGame.whiteTurn = false;
                        chessGame.blackTurn = true;
                        if(answer == 4){
                            turnLable.setText("White Wins!");
                            saveGame("Checkmate, White Wins!");

                        }
                        if(answer == 6){
                            warningLable.setVisibility(View.VISIBLE);
                            warningLable.setText("Black is in Check");
                        }

                    }
                    else {
                        warningLable.setVisibility(View.VISIBLE);
                        warningLable.setText("Illegal Move, Try Again");
                        x =0;
                        y=0;
                        currentX =0;
                        currentY=0;
                    }


                }
                else if(!whitesMove){
                    chessGame.getRandomMove();
                    disableAll(whitePieces);

                    int answer = chessGame.blackMove(currentY, currentX, destinationY, destinationX);
                    Log.i("answer is ", answer +" ");
                    if(answer != -1){
                        //castling
                        if(answer == 2){
                            castleRook(blackPieces);
                        }
                        String currentMove = saveX + "" + saveY + "" + saveDestinationX + "" + saveDestinationY;
                        gameHistory.add(currentMove);
                        //move piece
                        x = x * differenceX + startingX;
                        y = y * differenceY + startingY;
                        //y -= 160;
                        y -= differenceY*2;
                        imageView.setX(x);
                        imageView.setY(y);
                        captureOpositePlayer(whitePieces, x, y, false);
                        whitesMove = true;
                        enableAll(whitePieces);
                        turnLable.setText("White's Move");
                        chessGame.whiteTurn = true;
                        chessGame.blackTurn = false;
                        if(answer == 4){
                            turnLable.setText("Black Wins!");
                            saveGame("Checkmate, Black Wins!");

                        }
                        if(answer == 6){
                            warningLable.setVisibility(View.VISIBLE);
                            warningLable.setText("White is in Check");
                        }
                    }
                    else {
                        warningLable.setVisibility(View.VISIBLE);
                        warningLable.setText("Illegal Move, Try Again");
                        x =0;
                        y=0;
                        currentX =0;
                        currentY=0;
                    }
                }


            }
            System.out.println(gameHistory.toString());
            return true;
        }
    };

    private void disableAll(ImageView[] pieces){
        for(ImageView piece: pieces){
            piece.setEnabled(false);
        }
    }
    private void enableAll(ImageView[] pieces){
        for(ImageView piece: pieces){
            piece.setEnabled(true);
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

    private void captureOpositePlayer(ImageView[] pieces, int x, int y, boolean isWhite){

        for(ImageView piece: pieces){
            if(piece.getX() == x && piece.getY() == y){
                piece.setVisibility(View.GONE);
                return;
            }
        }

    }
    private void castleRook(ImageView[] pieces){
        int savex, savey, savedestinationx, savedestinationy;
        int castleRookX, castleRookY, castleDestinationX, castleDestinationY;
        castleRookX = chessGame.castleRookY;
        castleRookY = chessGame.castleRookX;
        castleDestinationX = chessGame.castleDestinationY;
        castleDestinationY = chessGame.castleDestinationX;
        Log.i("castle xy ", " " + castleRookX + castleRookY + castleDestinationX + castleDestinationY +" ");
        castleRookX = castleRookX-1 % 8;
        castleRookY = (8 - (castleRookY % 8))%8;
        savex = castleRookX;
        savey = castleRookY;
        Log.i("castle xy ", " " + castleRookX + castleRookY + castleDestinationX + castleDestinationY +" ");
        castleRookX = castleRookX * differenceX + startingX;
        castleRookY = castleRookY * differenceY + startingY;
        //castleRookY -=160;
        castleRookY -= differenceY *2;

        Log.i("castle image ", " " + castleRookX + " " + castleRookY);
        ImageView rook = getPieceAt(pieces, castleRookX, castleRookY);
        castleDestinationX = castleDestinationX-1 % 8;
        castleDestinationY = (8 - (castleDestinationY % 8))%8;
        savedestinationx = castleDestinationX;
        savedestinationy = castleDestinationY;
        String saveMove = savex + "" + savey + "" + savedestinationx + "" + savedestinationy;
        castleDestinationX = castleDestinationX * differenceX + startingX;
        castleDestinationY = castleDestinationY * differenceY + startingY;
        //castleDestinationY -= 160;
        castleDestinationY -= differenceY*2;
        rook.setX(castleDestinationX);
        rook.setY(castleDestinationY);
    }

    public void saveGame(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Game");
        builder.setMessage(message + " " + "Enter name for this game to save");

        gameNameInput = new EditText(this);
        builder.setView(gameNameInput);
        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gameName = gameNameInput.getText().toString();
                game savedGame = new game();
                Calendar todaysDate = Calendar.getInstance();
                savedGame.setDate(todaysDate);
                savedGame.setTitle(gameName);
                savedGame.setGameMoves(gameHistory);
                MainActivity.recordedGames.addGame(savedGame);
                finish();

            }
        });
        builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();

            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }

}

























