package com.example.android.exo2

import java.text.SimpleDateFormat
import java.util.*
import java.io.Serializable
import kotlinx.*


class Intervention :  Serializable,Comparable<Intervention> {
    var numero: String = ""
    var date: String = ""
    var nomPlombier: String = ""
    var typeIntervention: String = ""

    constructor() {}

    constructor(numero: String, date: String, nomPlombier: String, TypeIntervention: String) {
        this.numero = numero
        this.date = date
        this.nomPlombier= nomPlombier
        this.typeIntervention= TypeIntervention
    }

    fun getDate(): Calendar {
        val date1 = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        date1.setTime(sdf.parse(this.date))
        return date1
    }

    override fun compareTo(other:Intervention):Int
    {
        return this.getDate().compareTo(other.getDate())
    }
}