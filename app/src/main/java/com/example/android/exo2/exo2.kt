package com.example.android.exo2

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.exo2.*
import android.util.JsonWriter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ajouter_intervention.*
import java.io.*
import java.util.*
import kotlin.text.Typography.section


class exo2 : AppCompatActivity() {

    var result = ArrayList<Intervention>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val params = getSharedPreferences("COLOR", 0)
        val app_color = params.getInt("macouleur", 0)
        if (app_color == 0) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(app_color)
        }
        setContentView(R.layout.exo2)

        //la liste des interventions
        var listView: ListView?
        listView = findViewById(R.id.listView)

        //calendar
        val c= Calendar.getInstance()
        val year= c.get(Calendar.YEAR)
        val month= c.get(Calendar.MONTH)
        val day= c.get(Calendar.DAY_OF_MONTH)
        val monthValue=month+1

        result = readFile()
        result.sort()

        var result1 = ArrayList<Intervention>()
        var adapter = listAdapter(this, result)
        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()

        setEvent(AjouterIntervention, com.example.android.exo2.AjouterIntervention::class.java)

        // Supprimer l'intervention cliquee
        listView.setOnItemClickListener { parent, view, position, id ->
            result.remove(result[position])
            result.sort()
            adapter?.notifyDataSetChanged()
            writeToFile()
            Toast.makeText(this, "Intervention supprimee", Toast.LENGTH_SHORT).show()
        }

        // visualiser calendrier
        showCalendarBtn.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year1, month1, dayOfMonth1 ->
                var monthValue=""
                var dayValue=""

                if (month1<9){monthValue="0"+(month1+1)}
                else{monthValue=""+(month1+1)}

                if (dayOfMonth1<10){dayValue="0"+dayOfMonth1}

                val  day1 : String = ""+dayValue+"/"+monthValue+"/"+year1+""
                Toast.makeText(this@exo2, day1,Toast.LENGTH_SHORT).show()
                result1.removeAll(result1)
                for (i in 0 until result.size){
                    if (result[i].date.equals(day1.toString(),true)){
                        result1.add(result[i])
                        result1.sort()
                        adapter = listAdapter(this@exo2,result1)
                        listView?.adapter = adapter
                        adapter?.notifyDataSetChanged()
                        Toast.makeText(this@exo2, day1,Toast.LENGTH_SHORT).show()
                    }
                }

            },year,month,day)
            dpd.show()

        }



    }

    private fun setEvent(btn : Button, cls: Class<*>) {
        btn.setOnClickListener({
            val intent = Intent(this, cls)
            startActivity(intent)
        })
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

    fun writeToFile(){
        val fileName = "file.txt"
        val fos: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(result)
        os.close()
        fos.close()
        Toast.makeText(this,"Données enregistrées", Toast.LENGTH_SHORT).show()

    }

}