package com.nandaiqbalh.nontonmoviekotlin.authentication.signin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs
import java.util.regex.Pattern


class SignInActivity : AppCompatActivity() {

    lateinit var btnSignIn: Button
    lateinit var btnSignUp: Button

    lateinit var edtUsername: EditText
    lateinit var edtPassword: EditText

    lateinit var mDatabase: DatabaseReference

    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // init
        init()

        // button pressed
        mainButton()

        // shared prefs helper
        spValueHelper()

    }

    private fun init() {

        btnSignIn = findViewById(R.id.btn_sign_in)
        btnSignUp = findViewById(R.id.btn_sign_up)

        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)

        mDatabase = FirebaseDatabase.getInstance().getReference("User") // "User" merupakan path nama tabel dalam database

        sharedPrefs = SharedPrefs(this)
    }

    private fun mainButton() {

        // btn sign in
        btnSignIn.setOnClickListener {

            val txtUsername = edtUsername.text.toString().trim()
            val txtPassword =
                edtPassword.text.toString().trim { it <= ' ' } // untuk validasi password

            val PASSWORD_PATTERN: Pattern = Pattern.compile(
                "^" +
                        "(?=.*[0-9])" +  //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +  //any letter
                        // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        // "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +  //at least 8 characters
                        "$"
            )

            if (txtUsername.isEmpty()) {
                edtUsername.error = "Oops! This field can not be blank!"
                edtUsername.requestFocus()
            } else if (txtPassword.isEmpty()) {
                edtPassword.error = "Oops! This field can not be blank!"
                edtPassword.requestFocus()
            } else if (!PASSWORD_PATTERN.matcher(txtPassword).matches()) {
                edtPassword.error = "The password must have at least 8 digits with number and char!"
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
                        finishAffinity()

                        // simpan value
                        sharedPrefs.setValue("name", user.name.toString());
                        sharedPrefs.setValue("email", user.email.toString());
                        sharedPrefs.setValue("username", user.username.toString());
                        sharedPrefs.setValue("url", user.url.toString());
                        sharedPrefs.setValue("saldo", user.saldo.toString());
                        sharedPrefs.setValue("status", "1");

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

    private fun spValueHelper(){

        sharedPrefs.setValue("onboarding", "1")

        if (sharedPrefs.getValue("status").equals("1")){

            var intent = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}