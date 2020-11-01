package kr.ac.team13.chap11_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    int select;
    LinearLayout pictureLayout;
    Integer[] posterID;
    String[] movie;
    MyGraphicView graphicView;
    static float scaleX = 1, scaleY = 1, angle = 0, grX = 0, grY = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);
        registerForContextMenu(pictureLayout);

        movie = new String[]{"쿵푸팬더", "짱구는 못말려", "아저씨",
                "아바타", "대부", "국가대표", "토이스토리3",
                "마당을 나온 암탉", "죽은 시인의 사회", "서유기"};
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        posterID = new Integer[]{R.drawable.mov21, R.drawable.mov22, R.drawable.mov23,
                R.drawable.mov24, R.drawable.mov25, R.drawable.mov26, R.drawable.mov27,
                R.drawable.mov28, R.drawable.mov29, R.drawable.mov30};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movie);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = position;
                graphicView.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public class MyGraphicView extends View{
        public MyGraphicView(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), posterID[select]);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.rotate(angle, cenX, cenY);
            canvas.skew(grX, grY);
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.drawBitmap(picture, picX, picY, null);
            picture.recycle();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();
        if(v == pictureLayout){
            mInflater.inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemCycle:
                angle = angle + 20;
                graphicView.invalidate();
                return true;
            case R.id.itemInc:
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
                return true;
            case R.id.itemDec:
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
                return true;
            case R.id.IncGradient:
                grX = grX + 0.3f;
                grY = grY + 0.3f;
                graphicView.invalidate();
                return true;
            case R.id.DecGradient:
                grX = grX - 0.3f;
                grY = grY - 0.3f;
                graphicView.invalidate();
                return true;
        }
        return false;
    }
}
