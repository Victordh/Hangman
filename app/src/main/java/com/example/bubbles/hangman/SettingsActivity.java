package com.example.bubbles.hangman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends Activity {

    public static final String PREFERENCES_FILE_NAME = "settings";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    CheckBox evil_mode_checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        evil_mode_checkBox = (CheckBox) findViewById(R.id.evil_mode_checkbox);
        settings = this.getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        boolean evil_mode_checkbox_state = settings.getBoolean("evil_mode_checkbox_state", false);
        if(evil_mode_checkbox_state) {
            evil_mode_checkBox.setText(R.string.settings_evil_mode_checkbox_on);
            evil_mode_checkBox.setChecked(true);
        }
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(SettingsActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void evil_mode_checkbox_click(View view) {
        editor = settings.edit();
        if(evil_mode_checkBox.isChecked()){
            evil_mode_checkBox.setText(R.string.settings_evil_mode_checkbox_on);
            editor.putBoolean("evil_mode_checkbox_state", true);
        }
        else {
            evil_mode_checkBox.setText(R.string.settings_evil_mode_checkbox_off);
            editor.putBoolean("evil_mode_checkbox_state", false);
        }
        editor.commit();
    }

}
