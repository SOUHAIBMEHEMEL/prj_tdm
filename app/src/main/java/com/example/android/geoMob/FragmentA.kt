package com.example.android.geoMob

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.exo1.R

class FragmentA: Fragment() {

    private var db: PaysDB? = null
    private var dao: PaysDAO? = null
    private var list_pays: MutableList<Pays>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_a, container, false)

        return rootView
    }

}