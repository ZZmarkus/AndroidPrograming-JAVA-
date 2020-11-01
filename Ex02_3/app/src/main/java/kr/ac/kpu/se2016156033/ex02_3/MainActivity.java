package kr.ac.kpu.se2016156033.ex02_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNate;
    Button btn911;
    Button btnGal;
    Button btnEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNate = (Button)findViewById(R.id.btnNate);
        btn911 = (Button)findViewById(R.id.btn911);
        btnGal = (Button)findViewById(R.id.btnGal);
        btnEnd = (Button)findViewById(R.id.btnEnd);

        btnNate.setBackgroundColor(Color.GRAY);
        btn911.setBackgroundColor(Color.GREEN);
        btnGal.setBackgroundColor(Color.RED);
        btnEnd.setBackgroundColor(Color.YELLOW);

        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(mlntent);
            }
        });

        btn911.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911"));
                startActivity(mlntent);
            }
        });

        btnGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mlntent);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
