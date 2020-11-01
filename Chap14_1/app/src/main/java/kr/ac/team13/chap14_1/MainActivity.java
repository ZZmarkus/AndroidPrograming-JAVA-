package kr.ac.team13.chap14_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Button btnStart, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MusicService.class);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                android.util.Log.i("서비스 테스트", "startService()");
                Toast.makeText(getApplicationContext(), "startService()", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                android.util.Log.i("서비스 테스트", "stopService()");
            }
        });
    }
}
