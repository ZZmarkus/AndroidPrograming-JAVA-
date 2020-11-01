package kr.ac.kpu.se2016156033.ex07_4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-4-1");     //과제의 지시사항을 이행하기 위해 타이틀 제목을 넣었습니다.
        imgView = (ImageView) findViewById(R.id.ImageView1);    //imgView는 activity_main.xml코드의 ImageView1에 해당됩니다.
    }
    //상위클래스의 생성자를 실행하고 메뉴 인플레이터를 생성하였습니다.
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);       //앞에서 작성한 menu1.xml파일을 등록하였습니다.
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){      //imte.getItemId()메소드로 어떤 메뉴를 클릭했는지 가져오고 switch ~ case문으로 각 항목마다 어떤 행동을 할지 정하였습니다.
            case R.id.Dog:      //강아지를 클릭하면 ImageView에 setImageResource메소드를 통해 drawble폴더의 dog 이미지를 삽입합니다.
                imgView.setImageResource(R.drawable.dog);
                imgView.setVisibility(View.VISIBLE);        //삽입한 이미지를 보여줍니다.
                return true;
            case R.id.Cat:      //고양이를 클릭하면 ImageView에 setImageResource메소드를 통해 drawble폴더의 cat 이미지를 삽입합니다.
                imgView.setImageResource(R.drawable.cat);
                imgView.setVisibility(View.VISIBLE);        //삽입한 이미지를 보여줍니다.
                return true;
            case R.id.Rabbit:
                imgView.setImageResource(R.drawable.rabbit);    //토끼를 클릭하면 ImageView에 setImageResource메소드를 통해 drawble폴더의 rabbit 이미지를 삽입합니다.
                imgView.setVisibility(View.VISIBLE);        //삽입한 이미지를 보여줍니다.
                return true;
        }
        return false;       //어떤 메뉴도 클릭되지 않으면 false를 반환합니다.
    }
}