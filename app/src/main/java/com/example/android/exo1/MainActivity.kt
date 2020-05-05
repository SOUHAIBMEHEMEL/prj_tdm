package com.example.android.exo1

import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.example.android.exo1.Activity2
import com.example.android.exo2.Intervention
import com.example.android.exo2.exo2
import com.example.android.exo2.listAdapter
import com.example.android.exo3.exo3
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences("COLOR", 0)
        val app_color = params.getInt("macouleur", 0)
        if(app_color==0){setTheme(R.style.AppTheme)}
        else { setTheme(app_color)}
        setContentView(R.layout.activity_main)

        setEvent(btn2, Activity2::class.java)
        setEvent(btn3, exo2::class.java)
        setEvent(btn4, exo3::class.java)

    }


    private fun setEvent(btn : Button, cls: Class<*>) {

        btn.setOnClickListener({
            val intent = Intent(this, cls)
            startActivity(intent)
        })

    }
}
