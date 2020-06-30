package com.example.android.geoMob

import androidx.room.*
import com.example.android.geoMob.Pays

@Dao
interface PaysDAO {

    @Query("SELECT * FROM Pays")
    fun getProduits(): MutableList<Pays>

    @Query("SELECT * FROM Pays WHERE numero = :num")
    fun getProduit(num: Int): List<Pays>

    @Insert
    fun ajouter(intervention : Pays)

    @Delete
    fun supprimer(produit: Pays)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun modifier(intervention:  Pays)
}