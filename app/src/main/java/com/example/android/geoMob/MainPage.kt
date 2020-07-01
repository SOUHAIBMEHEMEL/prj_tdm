package com.example.android.geoMob

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.exo1.R

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_a.*


class MainPage : AppCompatActivity(), MyCommunicator {

    private var mIsDualPane = false
    private var db: PaysDB? = null
    private var dao: PaysDAO? = null
    private var list_pays: MutableList<Pays>? = null
    private var mp: MediaPlayer? = null
    private var mc: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        initDB()

        // If FragmentB is present in activity_main.xml, then we are in Tablet
        // Else the app is running in handset
        val fragmentBView = findViewById<View>(R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
    }

    override fun displayDetails(title: String, description: String, hymne:Int, surface:String, population:String, historique: String, ressource: String, personnalite: String, video:String, images:String) {

        if (mIsDualPane) { // If we are in Landscape mode
            txvTitle.text = title
            txvDescription.text = description
            txtsurface.text=surface
            txtpopulation.text=population
            historiqueDate.text= historique
            historiqueDescription.text= historique

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
                lireVideo(list_video[2].toInt(), R.id.Video3)
            }
            videoBtn4.setOnClickListener(){
                lireVideo(list_video[3].toInt(), R.id.Video4)
            }

            //set images
            val list_images: List<String> = images.split("_")
            image1.setImageResource(list_images[0].toInt())
            image2.setImageResource(list_images[1].toInt())
            image3.setImageResource(list_images[2].toInt())
            image4.setImageResource(list_images[3].toInt())

            //set personalite
            val personalite: List<String> = personnalite.split("_")
            personaliteImage.setImageResource(personalite[0].toInt())
            personaliteDescription.text=personnalite[1].toString()

            //image1.setImageResource(R.drawable.bresil)
        } else { // When we are in Portrait Mode
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("surface", surface)
            intent.putExtra("population", population)
            intent.putExtra("historique", historique)
            intent.putExtra("hymne", hymne)
            intent.putExtra("video", video)
            intent.putExtra("images", images)
            intent.putExtra("personnalite", personnalite)
            startActivity(intent)
        }

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


    fun initDB() {
        var act = this


        object : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                act.db = PaysDB.getInstance(act)
                act.dao = db?.PaysDAO()
                list_pays = act.dao?.getProduits()
                if (list_pays!!.size == 0){
                    act.dao?.ajouter(Pays(
                        R.drawable.algerie,"Algerie",
                        "Description algerie",
                        R.raw.hymne_algerie,
                        "2M km2",
                        "45 milion",
                        "1954 Revolution",
                        "Petrol ressource naturelle",
                        ""+R.drawable.ibn_badis+"_Ibn Badis Abdelhamid (1889 - 1940): Une figure emblématique du mouvement réformiste musulman en Algérie",
                        ""+R.raw.video_algerie1+"_"+R.raw.video_algerie2+"_"+R.raw.video_algerie3+"_"+R.raw.video_algerie1,
                        ""+R.drawable.algerie_img1+"_"+R.drawable.algerie_img2+"_"+R.drawable.algerie_img3+"_"+R.drawable.algerie_img4

                        ))
                }

                return null
            }

            override fun onPostExecute(result: Void?) {
                if(list_pays != null) {

                    val adapter = RecyclerAdapter(act, list_pays!!)
                    recycler_view.adapter = adapter

                    val manager = LinearLayoutManager(act)
                    manager.orientation = RecyclerView.VERTICAL
                    recycler_view.layoutManager = manager
                    Toast.makeText(act,"données chargeés",Toast.LENGTH_SHORT).show()
                }

            }
        }.execute()
    }
}
