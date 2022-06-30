package com.nandaiqbalh.nontonmoviekotlin.authentication.signin

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nandaiqbalh.nontonmoviekotlin.R

class SignInActivity : AppCompatActivity() {

    lateinit var btnSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

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

        btnSignIn = findViewById(R.id.btn_sign_in)
    }

    private fun mainButton() {

        btnSignIn.setOnClickListener {

            // Write a message to the database
            val database = Firebase.database
            val myRef = database.getReference("message")

            myRef.setValue("Hello, World!")
        }
    }
}