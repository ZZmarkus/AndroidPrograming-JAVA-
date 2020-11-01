package kr.ac.team13.chap12_6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;
    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new myDBHelper(this);
        dp = (DatePicker) findViewById(R.id.dataPicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);

        btnWrite = (Button) findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_"
                        + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth);

                String diaryStr = null;
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM myDiary WHERE diaryDate = '" + fileName + "';", null);

                cursor.moveToNext();
                if(cursor.getString(1).equals(null)){
                    btnWrite.setText("새로 저장");
                    edtDiary.setHint("일기 없음");

                }
                else{
                    diaryStr += cursor.getString(1);
                    btnWrite.setText("수정하기");
                }


                cursor.close();
                sqlDB.close();
                edtDiary.setText(diaryStr);
                btnWrite.setEnabled(true);
            }
        });


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();

                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM myDiary WHERE diaryDate = '" + fileName + "';", null);

                cursor.moveToNext();
                if(cursor.getString(1).equals(null)) {
                    sqlDB.execSQL("UPDATE myDiary SET content ='"
                            + edtDiary.getText() + "' WHERE diaryDate = '"
                            + fileName + "';");
                }
                else {
                    sqlDB.execSQL("INSERT INTO myDiary VALUES ('" + fileName + "', '" + edtDiary.getText().toString() + "');");

                }
                cursor.close();
                sqlDB.close();

            }
        });

    }


    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(@Nullable Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE myDiary ( diaryDate CHAR(20) PRIMARY KEY, content VARCHAR(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS myDiary");
            onCreate(db);
        }
    }
}
