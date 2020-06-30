package com.example.android.geoMob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve data coming from MainActivity.java
        val description = intent.getStringExtra("description")
        val title = intent.getStringExtra("title")

        // Pass the data to FragmentB to display it
        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
        //fragmentB?.displayDetails(title, "des")
        txvTitle.text = title
        txvDescription.text = description
    }
}
