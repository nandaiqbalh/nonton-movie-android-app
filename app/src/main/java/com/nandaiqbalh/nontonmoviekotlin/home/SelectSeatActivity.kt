package com.nandaiqbalh.nontonmoviekotlin.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.model.Film

class SelectSeatActivity : AppCompatActivity() {

    private lateinit var tvTitleFilm: TextView

    private lateinit var btnBookTicket: Button
    private lateinit var btnBack: ImageView

    private lateinit var kursiA1: ImageView
    private lateinit var kursiA2: ImageView
    private lateinit var kursiA3: ImageView
    private lateinit var kursiA4: ImageView
    private lateinit var kursiA5: ImageView
    private lateinit var kursiA6: ImageView

    private lateinit var kursiB1: ImageView
    private lateinit var kursiB2: ImageView
    private lateinit var kursiB3: ImageView
    private lateinit var kursiB4: ImageView
    private lateinit var kursiB5: ImageView
    private lateinit var kursiB6: ImageView

    private lateinit var kursiC1: ImageView
    private lateinit var kursiC2: ImageView
    private lateinit var kursiC3: ImageView
    private lateinit var kursiC4: ImageView
    private lateinit var kursiC5: ImageView
    private lateinit var kursiC6: ImageView

    private lateinit var kursiD1: ImageView
    private lateinit var kursiD2: ImageView
    private lateinit var kursiD3: ImageView
    private lateinit var kursiD4: ImageView
    private lateinit var kursiD5: ImageView
    private lateinit var kursiD6: ImageView

    private lateinit var kursiE1: ImageView
    private lateinit var kursiE2: ImageView
    private lateinit var kursiE3: ImageView
    private lateinit var kursiE4: ImageView
    private lateinit var kursiE5: ImageView
    private lateinit var kursiE6: ImageView

    var statusA1: Boolean = false
    var statusA2: Boolean = false
    var statusA3: Boolean = false
    var statusA4: Boolean = false
    var statusA5: Boolean = false
    var statusA6: Boolean = false

    var statusB1: Boolean = false
    var statusB2: Boolean = false
    var statusB3: Boolean = false
    var statusB4: Boolean = false
    var statusB5: Boolean = false
    var statusB6: Boolean = false

    var statusC1: Boolean = false
    var statusC2: Boolean = false
    var statusC3: Boolean = false
    var statusC4: Boolean = false
    var statusC5: Boolean = false
    var statusC6: Boolean = false

    var statusD1: Boolean = false
    var statusD2: Boolean = false
    var statusD3: Boolean = false
    var statusD4: Boolean = false
    var statusD5: Boolean = false
    var statusD6: Boolean = false

    var statusE1: Boolean = false
    var statusE2: Boolean = false
    var statusE3: Boolean = false
    var statusE4: Boolean = false
    var statusE5: Boolean = false
    var statusE6: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)

        val data = intent.getParcelableExtra<Film>("data")

        // init
        init()
        initSeats()

        // set data
        setData(data)

        // main button
        mainButton()
    }

    private fun init() {
        tvTitleFilm = findViewById(R.id.tv_title_film)

        btnBookTicket = findViewById(R.id.btn_book_ticket)
        btnBack = findViewById(R.id.btn_back)

    }

    private fun initSeats() {
        kursiA1 = findViewById(R.id.a1)
        kursiA2 = findViewById(R.id.a2)
        kursiA3 = findViewById(R.id.a3)
        kursiA4 = findViewById(R.id.a4)
        kursiA5 = findViewById(R.id.a5)
        kursiA6 = findViewById(R.id.a6)

        kursiB1 = findViewById(R.id.b1)
        kursiB2 = findViewById(R.id.b2)
        kursiB3 = findViewById(R.id.b3)
        kursiB4 = findViewById(R.id.b4)
        kursiB5 = findViewById(R.id.b5)
        kursiB6 = findViewById(R.id.b6)

        kursiC1 = findViewById(R.id.c1)
        kursiC2 = findViewById(R.id.c2)
        kursiC3 = findViewById(R.id.c3)
        kursiC4 = findViewById(R.id.c4)
        kursiC5 = findViewById(R.id.c5)
        kursiC6 = findViewById(R.id.c6)

        kursiD1 = findViewById(R.id.d1)
        kursiD2 = findViewById(R.id.d2)
        kursiD3 = findViewById(R.id.d3)
        kursiD4 = findViewById(R.id.d4)
        kursiD5 = findViewById(R.id.d5)
        kursiD6 = findViewById(R.id.d6)

        kursiE1 = findViewById(R.id.e1)
        kursiE2 = findViewById(R.id.e2)
        kursiE3 = findViewById(R.id.e3)
        kursiE4 = findViewById(R.id.e4)
        kursiE5 = findViewById(R.id.e5)
        kursiE6 = findViewById(R.id.e6)
    }

    private fun setData(data: Film?) {

        tvTitleFilm.setText(data?.judul)
    }

    private fun mainButton() {

        // back
        btnBack.setOnClickListener {
            var intent = Intent(this@SelectSeatActivity, DetailActivity::class.java)
            startActivity(intent)
        }
    }
}