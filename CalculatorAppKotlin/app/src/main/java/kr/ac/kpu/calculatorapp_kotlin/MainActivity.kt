package kr.ac.kpu.calculatorapp_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        resultButton.setOnClickListener {
            saveData(heightEdtText.text.toString().toInt(),
                weightEdtText.text.toString().toInt())
            startActivity<ResultActivity>(
                "weight" to weightEdtText.text.toString(),
                "height" to heightEdtText.text.toString()
            )
        }
    }
    private fun saveData(height : Int, weight : Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height).putInt("KEY_WEIGHT", weight).apply()
    }
    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if (height != 0 && weight != 0){
            heightEdtText.setText(height.toString())
            weightEdtText.setText(weight.toString())
        }
    }
}
