package com.example.bubbles.hangman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {

    SharedPreferences settings;
    TextView menu_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu_title = (TextView) findViewById(R.id.menu_title);

        settings = getApplicationContext().getSharedPreferences("settings", 0);
        boolean evil_mode_checked = settings.getBoolean("evil_mode_checkbox_state", false);
        // if evil mode is checked
        if(evil_mode_checked) {
            menu_title.setText(R.string.menu_evil_title);
            menu_title.setTextColor(Color.RED);
        }
        else {
            menu_title.setText(R.string.menu_normal_title);
            menu_title.setTextColor(Color.BLACK);
        }
    }

    public void new_game_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void instructions_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, InstructionsActivity.class);
        startActivity(intent);
    }

    public void high_scores_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, HighScoresActivity.class);
        startActivity(intent);
    }

    public void settings_button_click(View view) {
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
