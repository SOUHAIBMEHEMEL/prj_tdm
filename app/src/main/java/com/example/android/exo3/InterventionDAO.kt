package com.example.android.exo3

import androidx.room.*

@Dao
interface InterventionDAO {

    @Query("SELECT * FROM InterventionExo3")
    fun getProduits(): MutableList<InterventionExo3>

    @Query("SELECT * FROM InterventionExo3 WHERE numero = :num")
    fun getProduit(num: Int): List<InterventionExo3>

    @Insert
    fun ajouter(intervention : InterventionExo3)

    @Delete
    fun supprimer(produit: InterventionExo3)
}