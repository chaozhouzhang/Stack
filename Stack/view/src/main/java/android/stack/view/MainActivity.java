package android.stack.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private View mView1;
    private View mView2;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView1 = findViewById(R.id.view_line_1);
        mView2 = findViewById(R.id.view_line_2);
        mButton = findViewById(R.id.btn_move);
        final int[] index = {1};
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView1.setX(100* index[0]);
                mView2.setX(100* index[0]);
                index[0]++;

            }
        });


    }
}