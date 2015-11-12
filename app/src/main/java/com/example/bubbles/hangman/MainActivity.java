package com.example.bubbles.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView hangman6, hangman5, hangman4, hangman3, hangman2, hangman1, hangman0;
    private TextView the_word, guesses_left, letters_tried_text, letters_tried;
    private Button guess_button;
    private EditText guess_input;
    private String picked_word, questionmarks, the_word_was;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialise();
        hide();
        guesses_left.setText(R.string.guesses_welcome);
        letters_tried_text.setText(R.string.letters_tried_text_welcome);

    }

    // initialises all layout elements
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

        Button new_word_button = (Button) findViewById(R.id.new_word_button);
        guess_button = (Button) findViewById(R.id.guess_button);

        guess_input = (EditText) findViewById(R.id.guess_input);
    }

    // hides elements that shouldn't be visible at the start
    public void hide() {
        hangman5.setVisibility(View.INVISIBLE);
        hangman4.setVisibility(View.INVISIBLE);
        hangman3.setVisibility(View.INVISIBLE);
        hangman2.setVisibility(View.INVISIBLE);
        hangman1.setVisibility(View.INVISIBLE);
        hangman0.setVisibility(View.INVISIBLE);
        guess_button.setVisibility(View.INVISIBLE);
    }

    // resets game to start-state
    public void reset() {
        hide();
        the_word.setText(R.string.the_word);
        guesses_left.setText(R.string.guesses_left);
        letters_tried.setText(R.string.letters_tried);
        letters_tried_text.setText(R.string.letters_tried_text);
        guess_input.setText("");
        guess_button.setVisibility(View.VISIBLE);
        hangman6.setVisibility(View.VISIBLE);
    }

    public void new_word_button_click(View view) {
        reset();

        // picks a word randomly from the dictionary
        Integer random_number = new Random().nextInt(getResources().getStringArray(R.array.words).length);
        String[] array = getResources().getStringArray(R.array.words);
        picked_word = array[random_number];
        the_word_was = picked_word;

        // changes textSize according to length of the_word
        if(picked_word.length() > 11) {
            the_word.setTextSize(24);
        }
        else if(picked_word.length() > 8) {
            the_word.setTextSize(36);
        }
        else {
            the_word.setTextSize(48);
        }

        // adds correct amount of letters to the_word (in ?s)
        the_word.setText("");
        for (Integer i = 0; i < picked_word.length(); i++) {
            if (i == 0) {
                questionmarks = "? ";
            }
            else {
                questionmarks += "? ";
            }
        }
        the_word.setText(questionmarks);

    }

    public void guess_button_click(View view) {
        // takes the first character of user input (disregards if no input)
        if(guess_input.getText().toString().length() != 0) {
            String first_letter = guess_input.getText().toString().substring(0, 1).toLowerCase();

            // checks if the character is a letter
            if (first_letter.matches("[a-z]+")) {
                String check_for_letter = letters_tried.getText().toString();

                // checks with already tried letters
                if (!check_for_letter.contains(first_letter)) {
                    letters_tried.setText(check_for_letter + " " + first_letter);

                    // checks if letter is in the_word
                    if (picked_word.contains(first_letter)) {
                        while (picked_word.contains(first_letter)) {
                            // changes the ?(s) to the correct letter
                            String word = the_word.getText().toString();
                            int location = picked_word.indexOf(first_letter);
                            String new_word = word.substring(0,(location * 2))+ first_letter + word.substring(location * 2 + 1);
                            the_word.setText(new_word);

                            // removes the letter from the computer's word
                            char[] picked_word_chars = picked_word.toCharArray();
                            picked_word_chars[location] = '?';
                            picked_word = String.valueOf(picked_word_chars);

                            // check if the entire word has been revealed
                            if(picked_word.matches("[?]+")) {
                                guess_button.setVisibility(View.INVISIBLE);
                                Toast win_toast = Toast.makeText(getApplicationContext(), R.string.toast_win, Toast.LENGTH_LONG);
                                win_toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                                win_toast.show();

                            }
                        }
                    }
                    // removes one guess chance
                    else {
                        Integer amount = Integer.parseInt(guesses_left.getText().toString().substring(9, 10));
                        amount -= 1;
                        guesses_left.setText("You have " + amount + " wrong guesses left!");
                        switch (amount) {
                            case 5:
                                hangman6.setVisibility(View.INVISIBLE);
                                hangman5.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                hangman5.setVisibility(View.INVISIBLE);
                                hangman4.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                hangman4.setVisibility(View.INVISIBLE);
                                hangman3.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                hangman3.setVisibility(View.INVISIBLE);
                                hangman2.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                hangman2.setVisibility(View.INVISIBLE);
                                hangman1.setVisibility(View.VISIBLE);
                                break;
                            case 0:
                                hangman1.setVisibility(View.INVISIBLE);
                                hangman0.setVisibility(View.VISIBLE);
                                guess_button.setVisibility(View.INVISIBLE);
                                Toast loss_toast = Toast.makeText(getApplicationContext(), "Uh oh, try again!\n" + "The word was " + the_word_was, Toast.LENGTH_LONG);
                                loss_toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                                loss_toast.show();
                                break;
                        }
                    }
                }
            }
        }
        guess_input.setText("");
    }

    // if there's no more ?s in the word show message (toast) and remove guess_button
}