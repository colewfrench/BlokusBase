package com.example.colefrench.colorpanel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends Activity {

    private static final int BOARD_SIZE = 20;
    private static final int TILE_SIZE = 76;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set fullscreen, no title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // begin App code

        LinearLayout mainBoardLayout = (LinearLayout)findViewById(R.id.boardLayout);
        mainBoardLayout.setBackgroundColor(Color.BLACK);
        LinearLayout[] boardLayouts = new LinearLayout[BOARD_SIZE];

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        Log.d("Width", "" + width);

        int tileSize = TILE_SIZE;

        LinearLayout.LayoutParams boardLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tileSize);

        for (int i = 0; i < BOARD_SIZE; i++)
        {
            boardLayouts[i] = new LinearLayout(this);

            boardLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            boardLayouts[i].setGravity(Gravity.CENTER_HORIZONTAL);
            boardLayouts[i].setLayoutParams(boardLayoutParams);

            mainBoardLayout.addView(boardLayouts[i]);
            // look up the LayoutParams docs, currently using the width,height,weight constructor

        }

        ImageButton[][] boardButtons = new ImageButton[BOARD_SIZE][BOARD_SIZE];
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(tileSize, tileSize);

        //Random rand = new Random();
        int red, green, blue;
        boolean altColor = true;

        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                boardButtons[i][j] = new ImageButton(this);
                boardButtons[i][j].setLayoutParams(buttonParams);

                // set the image and scale it to the full size of the imageButton
                //boardButtons[i][j].setImageResource(R.drawable.red_block);
                //boardButtons[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);

                boardButtons[i][j].setBackgroundColor(Color.BLUE);
                boardLayouts[i].addView(boardButtons[i][j]);

                //red = rand.nextInt(256);
                //green = rand.nextInt(256);
               // blue = rand.nextInt(256);

                if(altColor == true && i%2 ==0)
                {
                    boardButtons[i][j].setBackgroundColor(Color.argb(255, 128, 128, 128));
                    altColor = false;
                }
                else if(altColor == true)
                {
                    boardButtons[i][j].setBackgroundColor(Color.argb(255, 220, 220, 220));
                    altColor = false;
                }
                else if(altColor == false && i%2==0)
                {
                    boardButtons[i][j].setBackgroundColor(Color.argb(225, 220, 220, 220));
                    altColor = true;
                }
                else
                {
                    boardButtons[i][j].setBackgroundColor(Color.argb(255, 128,128, 128));
                    altColor = true;
                }

                boardButtons[i][j].setOnClickListener(new ButtonListener());
            }

        }

        makeScrollingPieceButtons();
        // makePieceButtons();
    }

    private void makePieceButtons()
    {
        LinearLayout[] pieceLayouts = new LinearLayout[3];

        pieceLayouts[0] = (LinearLayout)findViewById(R.id.topRowPieceLayout);
        pieceLayouts[1] = (LinearLayout)findViewById(R.id.MidRowPieceLayout);
        pieceLayouts[2] = (LinearLayout)findViewById(R.id.BotRowPieceLayout);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++) {
                ImageButton imageButton = new ImageButton(this);
                imageButton.setLayoutParams(buttonParams);
                imageButton.setImageResource(R.drawable.ic_launcher);
                pieceLayouts[i].addView(imageButton);
            }
        }


    }

    private void makeScrollingPieceButtons()
    {
        LinearLayout scrollingPieceLayout = (LinearLayout)findViewById(R.id.ScrollingLayout);

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(250, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        for (int j = 0; j < 21; j++) {
            ImageButton imageButton = new ImageButton(this);
            imageButton.setLayoutParams(buttonParams);
            imageButton.setImageResource(R.drawable.ic_launcher);
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
            scrollingPieceLayout.addView(imageButton);
        }
    }

    public class ButtonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            v.setBackgroundColor(Color.BLACK);
        }
    }
}
