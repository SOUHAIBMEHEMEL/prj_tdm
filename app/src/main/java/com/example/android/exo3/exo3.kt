package com.example.android.exo3

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.exo1.R
import com.example.android.exo1.R.id.lvProduits
import com.example.android.exo2.Intervention
import com.example.android.exo2.listAdapter
import kotlinx.android.synthetic.main.content_database.*
import kotlinx.android.synthetic.main.exo3.*
import java.util.ArrayList

class exo3 : AppCompatActivity() {

    private var db: InterventionDB? = null
    private var dao: InterventionDAO? = null
    private var list_intervention: MutableList<InterventionExo3>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exo3)


        initDB()
        val activity = this
        fab.setOnClickListener {
            val intent = Intent(activity, EditIntervention::class.java)
            intent.putExtra("mode","ajout")
            startActivity(intent)
        }
    }


    fun initDB() {
        var act = this


        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                act.db = InterventionDB.getInstance(act)
                act.dao = db?.InterventionDAO()
                list_intervention = act.dao?.getProduits()


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_intervention != null) {

                    val adapter = InterventionListAdapter(act, R.layout.intervention_item, list_intervention!!)
                    lvProduits.adapter = adapter
                    Toast.makeText(act,"données chargeés",Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}