package com.example.android.geoMob

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.android.exo1.R
import com.example.android.exo1.R.id.*

class InterventionListAdapter(private val _ctx: Context, rId: Int, private val produits: MutableList<Pays>) : ArrayAdapter<Pays>(_ctx, rId, produits) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = _ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.intervention_item, parent, false)
        val numero = rowView.findViewById<TextView>(numero2)
        val date = rowView.findViewById<TextView>(date2)
        val nomPlombier = rowView.findViewById<TextView>(nomPlombier2)
        val typeInt = rowView.findViewById<TextView>(typeIntervention2)

        var btnEdit = rowView.findViewById<TextView>(modifierBtn2)
        var btnRemove = rowView.findViewById<TextView>(supprimerBtn2)

        val p = produits[position]
        numero.text= p.numero.toString()
        date.text = p.date
        nomPlombier.text = p.nom_plombier
        typeInt.text= p.type

        btnEdit.setOnClickListener({
            val intent = Intent(_ctx, EditIntervention::class.java)
            intent.putExtra("mode", "modif")
            intent.putExtra("numero", p.numero)
            _ctx.startActivity(intent)
        })

        btnRemove.setOnClickListener({
            supprimerIntervention(_ctx, p)
        })


        return rowView
    }

    fun supprimerIntervention(ctx: Context, p: Pays) {
        var adapter = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = PaysDB.getInstance(ctx)
                val dao = db?.PaysDAO()
                dao?.supprimer(p)
                return null
            }


            override fun onPostExecute(result: Void?) {
                Toast.makeText(_ctx,"intervention supprimée",Toast.LENGTH_SHORT).show()
                adapter.remove(p)

            }
        }.execute()
    }

}