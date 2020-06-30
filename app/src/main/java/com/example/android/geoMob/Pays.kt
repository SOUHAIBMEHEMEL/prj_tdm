package com.example.android.geoMob

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pays(
    @PrimaryKey() var image: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String

){
    constructor():this(0,"",""){

    }
}