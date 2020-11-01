package kr.ac.kpu.se2016156033.ex07_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rGroup;      //강아지, 고양이, 토끼, 말 버튼의 RadioGroup입니다.
    RadioButton rDog, rCat, rRabbit, rHosre;
    Button showButton;      //그림보기 버튼입니다.
    View dialogView;        //대화상자 뷰 입니다.
    ImageView imgView;      //대화상자 안에 들어갈 이미지 뷰 입니다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 7-6");       //지시사항인 타이틀 지정

        rGroup = (RadioGroup) findViewById(R.id.rGroup1);
        rDog = (RadioButton) findViewById(R.id.Dog);
        rCat = (RadioButton) findViewById(R.id.Cat);
        rRabbit = (RadioButton) findViewById(R.id.Rabbit);
        rHosre = (RadioButton) findViewById(R.id.Horse);
        showButton = (Button) findViewById(R.id.SohwButton1) ;


        showButton.setOnClickListener(new View.OnClickListener() {      //그림보기 버튼을 눌렀을 때 동작하는 이벤트 리스너
            @Override
            public void onClick(View v) {
                //dialog1.xml 파일을 인플레이트하여 dialogView에 대입하였습니다.
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
                //대화상자를 생성하는데 현재 내부 클래스 안에 있으므로 'MainActivity.this'로 컨텍스트를 지정하였습니다.
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                //setView를 통하여 앞에서 인플레이트한 뷰를 대화상자로 사용합니다.
                dlg.setView(dialogView);
                //dialog1.xml의 ImageView를 대입하였습니다.
                imgView = (ImageView) dialogView.findViewById(R.id.imgView1);

                //대화상자의 닫기버튼(네거티브버튼)을 생성하였습니다.
                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                //switch ~ case문으로 RadioButton의 Id값에 따라 대화상자에 보여지는 내용이 달라집니다.
                switch (rGroup.getCheckedRadioButtonId()){
                    case R.id.Dog:  //라디오버튼이 강아지를 클릭한 상태라면
                        imgView.setImageResource(R.drawable.dog);   //대화상자의 ImageView에 drawble의 dog이미지를 넣습니다.
                        imgView.setVisibility(View.VISIBLE);        //생성된 ImageView를 보이게합니다.
                        dlg.setTitle("강아지");        //대화상자의 타이틀은 강아지 입니다.
                        dlg.show();     //대화상자를 보여줍니다.
                        break;
                        //아래의 코드는 강아지와 같습니다.
                    case R.id.Cat:
                        imgView.setImageResource(R.drawable.cat);
                        imgView.setVisibility(View.VISIBLE);
                        dlg.setTitle("고양이");
                        dlg.show();
                        break;
                    case R.id.Rabbit:
                        imgView.setImageResource(R.drawable.rabbit);
                        imgView.setVisibility(View.VISIBLE);
                        dlg.setTitle("토끼");
                        dlg.show();
                        break;
                    case R.id. Horse:
                        imgView.setImageResource(R.drawable.horse);
                        imgView.setVisibility(View.VISIBLE);
                        dlg.setTitle("말");
                        dlg.show();
                        break;
                    default:        //처음 실행할 때 라디오 버튼이 아무것도 클릭되어있지 않은데 이 상태에서 클릭할 경우 토스트 메시지를 보여줍니다.
                        Toast.makeText(getApplicationContext(), "동물을 먼저 선택해 주세요", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });


    }
}
