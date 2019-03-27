package com.example.samaksh.connect3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMultiplayerClick(View view){
        Intent intent = new Intent(this, TwoPlayerMode.class);
        startActivity(intent);
    }
}
