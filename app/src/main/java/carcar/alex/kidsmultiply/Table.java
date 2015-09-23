package carcar.alex.kidsmultiply;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Table extends AppCompatActivity {

    private MediaPlayer FXPlayer;
    private boolean multiplicationMode = true;
    private int n = 1;

    public void toggleMode(View v) {
        ImageView imageView = (ImageView) v;
        String title = n + (multiplicationMode?" ÷ ":" x ") + "Table";
        this.setTitle(title);

        playSound(R.raw.type);
        if (multiplicationMode) {
            multiplicationMode = false;
            imageView.setImageResource(R.mipmap.toggle_divide);
        } else {
            multiplicationMode = true;
            imageView.setImageResource(R.mipmap.toggle_multiply);
        }
        TextView a1 = (TextView) findViewById(R.id.table_text);
        a1.setText(makeTable());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Intent intent = getIntent();
        n = intent.getExtras().getInt("n");
        ImageView imageView = (ImageView) findViewById(R.id.toggle);
        ImageView imageNumber = (ImageView) findViewById(R.id.imageNumber);
        imageView.setImageResource(R.mipmap.toggle_multiply);
        switch (n) {
            case 1: imageNumber.setImageResource(R.mipmap.image1); break;
            case 2: imageNumber.setImageResource(R.mipmap.image2); break;
            case 3: imageNumber.setImageResource(R.mipmap.image3); break;
            case 4: imageNumber.setImageResource(R.mipmap.image4); break;
            case 5: imageNumber.setImageResource(R.mipmap.image5); break;
            case 6: imageNumber.setImageResource(R.mipmap.image6); break;
            case 7: imageNumber.setImageResource(R.mipmap.image7); break;
            case 8: imageNumber.setImageResource(R.mipmap.image8); break;
            case 9: imageNumber.setImageResource(R.mipmap.image9); break;
            case 10: imageNumber.setImageResource(R.mipmap.image10); break;
            case 11: imageNumber.setImageResource(R.mipmap.image11); break;
            case 12: imageNumber.setImageResource(R.mipmap.image12); break;
        }
        this.setTitle(n + " x Table");
        TextView a1 = (TextView) findViewById(R.id.table_text);
        a1.setText(makeTable());
        a1.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_MOVE) :
            case (MotionEvent.ACTION_UP) :
                this.finish();
                return true;

            default :
                return super.onTouchEvent(event);
        }
    }

    private String makeTable() {
        String result = "";
        for (int r=0; r<6; r++) {
            for (int c=0; c<2; c++) {
                int i;
                i = c*6 + r + 1;
                result += formatAnswer(n,i);
            }
            result += "\n";
        }
        return result;
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
}
