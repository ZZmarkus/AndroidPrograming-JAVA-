package kr.ac.team13.chap8_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    EditText edtDiary;
    Button btnwrite;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dp = (DatePicker) findViewById(R.id.datePicker);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnwrite = (Button) findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath + "/mydir");
        if(!myDir.exists())
            myDir.mkdir();

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" +
                        Integer.toString(monthOfYear + 1) +"_" +
                        Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);
                edtDiary.setText(str);
                btnwrite.setEnabled(true);
            }
        });
        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream outFs = new FileOutputStream("/storage/emulated/0/myDir/" + fileName);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){

                }
            }
        });
    }
    String readDiary(String fName) {
        String diaryStr = null;
        try {
            FileInputStream inFs = new FileInputStream("/storage/emulated/0/myDir/" + fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnwrite.setText("수정하기");
        }catch (IOException e){
            edtDiary.setHint("일기 없음");
            btnwrite.setText("새로 저장");
        }
        return diaryStr;
    }
}
