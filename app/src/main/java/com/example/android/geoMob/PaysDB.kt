package com.example.android.geoMob

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.geoMob.Pays
import com.example.android.geoMob.PaysDAO


@Database(entities = arrayOf(Pays::class), version = 1)
abstract class PaysDB (): RoomDatabase(){
    abstract fun PaysDAO(): PaysDAO

    companion object {
        private var instance: PaysDB? = null

        fun getInstance(context: Context): PaysDB? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, PaysDB::class.java, "GeoMobDb14.db").build()
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }


}