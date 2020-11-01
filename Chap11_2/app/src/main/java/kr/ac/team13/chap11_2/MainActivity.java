package kr.ac.team13.chap11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        MyGalleryAdapter galleryAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galleryAdapter);
    }

    public class MyGalleryAdapter extends BaseAdapter{
        Context context;
        Integer[] posterID = {R.drawable.mov11, R.drawable.mov12, R.drawable.mov13,
                R.drawable.mov14, R.drawable.mov15, R.drawable.mov16, R.drawable.mov17,
                R.drawable.mov18, R.drawable.mov19, R.drawable.mov20};
        String[] posterTitle = { "여인의 향기", "쥬라기 공원", "포레스트 검프", "사랑의 블랙홀",
                "혹성탈출", "아름다운비행", "내이름은 칸", "해리포터", "마더", "킹콩을 들다" };

        public MyGalleryAdapter(Context c){
            context = c;
        }
        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5,5,5,5);
            imageview.setImageResource(posterID[position]);
            final int pos = position;

            imageview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(posterID[pos]);

                    Toast toast = new Toast(MainActivity.this);
                    View toastView = (View) View.inflate(MainActivity.this,
                            R.layout.dialog, null);
                    TextView toastText = (TextView) toastView.findViewById(R.id.tvPoster);
                    toastText.setText(posterTitle[pos]);
                    toast.setView(toastView);
                    toast.show();
                    return false;
                }
            });

            return imageview;
        }
    }
}
