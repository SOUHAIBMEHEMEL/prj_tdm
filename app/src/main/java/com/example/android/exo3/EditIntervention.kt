package com.example.android.exo3

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_intervention)

        saveBtn.setOnClickListener{
            AjouterInterv()
        }
    }


    fun AjouterInterv() {
        var act = this

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = InterventionDB.getInstance(act)
                val dao = db?.InterventionDAO()

                val numero = numero1.text.toString().toInt()
                val date = date1.text.toString()
                val nom = nomPlombier1.text.toString()
                val type = typeIntervention1.text.toString()
                val produit = InterventionExo3(numero,date,nom,type)
                dao?.ajouter(produit)

                return null
            }


            override fun onPostExecute(result: Void?) {
                Toast.makeText(context,"Ajoutee avec succees",Toast.LENGTH_SHORT).show()
                returnToList()

            }
        }.execute()
    }
    fun returnToList(){
        var intent = Intent(this, exo3::class.java)
        startActivity(intent)
    }
}