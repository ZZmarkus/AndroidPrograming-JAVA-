package kr.ac.team13.chap13_2;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    MediaPlayer mPlayer;
    SeekBar pbMP3;
    Switch switch1;
    TextView txtView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);
        pbMP3 = (SeekBar) findViewById(R.id.pbMP3);

        switch1 = (Switch) findViewById(R.id.switch1);

        switch1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (switch1.isChecked() == true) {
                    mPlayer = MediaPlayer.create(getApplicationContext(),
                            R.raw.song1);
                    mPlayer.setLooping(true);
                    mPlayer.start();
                    makeThread();
                } else {
                    mPlayer.pause();
                }
            }
        });


        pbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                txtView.setText("진행률 : " + progress/200 + "%");
                if (fromUser) {
                    mPlayer.seekTo(progress);
                }
            }
        });
    }

    void makeThread() {
        new Thread() {
            public void run() {
                // 음악이 계속 작동 중이라면
                while (mPlayer.isPlaying()) {
                    pbMP3.setMax(mPlayer.getDuration()); // 음악의 시간을 최대로 설정
                    pbMP3.setProgress(mPlayer.getCurrentPosition()); // 현재 위치를
                    // 지정
                    SystemClock.sleep(100);
                }
                pbMP3.setProgress(0);
            }
        }.start();
    }
}