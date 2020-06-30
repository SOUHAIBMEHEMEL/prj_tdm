package com.example.android.geoMob

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pays(
    @PrimaryKey() var image: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,

    @ColumnInfo(name = "hymne") var hymne: Int,
    @ColumnInfo(name = "surface") var surface: String,
    @ColumnInfo(name = "population") var population: String

){
    constructor():this(0,"","",0,"",""){

    }
}