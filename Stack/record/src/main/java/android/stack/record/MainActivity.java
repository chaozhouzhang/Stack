package android.stack.record;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioRecord;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}