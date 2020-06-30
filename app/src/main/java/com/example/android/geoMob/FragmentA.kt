package com.example.android.geoMob

import android.app.Fragment
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.exo1.R

class FragmentA: Fragment() {

    private var db: PaysDB? = null
    private var dao: PaysDAO? = null
    private var list_pays: MutableList<Pays>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_a, container, false)
        //initDB()
        setupRecyclerView(rootView)

        return rootView
    }

    fun setupRecyclerView(rootView: View) {

        val recyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView
        list_pays= DataProvider.data


        val adapter = RecyclerAdapter(context, this.list_pays!!)
        recyclerView.adapter = adapter

        val manager = LinearLayoutManager(activity)
        manager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = manager
    }

    fun initDB() {
        var act = context!!.applicationContext


        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                db = PaysDB.getInstance(act)
                dao = db?.PaysDAO()
                list_pays = dao?.getProduits()
                Toast.makeText(context,"base de donnees marche", Toast.LENGTH_SHORT).show()


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_pays == null) {
                    dao?.ajouter(Pays(R.drawable.algerie,"algerie111","alg111"))
                    list_pays= mutableListOf(Pays(R.drawable.algerie,"asssss","abbbbbb"))

                    //val adapter = InterventionListAdapter(act, R.layout.intervention_item, list_pays!!)
                    //lvProduits.adapter = adapter
                    Toast.makeText(context,"données chargeés", Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}