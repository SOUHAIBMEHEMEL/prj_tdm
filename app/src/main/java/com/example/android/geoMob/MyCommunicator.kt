package com.example.android.geoMob

interface MyCommunicator { // Meant for inter-fragment communication

    fun displayDetails(title: String, description: String, hymne:Int, image:Int, surface:String, population:String, historique: String, ressource: String, personnalite: String, video: String, images:String)
}
