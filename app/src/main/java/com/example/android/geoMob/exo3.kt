package com.example.android.geoMob

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.exo1.R
import com.example.android.exo1.R.id.lvProduits
import kotlinx.android.synthetic.main.content_database.*
import kotlinx.android.synthetic.main.exo3.*
import java.util.ArrayList

class exo3 : AppCompatActivity() {

    private var db: PaysDB? = null
    private var dao: PaysDAO? = null
    private var list_pays: MutableList<Pays>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences("COLOR", 0)
        val app_color = params.getInt("macouleur", 0)
        if (app_color == 0) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(app_color)
        }
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
                act.db = PaysDB.getInstance(act)
                act.dao = db?.PaysDAO()
                list_pays = act.dao?.getProduits()


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_pays != null) {

                    val adapter = InterventionListAdapter(act, R.layout.intervention_item, list_pays!!)
                    lvProduits.adapter = adapter
                    Toast.makeText(act,"données chargeés",Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}