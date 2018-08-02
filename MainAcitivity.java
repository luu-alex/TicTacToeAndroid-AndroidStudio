package com.example.dog.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; //0 is x and 1 is circle
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningState = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{0,3,6},{1,4,7},{2,5,8},{6,4,2}};
    int activeGame = 1;


    private TextView mTextMessage;
    public void reset(View view) {
        for (int i = 0; i<gameState.length;i++) {
            gameState[i] = 2;
        }
        activeGame = 1;
        System.out.println("Reset is being pressed");
        FrameLayout gridLayout = (FrameLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i<gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        View b = findViewById(R.id.playagain);
        b.setVisibility(View.INVISIBLE);
        TextView t = findViewById(R.id.playagain1);
        t.setVisibility(View.INVISIBLE);
    }
    public int winCondition(int [] gamestate) {

        if (activeGame == 1) {
            for (int i =0;i<winningState.length; i++) {
                if (gameState[winningState[i][0]] == 0 && gameState[winningState[i][2]] == 0 && gameState[winningState[i][1]] == 0) {
                    return 0;
                }
                if (gameState[winningState[i][0]] == 1 && gameState[winningState[i][2]] == 1 && gameState[winningState[i][1]] == 1) {
                    return 1;
                }
            }
        }

        return 2;
    }
    public void dropIn( View view) {
        if (activeGame == 0) {
            return;
        }
        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapped-1] == 2) {
            counter.setTranslationY(-1000f);
            gameState[tapped-1] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blackx);
                counter.animate().translationYBy(1000f).setDuration(300);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.redpiece);
                counter.animate().translationYBy(1000f).setDuration(300);
                activePlayer = 0;

            }
            if (winCondition(gameState) == 1 ) {
                System.out.println("congratz player o");
                activeGame =0;
                View b = findViewById(R.id.playagain);
                b.setVisibility(View.VISIBLE);
                TextView t = findViewById(R.id.playagain1);
                t.setVisibility(View.VISIBLE);
                t.setText("Congrats player red");

            } else if (winCondition(gameState) == 0 ) {


                System.out.println("congratz player x");
                activeGame = 0;
                View b = findViewById(R.id.playagain);
                b.setVisibility(View.VISIBLE);
                TextView t = findViewById(R.id.playagain1);
                t.setVisibility(View.VISIBLE);
                t.setText("Congrats player black");

            }
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

}
