package com.example.bubbles.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView hangman6, hangman5, hangman4, hangman3, hangman2, hangman1, hangman0;
    private TextView the_word, guesses_left, letters_tried_text, letters_tried;
    private Button new_word_button, guess_button;
    private EditText guess_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialises all text, images and buttons
        initialise();
        // hides elements that shouldn't be visible at the start
        hide();

    }

    public void initialise() {
        hangman6 = (ImageView) findViewById(R.id.hangman6);
        hangman5 = (ImageView) findViewById(R.id.hangman5);
        hangman4 = (ImageView) findViewById(R.id.hangman4);
        hangman3 = (ImageView) findViewById(R.id.hangman3);
        hangman2 = (ImageView) findViewById(R.id.hangman2);
        hangman1 = (ImageView) findViewById(R.id.hangman1);
        hangman0 = (ImageView) findViewById(R.id.hangman0);

        the_word = (TextView) findViewById(R.id.the_word);
        guesses_left = (TextView) findViewById(R.id.guesses_left);
        letters_tried_text = (TextView) findViewById(R.id.letters_tried_text);
        letters_tried = (TextView) findViewById(R.id.letters_tried);

        new_word_button = (Button) findViewById(R.id.new_word_button);
        guess_button = (Button) findViewById(R.id.guess_button);

        guess_input = (EditText) findViewById(R.id.guess_input);
    }

    public void hide() {
        hangman5.setVisibility(View.INVISIBLE);
        hangman4.setVisibility(View.INVISIBLE);
        hangman3.setVisibility(View.INVISIBLE);
        hangman2.setVisibility(View.INVISIBLE);
        hangman1.setVisibility(View.INVISIBLE);
        hangman0.setVisibility(View.INVISIBLE);
    }

    public void reset() {
        hide();
        the_word.setText(R.string.the_word);
        guesses_left.setText(R.string.guesses_left);
        letters_tried.setText(R.string.letters_tried);
        guess_input.setText("");
    }

}