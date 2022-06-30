package com.nandaiqbalh.nontonmoviekotlin.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.authentication.signin.SignInActivity
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs

class OnBoardingTwoActivity : AppCompatActivity() {

    lateinit var btn_home: Button
    lateinit var btn_skip: Button

    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        // full screen
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        // init
        init()

        // button pressed
        mainButton()

    }

    private fun init() {
        btn_home = findViewById(R.id.btn_next)
        btn_skip = findViewById(R.id.btn_skip)

        sharedPrefs = SharedPrefs(this)
    }

    private fun mainButton() {

        btn_home.setOnClickListener {
            var intent = Intent(this@OnBoardingTwoActivity, OnBoardingThreeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btn_skip.setOnClickListener {

            sharedPrefs.setValue("onboarding", "1")

            var intent = Intent(this@OnBoardingTwoActivity, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}