package kr.ac.kpu.se2016156033.ex07_4_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-4-2");

        imgView = (ImageView) findViewById(R.id.ImageView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //메뉴에 항목을 추가했습니다. add메소드의 파라미터로는 그룹id, 항목id, 순번, 제목 순으로 지정합니다.
        menu.add(0,1,0,"강아지");
        menu.add(0,2,0,"고양이");
        menu.add(0,3,0,"토끼");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){      //imte.getItemId()메소드로 어떤 메뉴를 클릭했는지 가져오고 switch ~ case문으로 각 항목마다 어떤 행동을 할지 정하였습니다.
            case 1:      //1번 아이템 id 즉, 강아지를 클릭하면 ImageView에 setImageResource메소드를 통해 drawble폴더의 dog 이미지를 삽입합니다.
                imgView.setImageResource(R.drawable.dog);
                imgView.setVisibility(View.VISIBLE);        //삽입한 이미지를 보여줍니다.
                return true;
            case 2:      //2번 아이템 id 즉, 고양이를 클릭하면 ImageView에 setImageResource메소드를 통해 drawble폴더의 cat 이미지를 삽입합니다.
                imgView.setImageResource(R.drawable.cat);
                imgView.setVisibility(View.VISIBLE);        //삽입한 이미지를 보여줍니다.
                return true;
            case 3:
                imgView.setImageResource(R.drawable.rabbit);    //3번 아이템 id 즉, 토끼를 클릭하면 ImageView에 setImageResource메소드를 통해 drawble폴더의 rabbit 이미지를 삽입합니다.
                imgView.setVisibility(View.VISIBLE);        //삽입한 이미지를 보여줍니다.
                return true;
        }
        return false;       //어떤 메뉴도 클릭되지 않으면 false를 반환합니다.
    }
}
