package com.example.android.exo1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class Activity2 : AppCompatActivity() {

    val PARAMS_NAME = "COLOR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences(PARAMS_NAME, 0)
        val app_color = params.getInt("macouleur", 0)
        if(app_color == 0){setTheme(R.style.AppTheme)}
        else{setTheme(app_color)}
        setContentView(R.layout.activity_2)

        val btnDefault = findViewById<Button>(R.id.defaultBtn)
        val btnBlue = findViewById<Button>(R.id.blueBtn)
        val btnRed = findViewById<Button>(R.id.redBtn)

        btnDefault?.setOnClickListener({
            val prms = getSharedPreferences(PARAMS_NAME, 0)
            val editeur = prms.edit()
            editeur.putInt("macouleur", R.style.AppTheme)
            editeur.apply()
            setTheme(R.style.AppTheme)

            val intent = Intent(this@Activity2, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        })

        btnBlue?.setOnClickListener({
            val prms = getSharedPreferences(PARAMS_NAME, 0)
            val editeur = prms.edit()
            editeur.putInt("macouleur", R.style.AppTheme_blue)
            editeur.apply()
            setTheme(R.style.AppTheme_blue)

            val intent = Intent(this@Activity2, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        })

        btnRed?.setOnClickListener({
            val prms = getSharedPreferences(PARAMS_NAME, 0)
            val editeur = prms.edit()
            editeur.putInt("macouleur", R.style.AppTheme_red)
            editeur.apply()
            setTheme(R.style.AppTheme_red)

            val intent = Intent(this@Activity2, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        })

    }


    private fun setEvent(btn : Button, cls: Class<*>) {

        btn.setOnClickListener({
            val intent = Intent(this, cls)
            startActivity(intent)
        })

    }
}
