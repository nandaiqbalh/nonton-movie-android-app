package com.nandaiqbalh.nontonmoviekotlin.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.adapter.PlayerAdapter
import com.nandaiqbalh.nontonmoviekotlin.model.Film
import com.nandaiqbalh.nontonmoviekotlin.model.Plays

class DetailActivity : AppCompatActivity() {
    
    private lateinit var ivPosterDetail : ImageView
    private lateinit var tvTitleDetail: TextView
    private lateinit var tvGenreDetail: TextView
    private lateinit var tvRateDetail: TextView
    private lateinit var tvDescriptionDetail: TextView

    private lateinit var btnSelectSeats : Button

    private lateinit var rvWhosPlay: RecyclerView

    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Plays>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // ambil data
        val data = intent.getParcelableExtra<Film>("data")

        // init
        init(data)

        // get data from intent
        ambilData(data)

        // who play
        whoPlay()

        // button pressed
        mainButton(data)

    }
    
    private fun init(data: Film?){
        ivPosterDetail = findViewById(R.id.iv_poster_detail)
        tvTitleDetail = findViewById(R.id.tv_title_detail)
        tvGenreDetail = findViewById(R.id.tv_genre_detail)
        tvRateDetail = findViewById(R.id.tv_rate_detail)
        tvDescriptionDetail = findViewById(R.id.tv_description_detail)

        btnSelectSeats = findViewById(R.id.btn_select_seats)

        rvWhosPlay = findViewById(R.id.rv_player)

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data?.judul.toString())
            .child("play")

    }

    private fun mainButton(data: Film?){
        btnSelectSeats.setOnClickListener {

            var intent = Intent(this@DetailActivity, SelectSeatActivity::class.java).putExtra("data", data)
            startActivity(intent)

        }
    }

    private fun ambilData(data: Film?){

        tvTitleDetail.text = data?.judul
        tvGenreDetail.text = data?.genre
        tvRateDetail.text = "${data?.rating} / 10.0"
        tvDescriptionDetail.text = data?.desc

        Glide.with(this)
            .load(data?.poster)
            .into(ivPosterDetail)

    }

    private fun whoPlay(){
        rvWhosPlay.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData() {

        // untuk whos plays
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // clear biar ga bentrok
                dataList.clear()

                for (ambilSnapshot in snapshot.children){
                    var film = ambilSnapshot.getValue(Plays::class.java)
                    if (film != null) {
                        dataList.add(film)
                    }
                }

                rvWhosPlay.adapter = PlayerAdapter(dataList){

                }


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }

        })
    }

}