package com.nandaiqbalh.nontonmoviekotlin.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.adapter.TicketDetailAdapter
import com.nandaiqbalh.nontonmoviekotlin.home.HomeActivity
import com.nandaiqbalh.nontonmoviekotlin.model.Checkout
import com.nandaiqbalh.nontonmoviekotlin.model.Film

class TicketActivity : AppCompatActivity() {

    private lateinit var ivPosterTicketDetail : ImageView
    private lateinit var tvTitleTicketDetail: TextView
    private lateinit var tvGenreTicketDetail: TextView
    private lateinit var tvRateTicketDetail: TextView

    private lateinit var rvTicketDetail: RecyclerView

    private var dataList = ArrayList<Checkout>()

    private lateinit var btnBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        // ambil data
        val data = intent.getParcelableExtra<Film>("data")

        // init
        init(data)

        ambilData(data)

        // btn triggered
        mainButton()
    }

    private fun init(data: Film?){
        ivPosterTicketDetail = findViewById(R.id.iv_poster_ticket_detail)
        tvTitleTicketDetail = findViewById(R.id.tv_title_ticket_detail)
        tvGenreTicketDetail = findViewById(R.id.tv_genre_ticket_detail)
        tvRateTicketDetail = findViewById(R.id.tv_rate_ticket_detail)

        rvTicketDetail = findViewById(R.id.rv_ticket_detail)

        btnBack = findViewById(R.id.btn_back)

    }

    private fun ambilData(data: Film?){

        tvTitleTicketDetail.text = data?.judul
        tvGenreTicketDetail.text = data?.genre
        tvRateTicketDetail.text = "${data?.rating} / 10.0"

        Glide.with(this)
            .load(data?.poster)
            .into(ivPosterTicketDetail)

        rvTicketDetail.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("A1"))
        dataList.add(Checkout("A2"))

        rvTicketDetail.adapter = TicketDetailAdapter(dataList){

        }

    }

    private fun mainButton(){

        btnBack.setOnClickListener {
            var intent = Intent(this@TicketActivity, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

}