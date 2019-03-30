package com.example.samaksh.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class TwoPlayerMode extends AppCompatActivity {

    TextView textView;
    Button restartButton;
    int player = 1;
    int[] playerState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winConditions = new int[][]{{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 5 ,8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};
    boolean gameOver = false;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = getResources().getDisplayMetrics().density;
        int width = displayMetrics.widthPixels;
        int l = (int)(width*0.060/2);
        System.out.println(width/density);
        gridLayout.setPadding(l,l,l,l);

        textView = findViewById(R.id.textView);
        restartButton = findViewById(R.id.restartButton);
        textView.setText(R.string.Player1Move);
    }

    public void onClick(View view)
    {
        ImageView clicked = (ImageView)view;
        clicked.setTranslationY(-2000f);
        clicked.setEnabled(false);
        playerState[Integer.parseInt(clicked.getTag().toString())] = player;
        if(!gameOver) {
            if (player == 1) {
                clicked.setImageResource(R.drawable.x);
                player = 0;
                textView.setText(R.string.Player2Move);
            } else {
                clicked.setImageResource(R.drawable.o1);
                player = 1;
                textView.setText(R.string.Player1Move);
            }
        }
        clicked.animate().translationYBy(2000f).rotation(3600).setDuration(500);
        checkResult();
    }

    public void checkResult(){
        for (int[] winCondition:winConditions) {
            if (playerState[winCondition[0]] == playerState[winCondition[1]] && playerState[winCondition[0]] == playerState[winCondition[2]] && playerState[winCondition[0]] != 2) {
                gameOver = true;
                if (player == 0)
                    textView.setText(R.string.CrossWins);
                else
                    textView.setText(R.string.CircleWins);
            }
        }
        i++;
        if(i==9){
            textView.setText(R.string.Draw);
            gameOver = true;
        }
    }
    public void restart(View view){
        i=0;
        recreate();
    }
}
