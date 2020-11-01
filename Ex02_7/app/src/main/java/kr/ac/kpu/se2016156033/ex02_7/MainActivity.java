package kr.ac.kpu.se2016156033.ex02_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button b1, b2;
    RadioButton rb1, rb2, rb3;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editTextWeb);
        b1 = (Button) findViewById(R.id.textButton1);
        b2 = (Button) findViewById(R.id.textButton2);
        rb1 = (RadioButton) findViewById(R.id.rdioBtn1);
        rb2 = (RadioButton) findViewById(R.id.rdioBtn2);
        rb3 = (RadioButton) findViewById(R.id.rdioBtn3);
        img = (ImageView) findViewById(R.id.imageview);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(editText.getText())));
                startActivity(intent);
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.oreo);
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.pie);
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.q10);
            }
        });
    }
}
