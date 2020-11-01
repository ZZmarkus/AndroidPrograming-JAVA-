package kr.ac.kpu.calculatorapp_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

        var bmi = weight / Math.pow(height / 100.0, 2.0)

        when{
            bmi >= 35 -> resultText.text = "고도 비만"
            bmi >= 30 -> resultText.text = "2단계 비만"
            bmi >= 25 -> resultText.text = "1단계 비만"
            bmi >= 23 -> resultText.text = "과체중"
            bmi >= 18.5 -> resultText.text = "정상"
            else -> resultText.text = "저체중"
        }

        when{
            bmi >= 23 -> imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 -> imageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else -> imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }

        toast("$bmi")
    }
}
