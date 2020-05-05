package com.example.android.exo2

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.exo1.Activity2
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ajouter_intervention.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.ArrayList

class AjouterIntervention: AppCompatActivity() {

    var interventions = ArrayList<Intervention>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences("COLOR", 0)
        val app_color = params.getInt("macouleur", 0)
        if(app_color==0){setTheme(R.style.AppTheme)}
        else { setTheme(app_color)}
        setContentView(R.layout.ajouter_intervention)
        interventions = readFile()


        setEvent(Annuler, exo2::class.java)
        AjouterInter.setOnClickListener {
            var numero =numero.text.toString()
            var date = date.text.toString()
            var nomPlombier = nomPlombier.text.toString()
            var typeIntervention = typeIntervention.text.toString()
            var intervention= Intervention(numero,date,nomPlombier,typeIntervention)
            interventions.add(intervention)
            writeToFile()
        }

    }


    private fun setEvent(btn : Button, cls: Class<*>) {

        btn.setOnClickListener({
            val intent = Intent(this, cls)
            startActivity(intent)
        })

    }

    fun writeToFile(){
        val fileName = "file.txt"
        val fos: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(interventions)
        os.close()
        fos.close()
        Toast.makeText(this,"Ajoutee avec succee", Toast.LENGTH_SHORT).show()

    }

    fun readFile() : ArrayList<Intervention>{
        val fileName = "file.txt"
        val fis: FileInputStream = openFileInput(fileName)
        var iss = ObjectInputStream(fis)
        val interventions: ArrayList<Intervention> = iss.readObject() as ArrayList<Intervention>
        iss.close()
        fis.close()

        return interventions
    }


}