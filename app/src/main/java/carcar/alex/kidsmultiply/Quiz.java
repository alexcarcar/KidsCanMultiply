package carcar.alex.kidsmultiply;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Quiz extends AppCompatActivity {

    private boolean multiplicationMode = true;
    private int theAnswer = 0;
    private MediaPlayer FXPlayer;

    private void playSound(int _id) {
        try {
            if (FXPlayer != null) {
                FXPlayer.stop();
                FXPlayer.release();
            }
            FXPlayer = MediaPlayer.create(this, _id);
            if (FXPlayer != null)
                FXPlayer.start();
        } catch (Exception e) {
            System.err.println("playSound: "+e);
        }
    }

    public void onDinoClick(View v) {
        playSound(R.raw.t_rex);
    }

    private void createQuestion() {
        Random randomGenerator = new Random();
        // Pick from 2-12; 1 is too easy
        int a = randomGenerator.nextInt(11)+2;
        int b = randomGenerator.nextInt(11)+2;
        String question = "";
        if (multiplicationMode) {
            theAnswer += a * b;
            question = a + " x " + b;
        } else {
            theAnswer = b;
            question += a*b + " ÷ " + a;
        }

        TextView q1 = (TextView) findViewById(R.id.question_text);
        q1.setText(question);

        TextView a1 = (TextView) findViewById(R.id.answer_text);
        a1.setText("");
    }

    private boolean checkAnswer(String result){
        int attempt = 0;
        try {
            attempt = Integer.parseInt(result);
        } catch (Exception e) {
            System.err.println("Attempt is not an integer.");
        }
        return (attempt == theAnswer);
    }

    public void onClick(View a) {
        int n = -1;

        if (a.getId() == R.id.image1) n = 1;
        if (a.getId() == R.id.image2) n = 2;
        if (a.getId() == R.id.image3) n = 3;
        if (a.getId() == R.id.image4) n = 4;
        if (a.getId() == R.id.image5) n = 5;
        if (a.getId() == R.id.image6) n = 6;
        if (a.getId() == R.id.image7) n = 7;
        if (a.getId() == R.id.image8) n = 8;
        if (a.getId() == R.id.image9) n = 9;
        if (a.getId() == R.id.image0) n = 0;
        if (a.getId() == R.id.delete) n = 100;
        if (a.getId() == R.id.enter) n = 200;

        if (n >= 0) {
            TextView a1 = (TextView) findViewById(R.id.answer_text);
            String result = a1.getText().toString();
            int l = result.length();

            if (n == 100) { // Delete a Character
                playSound(R.raw.type);
                if (l == 0) {
                    this.finish();
                    return;
                }
                result = result.substring(0,l-1);
            } else if ( n==200 ) { // Enter Key Pressed
                if (l == 0) {
                    playSound(R.raw.type);
                    return;  // Don't beep on just Enter
                }
                if (checkAnswer(result)) {
                    playSound(R.raw.clapping);
                    createQuestion();
                } else {
                    playSound(R.raw.buzzer);
                }
                result = "";
            } else {  // A key is pressed
                playSound(R.raw.type);
                if (l == 3) return; // Don't allow more than three digits
                if (l == 0 && n == 0) return; // Don't allow a first zero
                result += n;
            }
            a1.setText(result);
            a1.setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if (theAnswer == 0) {
            Intent intent = getIntent();
            multiplicationMode = intent.getExtras().getBoolean("multiplicationMode");
            createQuestion();
        }
    }
}
