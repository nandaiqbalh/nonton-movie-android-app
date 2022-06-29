package com.nandaiqbalh.nontonmoviekotlin.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.nontonmoviekotlin.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screenn)

        // full screen
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // create variable handler
        var handler = Handler()
        handler.postDelayed({

            var intent = Intent(this@SplashScreenActivity, OnBoardingOneActivity::class.java)
            startActivity(intent)
            finish()

        }, 5000)
    }
}