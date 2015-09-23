package carcar.alex.kidsmultiply;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

public class Table extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Intent intent = getIntent();
        String result = intent.getExtras().getString("result");
        int n = intent.getExtras().getInt("n");

        TextView a1 = (TextView) findViewById(R.id.table_text);
        a1.setText(result);
        a1.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView t1 = (TextView) findViewById(R.id.title_text);
        String title = n + " Table";
        t1.setText(title);
        t1.setGravity(Gravity.CENTER_HORIZONTAL);
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
}
