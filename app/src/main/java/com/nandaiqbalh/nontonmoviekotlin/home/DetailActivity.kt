package com.nandaiqbalh.nontonmoviekotlin.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.model.Film

class DetailActivity : AppCompatActivity() {
    
    private lateinit var ivPosterDetail : ImageView
    private lateinit var tvTitleDetail: TextView
    private lateinit var tvGenreDetail: TextView
    private lateinit var tvRateDetail: TextView
    private lateinit var tvDescriptionDetail: TextView
    
    private lateinit var rvWhosPlay: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // init
        init()

        // get data from intent
        ambilData()



    }
    
    private fun init(){
        ivPosterDetail = findViewById(R.id.iv_poster_detail)
        tvTitleDetail = findViewById(R.id.tv_title_detail)
        tvGenreDetail = findViewById(R.id.tv_genre_detail)
        tvRateDetail = findViewById(R.id.tv_rate_detail)
        tvDescriptionDetail = findViewById(R.id.tv_description_detail)

        rvWhosPlay = findViewById(R.id.rv_player)
    }

    private fun ambilData(){

        val data = intent.getParcelableExtra<Film>("data")

        tvTitleDetail.text = data?.judul
        tvGenreDetail.text = data?.genre
        tvRateDetail.text = "${data?.rating} / 10.0"
        tvDescriptionDetail.text = data?.desc

        Glide.with(this)
            .load(data?.poster)
            .into(ivPosterDetail)

    }

}