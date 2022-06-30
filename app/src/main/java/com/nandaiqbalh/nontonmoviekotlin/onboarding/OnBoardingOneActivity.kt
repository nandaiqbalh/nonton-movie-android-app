package com.nandaiqbalh.nontonmoviekotlin.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.authentication.signin.SignInActivity
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs

class OnBoardingOneActivity : AppCompatActivity() {

    lateinit var btn_next: Button
    lateinit var btn_skip: Button
    
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        // init
        init()

        // button pressed
        mainButton()

        if (sharedPrefs.getValue("onboarding").equals("1")){
            var intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

    }

    private fun init() {
        btn_next = findViewById(R.id.btn_next)
        btn_skip = findViewById(R.id.btn_skip)
        
        sharedPrefs = SharedPrefs(this)
    }

    private fun mainButton() {

        btn_next.setOnClickListener {
            var intent = Intent(this@OnBoardingOneActivity, OnBoardingTwoActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btn_skip.setOnClickListener {

            sharedPrefs.setValue("onboarding", "1")

            var intent = Intent(this@OnBoardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}