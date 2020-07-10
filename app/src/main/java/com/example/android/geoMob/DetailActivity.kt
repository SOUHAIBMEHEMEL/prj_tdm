package com.example.android.geoMob

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.exo1.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {

    private var mp: MediaPlayer? = null
    private var mc: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve data coming from MainActivity.java
        val description = intent.getStringExtra("description")
        val title = intent.getStringExtra("title")
        val surface=intent.getStringExtra("surface")
        val population= intent.getStringExtra("population")
        val historique=intent.getStringExtra("historique")
        val hymne= intent.getIntExtra("hymne",R.raw.hymne_algerie)
        val drap= intent.getIntExtra("drapeau",R.drawable.algerie)
        val video = intent.getStringExtra("video")
        val images = intent.getStringExtra("images")
        val personnalite = intent.getStringExtra("personnalite")

        // Pass the data to FragmentB to display it
        txvTitle.text = title
        txvDescription.text = description
        txtsurface.text=surface
        txtpopulation.text=population
        historiqueDate.text= historique
        historiqueDescription.text= historique
        drapeau.setImageResource(drap)

        // lire Hymne
        btnHymne.setOnClickListener(){
            lireAudio(hymne)
        }

        //lire videos
        val list_video: List<String> = video.split("_")

        videoBtn1.setOnClickListener(){
            lireVideo(list_video[0].toInt(), R.id.Video1)
        }
        videoBtn2.setOnClickListener(){
            lireVideo(list_video[1].toInt(), R.id.Video2)
        }
        videoBtn3.setOnClickListener(){
            lireVideo(list_video[3].toInt(), R.id.Video3)
        }
        videoBtn4.setOnClickListener(){
            lireVideo(list_video[4].toInt(), R.id.Video4)
        }

        // set images
        val list_images: List<String> = images.split("_")
        image1.setImageResource(list_images[0].toInt())
        image2.setImageResource(list_images[1].toInt())
        image3.setImageResource(list_images[2].toInt())
        image4.setImageResource(list_images[3].toInt())

        //set personalite
        val personalite: List<String> = personnalite.split("_")
        personaliteImage.setImageResource(personalite[0].toInt())
        personaliteDescription.text=personnalite[1].toString()



    }

    fun lireAudio(resId: Int) {

        if (mp == null) {        //set up MediaPlayer
            mp = MediaPlayer.create(this, resId)

            try {
                mp!!.prepare()

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        if (!mp!!.isPlaying())
            mp!!.start()
        else{
            mp!!.pause()
            mp=null
        }

    }

    private fun lireVideo(resId: Int, videoRessource: Int) {
        val vv = findViewById<VideoView>(videoRessource)
        mc=null
        if (mc == null) {

            mc = MediaController(this)
            vv.setMediaController(mc)
            val video = Uri.parse("android.resource://" + this?.packageName + "/"
                    + resId) //do not add any extension
            vv.setVideoURI(video)
            vv.start()
        } else {
            if (!vv.isPlaying) {
                vv.start()

            } else {
                vv.pause()
            }
        }
    }
}
