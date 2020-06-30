package com.example.android.geoMob

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.exo1.R
import com.example.android.exo1.R.id.container
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_database.*
import kotlinx.android.synthetic.main.fragment_b.*

class MainPage : AppCompatActivity(), MyCommunicator {

    private var mIsDualPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        // If FragmentB is present in activity_main.xml, then we are in Tablet
        // Else the app is running in handset
        val fragmentBView = findViewById<View>(R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(title: String, description: String) {

        if (mIsDualPane) { // If we are in Tablet
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
            //fragmentB?.displayDetails(title, description)
            txvTitle.text = title
            txvDescription.text = description
        } else { // When we are in Smart phone
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            startActivity(intent)
        }
    }


}
