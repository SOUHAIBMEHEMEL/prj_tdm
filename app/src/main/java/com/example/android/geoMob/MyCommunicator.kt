package com.example.android.geoMob

interface MyCommunicator { // Meant for inter-fragment communication

    fun displayDetails(title: String, description: String, hymne:Int, surface:String, population:String)
}
