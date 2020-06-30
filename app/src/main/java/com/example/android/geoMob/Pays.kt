package com.example.android.geoMob

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pays(
    @PrimaryKey() var numero: Int,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "nom plombier") var nom_plombier: String,
    @ColumnInfo(name = "type intervention") var type: String
){
    constructor():this(0,"","",""){

    }
}