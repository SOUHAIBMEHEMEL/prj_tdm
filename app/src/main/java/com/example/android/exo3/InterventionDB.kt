package com.example.android.exo3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(InterventionExo3::class), version = 1)
abstract class InterventionDB (): RoomDatabase(){
    abstract fun InterventionDAO():InterventionDAO

    companion object {
        private var instance: InterventionDB? = null

        fun getInstance(context: Context): InterventionDB? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, InterventionDB::class.java, "produit.db").build()
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }


}