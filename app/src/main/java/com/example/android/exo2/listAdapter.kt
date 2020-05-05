package com.example.android.exo2

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.android.exo1.R

class listAdapter (private var activity: Activity, private var items: ArrayList<Intervention>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var numero: TextView? = null
        var date: TextView? = null
        var nomPlombier: TextView? = null
        var typeIntervention: TextView? = null

        init {
            this.numero = row?.findViewById<TextView>(R.id.numero)
            this.date = row?.findViewById<TextView>(R.id.date)
            this.nomPlombier = row?.findViewById<TextView>(R.id.nomPlombier)
            this.typeIntervention = row?.findViewById<TextView>(R.id.typeIntervention)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_item, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var userDto = items[position]
        viewHolder.numero?.text = userDto.numero
        viewHolder.date?.text = userDto.date
        viewHolder.nomPlombier?.text = userDto.nomPlombier
        viewHolder.typeIntervention?.text = userDto.typeIntervention

        return view as View
    }

    override fun getItem(i: Int): Intervention {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}