package carcar.alex.kidsmultiply;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
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

    public void onKidsClick(@SuppressWarnings("UnusedParameters")View a) {
        playSound(R.raw.kids_laughing);
    }

    public void onQuizClick(@SuppressWarnings("UnusedParameters")View a) {
        Intent intent = new Intent(this, Quiz.class);
        playSound(R.raw.type);
        startActivity(intent);
    }

    public void displayTable(View a) {
        int n = 0;
        if(a.getId() == R.id.image1) n = 1;
        if(a.getId() == R.id.image2) n = 2;
        if(a.getId() == R.id.image3) n = 3;
        if(a.getId() == R.id.image4) n = 4;
        if(a.getId() == R.id.image5) n = 5;
        if(a.getId() == R.id.image6) n = 6;
        if(a.getId() == R.id.image7) n = 7;
        if(a.getId() == R.id.image8) n = 8;
        if(a.getId() == R.id.image9) n = 9;
        if(a.getId() == R.id.image10) n = 10;
        if(a.getId() == R.id.image11) n = 11;
        if(a.getId() == R.id.image12) n = 12;
        Intent i = new Intent(this, Table.class);
        i.putExtra("n",n);
        playSound(R.raw.news);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAboutClick(MenuItem item) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onPracticeClick(MenuItem item) {
        Intent intent = new Intent(this, Quiz.class);
        playSound(R.raw.type);
        startActivity(intent);
    }
}
