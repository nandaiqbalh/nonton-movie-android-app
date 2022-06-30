package com.nandaiqbalh.nontonmoviekotlin.authentication.signin

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.authentication.User
import com.nandaiqbalh.nontonmoviekotlin.authentication.signup.SignUpActivity
import com.nandaiqbalh.nontonmoviekotlin.home.HomeActivity

class SignInActivity : AppCompatActivity() {

    lateinit var btnSignIn: Button
    lateinit var btnSignUp: Button

    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText

    lateinit var mDatabase: DatabaseReference

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
        btnSignUp = findViewById(R.id.btn_sign_up)

        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
    }

    private fun mainButton() {

        // btn sign in
        btnSignIn.setOnClickListener {

            var txtUsername = edtUsername.text.toString()
            var txtPassword = edtPassword.text.toString()

            if (txtUsername.equals("")) {
                edtUsername.error = "Oops! This field can not be blank!"
                edtUsername.requestFocus()
            } else if (txtPassword.equals("")) {
                edtPassword.error = "Oops! This field can not be blank!"
                edtPassword.requestFocus()
            } else {
                pushLogin(txtUsername, txtPassword)
            }
        }

        // btn sign up
        btnSignUp.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }


    }

    private fun pushLogin(txtUsername: String, txtPassword: String) {

        mDatabase.child(txtUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var user = dataSnapshot.getValue(User::class.java)

                if (user == null) {
                    // ketika username tidak ditemukan, munculkan toaster
                    Toast.makeText(this@SignInActivity, "User not found!", Toast.LENGTH_LONG).show()
                } else {

                    // pengecekan apakah credential sudah match
                    if (user.password.equals(txtPassword)) {
                        // kalau sudah match akan masuk ke home
                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)

                    } else {

                        // kalau passwordnya tidak match, maka akan muncul toaster
                        Toast.makeText(this@SignInActivity, "Password doesn't match with our record!", Toast.LENGTH_LONG).show()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@SignInActivity, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}