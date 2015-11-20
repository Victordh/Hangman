package com.example.bubbles.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HighScoresActivity extends Activity {

    ListView high_scores_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        high_scores_list = (ListView) findViewById(R.id.high_scores_list);

        String[] high_scores_data = new String[] { "Won Word length Evil mode Guesses left",
                "Yes       9                    Yes                4",
                "Yes       6                    No                 3",
                "Yes       4                    Yes                1",
                "No        9                    Yes                0",
                "No        3                    No                 0"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, high_scores_data);
        high_scores_list.setAdapter(adapter);
    }

    public void back_to_menu_button_click(View view) {
        Intent intent = new Intent(HighScoresActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
