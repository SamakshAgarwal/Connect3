package com.example.samaksh.connect3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.GridLayout;
public class MainActivity extends AppCompatActivity {

    int player = 1;
    int[] playerState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winConditions = new int[][]{{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 5 ,8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};
    boolean gameOver = false;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = getResources().getDisplayMetrics().density;
        int width = displayMetrics.widthPixels;
        int l = (int)(width*0.057/2);
        System.out.println(width/density);
        gridLayout.setPadding(l,l,l,l);
    }

    @SuppressLint("WrongConstant")
    public void onClick(View view)
    {
        TextView textView = findViewById(R.id.textView);
        Button restartButton = findViewById(R.id.restartButton);
        ImageView clicked = (ImageView)view;
        clicked.setTranslationY(-2000f);
        clicked.setEnabled(false);
        playerState[Integer.parseInt(clicked.getTag().toString())] = player;
        if(!gameOver) {
            if (player == 1) {
                clicked.setImageResource(R.drawable.x);
                player = 0;
            } else {
                clicked.setImageResource(R.drawable.o1);
                player = 1;
            }
        }
        clicked.animate().translationYBy(2000f).rotation(3600).setDuration(500);

        for (int[] winCondition:winConditions) {
            if (playerState[winCondition[0]] == playerState[winCondition[1]] && playerState[winCondition[0]] == playerState[winCondition[2]] && playerState[winCondition[0]] != 2) {
                gameOver = true;
                if (player == 0)
                    textView.setText("Cross Wins");
                else
                    textView.setText("Circle Wins");
                restartButton.setVisibility(1);
            }
        }
        i++;
        if(i==9){
            textView.setText("Draw");
            restartButton.setVisibility(1);
        }
    }

    public void restart(View view){
        i=0;
        recreate();
    }
}
