package com.nandaiqbalh.nontonmoviekotlin.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.authentication.signin.SignInActivity
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs

class OnBoardingThreeActivity : AppCompatActivity() {

    lateinit var btnGetStarted: Button

    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

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

        btnGetStarted = findViewById(R.id.btn_get_started)

        sharedPrefs = SharedPrefs(this)

    }

    private fun mainButton() {
        btnGetStarted.setOnClickListener {

            sharedPrefs.setValue("onboarding", "1")

            var intent = Intent(this@OnBoardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}