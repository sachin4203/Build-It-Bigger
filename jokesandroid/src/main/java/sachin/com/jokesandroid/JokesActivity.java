package sachin.com.jokesandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    private TextView jokesTextView;
    public static String JOKE_INTENT = "Joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        jokesTextView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
       String joke = intent.getStringExtra(JOKE_INTENT);
        jokesTextView.setText(joke);
    }
}
