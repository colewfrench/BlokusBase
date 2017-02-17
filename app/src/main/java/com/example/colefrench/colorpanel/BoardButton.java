package com.example.colefrench.colorpanel;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by devinajimine and adrianlow on 2/15/17.
 */

public class BoardButton extends ImageButton
{
    private int initialColor;


    public BoardButton(Context context) {
        super(context);
    }


    public void setInitialColor(int color)
    {
       initialColor = color;
    }

    public int getInitialColor()
    {
        return initialColor;
    }
}
