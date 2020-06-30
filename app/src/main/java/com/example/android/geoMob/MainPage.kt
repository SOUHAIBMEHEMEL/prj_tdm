package com.example.android.geoMob

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.exo1.R

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_a.*


class MainPage : AppCompatActivity(), MyCommunicator {

    private var mIsDualPane = false
    private var db: PaysDB? = null
    private var dao: PaysDAO? = null
    private var list_pays: MutableList<Pays>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        initDB()

        // If FragmentB is present in activity_main.xml, then we are in Tablet
        // Else the app is running in handset
        val fragmentBView = findViewById<View>(R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(title: String, description: String) {

        if (mIsDualPane) { // If we are in Landscape mode
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentB?
            txvTitle.text = title
            txvDescription.text = description
            image1.setImageResource(R.drawable.bresil)
        } else { // When we are in Portrait Mode
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
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
                if (list_pays!!.size == 0){
                    act.dao?.ajouter(Pays(R.drawable.algerie,"Algerie","Description algerie"))
                    act.dao?.ajouter(Pays(R.drawable.maroc,"Maroc","Description maroc"))
                    act.dao?.ajouter(Pays(R.drawable.egypte,"Egypte","Description Egypte"))
                }


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_pays != null) {

                    val adapter = RecyclerAdapter(act, list_pays!!)
                    recycler_view.adapter = adapter

                    val manager = LinearLayoutManager(act)
                    manager.orientation = RecyclerView.VERTICAL
                    recycler_view.layoutManager = manager
                    Toast.makeText(act,"données chargeés",Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}
