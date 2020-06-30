package com.example.android.geoMob

import android.content.Intent

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences("COLOR", 0)
        val app_color = params.getInt("macouleur", 0)
        if(app_color==0){setTheme(R.style.AppTheme)}
        else { setTheme(app_color)}
        setContentView(R.layout.activity_main)

        setEvent(StartBtn, MainPage::class.java)


    }


    private fun setEvent(btn : Button, cls: Class<*>) {

        btn.setOnClickListener({
            val intent = Intent(this, cls)
            startActivity(intent)
        })

    }
}
