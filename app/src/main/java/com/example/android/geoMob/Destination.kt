package com.example.android.geoMob

import com.example.android.exo1.R
import java.util.*

data class Destination(
    var image: Int,
    var title: String,
    var description: String
)

object DataProvider {

    val data: MutableList<Pays>
        get() {
            val dataList = ArrayList<Pays>()

            val images = images

            for (i in images.indices) {
                val destination = Pays(images[i], "Destination $i", descriptions[i])
                dataList.add(destination)
            }

            return dataList
        }

    private val images: IntArray
        get() = intArrayOf(
            R.drawable.algerie, R.drawable.maroc, R.drawable.egypte
        )

    private val descriptions: Array<String>
        get() =arrayOf("a", "b", "c")
}