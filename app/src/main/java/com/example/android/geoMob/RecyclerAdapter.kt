package com.example.android.geoMob

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerAdapter(private val context: Context?, private var list: MutableList<Pays>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = list[position]

        holder.setData(current, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private var pos: Int = 0
        lateinit var current: Pays

        fun setData(current: Pays, position: Int) {
            itemView.tvTitle.text = current.title
            itemView.img_row.setImageResource(current.image)
            this.pos = position
            this.current = current

        }

        fun setListeners() {

            itemView.setOnClickListener {
                val myCommunicator = context as MyCommunicator
                myCommunicator.displayDetails(current.title, current.description, current.hymne,current.surface,current.population,
                    current.historique,current.ressource,current.personnalite, current.video,current.images)
                Toast.makeText(context,""+current.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
