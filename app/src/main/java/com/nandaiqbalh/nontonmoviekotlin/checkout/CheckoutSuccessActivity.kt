package com.nandaiqbalh.nontonmoviekotlin.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.home.HomeActivity

class CheckoutSuccessActivity : AppCompatActivity() {

    private lateinit var btnToHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_success)

        // init
        init()

        // main button
        mainButton()

    }

    private fun init(){

        btnToHome = findViewById(R.id.btn_to_home)
    }

    private fun mainButton(){

        btnToHome.setOnClickListener {

            var intent = Intent(this@CheckoutSuccessActivity, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}