package com.example.assignment3;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.assignment3.android.AndroidPreferenceRepository;
import com.example.assignment3.core.domain.SampleModulesRepository;
import com.example.assignment3.core.domain.SelectedModules;
import com.example.assignment3.core.repo.ModulesRepository;
import com.example.assignment3.core.repo.PreferenceRepository;
import com.example.assignment3.ui.SplashScreen;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;
    private static Fragment current;

    public static PreferenceRepository preferences;
    public static ModulesRepository modules;
    public static SelectedModules selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Full Screen
        getSupportActionBar().hide();

        //Disable Screen Orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Set the instance when app first run
        if(instance == null)
        {
            instance = this;
            preferences = new AndroidPreferenceRepository(this);
            selected = new SelectedModules();
            try {
                modules = new SampleModulesRepository(getAssets().open("Modules.txt"));
            }
            catch(IOException e) {
                throw new RuntimeException(e);
            }
            //Go to the splash screen when opened
            changePage(new SplashScreen());

            return;
        }
    }

    /***
     * Switch to the target page without animation.
     * @param className The class name of the layout
     */
    public void changePage(@NonNull final Fragment className) {

        MainActivity.instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                current = className;

                //Replace the content in the FrameLayout
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //Invoke Method
                ft.replace(R.id.pageFrame, current);
                ft.commitAllowingStateLoss();
            }
        });
    }

    /***
     * Switch to the target page with animation.
     * @param timeout The time that method will wait before switch to the new page (ms)
     * @param className The class name of the layout
     */
    public void changePage(final int timeout, final Fragment className) {

        //showLoadScreen(); //Show the loading animation

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(timeout == -1) return;
                try {
                    Thread.sleep(timeout);
                    if(className != null) {
                        changePage(className);
                    }
                    //hideLoadScreen(); //Hide the loading animation
                } catch (InterruptedException e) {  }

            }
        }).start();
    }

    public void hideToolBar()
    {
        findViewById(R.id.toolbar).setVisibility(View.INVISIBLE);
    }

    public void showToolBar()
    {
        findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
    }
}
