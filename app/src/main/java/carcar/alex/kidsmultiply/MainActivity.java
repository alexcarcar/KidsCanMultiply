package carcar.alex.kidsmultiply;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer FXPlayer;
    private boolean multiplicationMode = true;

    public void toggleMode(View v) {
        ImageView imageView = (ImageView) v;
        playSound(R.raw.type);
        if (multiplicationMode) {
            multiplicationMode = false;
            this.setTitle(R.string.app_name2);
            imageView.setImageResource(R.mipmap.toggle_divide);
        } else {
            multiplicationMode = true;
            this.setTitle(R.string.app_name);
            imageView.setImageResource(R.mipmap.toggle_multiply);
        }
    }

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
        intent.putExtra("multiplicationMode", multiplicationMode);
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

        if (n>0) {
            String result = "";
            for (int r=0; r<6; r++) {
                for (int c=0; c<2; c++) {
                    int i;
                    i = c*6 + r + 1;
                    result += formatAnswer(n,i);
                }
                result += "\n";
            }
            Intent i = new Intent(this, Table.class);
            i.putExtra("result", result);
            i.putExtra("n",n);
            startActivity(i);
            playSound(R.raw.news);
        }
    }

    private String formatAnswer(int n, int i) {
        int m = n*i;
        String result = "";
        if (multiplicationMode) {
            result += n +"x"+ i +"="+ m;
        } else {
            result += m +"÷"+ n +"="+ i;
        }

        if (i==7 || i==8 || i==9) result += ' ';
        if (m<100) result += ' ';
        if (m<10) result += ' ';
        if (i<7) result += "   ";
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiplicationMode = true;
        ImageView imageView = (ImageView) findViewById(R.id.toggle);
        imageView.setImageResource(R.mipmap.toggle_multiply);
    }
}
