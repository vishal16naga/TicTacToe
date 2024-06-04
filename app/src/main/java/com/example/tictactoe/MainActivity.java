package com.example.tictactoe;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean game= true;
    int player = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winpos= {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int count= 0;
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int image= Integer.parseInt(img.getTag().toString());
        if (!game) {
            gameReset(view);
            count= 0;
        }
        if (gamestate[image] == 2) {
            count++;
            if (count==9) {
                game= false;
            }
            gamestate[image] = player;
            img.setTranslationY(-1000f);
            if (player == 0) {
                img.setImageResource(R.drawable.x);
                player = 1;
                TextView status = findViewById(R.id.show);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                player = 0;
                TextView status = findViewById(R.id.show);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;
        if (count> 4) {
            for (int[] winposition : winpos) {
                if (gamestate[winposition[0]] == gamestate[winposition[1]] &&
                        gamestate[winposition[1]] == gamestate[winposition[2]] &&
                        gamestate[winposition[0]] != 2) {
                    flag = 1;
                    String winnershow;
                    game= false;
                    if (gamestate[winposition[0]] == 0) {
                        winnershow= "X has won";
                    } else {
                        winnershow= "O has won";
                    }
                    TextView gamestatus= findViewById(R.id.show);
                    gamestatus.setText(winnershow);
                }
            }
            if (count==9 && flag == 0) {
                TextView gamestatus = findViewById(R.id.show);
                gamestatus.setText("Match Draw");
            }
        }
    }
    public void gameReset(View view) {
        game= true;
        player = 0;
        Arrays.fill(gamestate, 2);
        ((ImageView) findViewById(R.id.block1)).setImageResource(0);
        ((ImageView) findViewById(R.id.block2)).setImageResource(0);
        ((ImageView) findViewById(R.id.block3)).setImageResource(0);
        ((ImageView) findViewById(R.id.block4)).setImageResource(0);
        ((ImageView) findViewById(R.id.block5)).setImageResource(0);
        ((ImageView) findViewById(R.id.block6)).setImageResource(0);
        ((ImageView) findViewById(R.id.block7)).setImageResource(0);
        ((ImageView) findViewById(R.id.block8)).setImageResource(0);
        ((ImageView) findViewById(R.id.block9)).setImageResource(0);
        TextView gamestatus = findViewById(R.id.show);
        gamestatus.setText("X's Turn - Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}