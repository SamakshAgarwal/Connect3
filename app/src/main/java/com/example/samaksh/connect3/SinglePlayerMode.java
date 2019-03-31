package com.example.samaksh.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class SinglePlayerMode extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[9];
    TextView textView;
    Button restartButton;
    int[] playerState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winConditions = new int[][]{{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {2, 5 ,8}, {2, 4, 6}, {3, 4, 5}, {6, 7, 8}};
    int player = 0,c = 0;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        textView = findViewById(R.id.textView);
        restartButton = findViewById(R.id.restartButton);
        for(int c=1;c<=9;c++){
            String buttonName = "tile"+String.valueOf(c);
            int resId = getResources().getIdentifier(buttonName,"id",getPackageName());
            imageViews[c-1] = findViewById(resId);
        }

        //first move
        int rand = ThreadLocalRandom.current().nextInt(1,2+1);
        if(rand==1){
            textView.setText(R.string.CPUTurn);
            player = 1;
            firstMove();
        }
        else {
            textView.setText(R.string.YourTurn);
        }
    }

    public void firstMove(){
        int rand = ThreadLocalRandom.current().nextInt(1,2+1);
        if(rand==1){
            imageViews[4].performClick();
        }
        else {
            imageViews[0].performClick();
        }
    }

    public void secondTileMove(){
        int k=0;
        for (int aPlayerState : playerState) {
            if (aPlayerState == 2)
                k++;
        }
        int[] availableTiles = new int[k];
        k=0;
        for(int i=0;i<playerState.length;i++){
            if(playerState[i]==2){
                availableTiles[k] = i;
                k++;
            }
        }
        int rand = ThreadLocalRandom.current().nextInt(0,k);
        imageViews[availableTiles[rand]].performClick();
    }

    public void thirdTileMove(){
        for(int[] winCondition:winConditions){
            if ((playerState[winCondition[0]] == playerState[winCondition[1]]) || (playerState[winCondition[0]] == playerState[winCondition[2]]) || (playerState[winCondition[1]] == playerState[winCondition[2]])) {
                if((playerState[winCondition[0]]==1 && playerState[winCondition[1]]==1) || (playerState[winCondition[1]]==1 && playerState[winCondition[2]]==1) || (playerState[winCondition[2]]==1 && playerState[winCondition[0]]==1)) {
                    for (int c = 0; c <= 2; c++) {
                        if (playerState[winCondition[c]] == 2) {
                            imageViews[winCondition[c]].performClick();
                            return;
                        }
                    }
                }
            }
        }

        for(int[] winCondition:winConditions){
            if ((playerState[winCondition[0]] == playerState[winCondition[1]]) || (playerState[winCondition[0]] == playerState[winCondition[2]]) || (playerState[winCondition[1]] == playerState[winCondition[2]])) {
                if((playerState[winCondition[0]]==0 && playerState[winCondition[1]]==0) || (playerState[winCondition[1]]==0 && playerState[winCondition[2]]==0) || (playerState[winCondition[2]]==0 && playerState[winCondition[0]]==0)) {
                    for (int c = 0; c <= 2; c++) {
                        if (playerState[winCondition[c]] == 2) {
                            imageViews[winCondition[c]].performClick();
                            return;
                        }
                    }
                }
            }
        }
        if(c<9)
            secondTileMove();
    }

    public void onClick(View view){
        ImageView clicked = (ImageView)view;
        clicked.setTranslationY(-2000f);
        clicked.setEnabled(false);
        playerState[Integer.parseInt(clicked.getTag().toString())] = player;
        if(!gameOver) {
            if (player == 1) {
                clicked.setImageResource(R.drawable.x);
                textView.setText(R.string.YourTurn);
                player = 0;
                checkResult();
            } else {
                clicked.setImageResource(R.drawable.o1);
                textView.setText(R.string.CPUTurn);
                player = 1;
                checkResult();
                thirdTileMove();
            }
        }
        clicked.animate().translationYBy(2000f).rotation(3600).setDuration(500);
    }

    public void checkResult(){
        for (int[] winCondition:winConditions) {
            if (playerState[winCondition[0]] == playerState[winCondition[1]] && playerState[winCondition[0]] == playerState[winCondition[2]] && playerState[winCondition[0]] != 2) {
                gameOver = true;
                if (playerState[winCondition[0]] == 1)
                    textView.setText(R.string.CrossWins);
                else
                    textView.setText(R.string.CircleWins);
                return;
            }
        }
        c++;
         if(c==9){
            textView.setText(R.string.Draw);
            gameOver = true;
        }
    }

    public void restart(View view)
    {
        c = 0;
        recreate();
    }
}
