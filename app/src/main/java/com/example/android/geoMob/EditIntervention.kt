package com.example.android.geoMob

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.android.exo1.R

import kotlinx.android.synthetic.main.edit_intervention.*


class EditIntervention : AppCompatActivity() {

    val context = this
    var mode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences("COLOR", 0)
        val app_color = params.getInt("macouleur", 0)
        if (app_color == 0) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(app_color)
        }
        setContentView(R.layout.edit_intervention)
        mode = intent.getStringExtra("mode")

        saveBtn.setOnClickListener{
            sauvegarderIntervention()
        }
    }

    fun sauvegarderIntervention() {
        var act = this
        val numero = numero1.text.toString().toInt()
        val date = date1.text.toString()
        val nom = nomPlombier1.text.toString()
        val type = typeIntervention1.text.toString()

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = PaysDB.getInstance(act)
                val dao = db?.PaysDAO()
                val produit = Pays(numero,date,nom)
                if(mode == "modif")
                    dao?.modifier(produit)
                else
                    dao?.ajouter(produit)

                return null
            }


            override fun onPostExecute(result: Void?) {
                Toast.makeText(context,"Enregistree avec succees",Toast.LENGTH_SHORT).show()
                returnToList()

            }
        }.execute()
    }


    fun returnToList(){
        var intent = Intent(this, exo3::class.java)
        startActivity(intent)
    }
}