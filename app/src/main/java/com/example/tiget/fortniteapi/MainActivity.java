package com.example.tiget.fortniteapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;



public class MainActivity extends AppCompatActivity {



    public static int[] Colors;
    public static int[] BackgroundScreens;

    public static int SCREEN_WIDTH_PX;
    public static int SCREEN_HEIGHT_PX;

    public static float density;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BackgroundScreens = new int[] {
            R.drawable.fire_lord,
            R.drawable.ice_lord,
            R.drawable.ice_lord_casting_blizzard,
            R.drawable.fly_trap,
            R.drawable.norse_emblem,
            R.drawable.omen,
            R.drawable.red_knight};

        Colors = new int[]{
            getResources().getColor(R.color.colorWhite),
            getResources().getColor(R.color.colorGreen),
            getResources().getColor(R.color.colorOrange)};


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = getResources().getDisplayMetrics().density;
        SCREEN_WIDTH_PX = dm.widthPixels;
        SCREEN_HEIGHT_PX = dm.heightPixels;


        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new Menu()).commit();
        }
    }
}
