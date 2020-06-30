package com.example.android.geoMob

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_b.*

class FragmentB: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_detail, container, false)
    }

    fun displayDetails(title: String, description: String) {

        txvTitle.text = title
        txvDescription.text = description
    }
}