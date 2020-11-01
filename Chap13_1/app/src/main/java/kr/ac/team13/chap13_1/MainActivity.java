package kr.ac.team13.chap13_1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int num = 0;
    ListView listViewMP3;
    Button btnPlay, btnPause, btnStop;
    TextView tvMP3, tvSeek;
    SeekBar seekBar;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/" ;
    MediaPlayer mPlayer;
    boolean PAUSED = false; // 음악이 일시정지 중인지 체크.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 MP3 플레이어(개선)");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);


        // SDCard의 파일을 읽어서 리스트뷰에 출력
        mp3List = new ArrayList<String>(); // 가변적 문자열

        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;
        for (File file : listFiles) {
            fileName = file.getName();
            extName = fileName.substring(fileName.length() - 3);
            if (extName.equals((String) "mp3")) // 확장명이 mp3일 때만 추가함.
                mp3List.add(fileName);
        }

        ListView listViewMP3 = (ListView) findViewById(R.id.listViewMP3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        selectedMP3 = mp3List.get(arg2);
                   }
                });
        selectedMP3 = mp3List.get(0);


        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop = (Button) findViewById(R.id.btnStop);
        tvMP3 = (TextView) findViewById(R.id.tvMP3);

        seekBar = (SeekBar) findViewById(R.id.seekMP3);
        tvSeek = (TextView) findViewById(R.id.tvSeek);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    mPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btnPlay.setClickable(false);
                    btnPause.setClickable(true);
                    btnStop.setClickable(true);
                    makeThread();
                    tvMP3.setText("실행중인 음악 :  " + selectedMP3);
                } catch (IOException e) {
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (PAUSED == false) {
                    mPlayer.pause();
                    btnPause.setText("이어듣기");
                    PAUSED = true;
                }
                else {
                    mPlayer.start();
                    PAUSED = false;
                    btnPause.setText("일시정지");
                    makeThread();
                }
            }
        });
        btnPause.setClickable(false);

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPlayer.stop();
                mPlayer.reset();
                btnPlay.setClickable(true);
                btnPause.setClickable(false);
                btnPause.setText("일시정지");
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악 :  ");

            }
        });
        btnStop.setClickable(false);

    }

    void makeThread() {
        new Thread() {
            SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
            public void run() {
                seekBar.setMax(mPlayer.getDuration()); // 음악의 시간을 최대로 설정
                // 음악이 계속 작동 중이라면
                while (mPlayer.isPlaying()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(mPlayer.getCurrentPosition()); // 현재 위치를
                            // 지정
                            tvSeek.setText("진행 시간 : " + timeFormat.format(mPlayer.getCurrentPosition()));
                        }
                    });
                    SystemClock.sleep(200);
                }
            }
        }.start();
    }
}

