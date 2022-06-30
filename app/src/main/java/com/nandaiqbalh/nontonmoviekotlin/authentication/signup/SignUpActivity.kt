package com.nandaiqbalh.nontonmoviekotlin.authentication.signup

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
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    private lateinit var btnNext: Button

    private lateinit var edtUsername:EditText
    private lateinit var edtPassword:EditText
    private lateinit var edtName:EditText
    private lateinit var edtEmail:EditText

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // init
        init()

        // button pressed
        mainButton()

    }

    private fun init(){

        btnNext = findViewById(R.id.btn_next)

        edtUsername = findViewById(R.id.edt_username)
        edtPassword = findViewById(R.id.edt_password)
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")
    }

    private fun mainButton() {

        btnNext.setOnClickListener {

            var txtUsername = edtUsername.text.toString().trim()
            var txtPassword = edtPassword.text.toString().trim()
            var txtName = edtName.text.toString().trim()
            var txtEmail = edtEmail.text.toString().trim()

            val passwordInput =
                edtPassword.text.toString().trim { it <= ' ' } // untuk validasi password

            val PASSWORD_PATTERN = Pattern.compile(
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
            } else if (passwordInput.isEmpty()) {
                edtPassword.error = "Oops! This field can not be blank!"
                edtPassword.requestFocus()
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                edtPassword.error = "The password must have at least 8 digits with number and char!"
                edtPassword.requestFocus()
            } else if (txtName.isEmpty()) {
                edtName.error = "Oops! This field can not be blank!"
                edtName.requestFocus()
            } else if (txtEmail.isEmpty()) {
                edtEmail.error = "Oops! This field can not be blank!"
                edtEmail.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()) {
                edtEmail.error = "The email address you entered is not valid!"
                edtEmail.requestFocus()
            } else {
                saveUser(txtUsername, txtPassword, txtName, txtEmail)
            }
        }
    }

    private fun saveUser(txtUsername: String, txtPassword: String, txtName: String, txtEmail: String) {

        var user = User()

        // simpen value ke object user dari kelas User Model
        user.username = txtUsername
        user.password = txtPassword
        user.name = txtName
        user.email = txtEmail

        if (txtUsername != null){
            checkingUser(txtUsername, user)
        }

    }

    private fun checkingUser(txtUsername: String, dataUser: User) {

        mDatabaseReference.child(txtUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var user = snapshot.getValue(User::class.java)

                if (user == null){

                    // ketika akun masih null, maka akan bisa registrasi
                    mDatabaseReference.child(txtUsername).setValue(dataUser)

                    // ketika sudah menambahkan ke database, arahkan screen ke Upload Photo Screen
                    var intent = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java).putExtra("name", dataUser.name)
                    startActivity(intent)
                    Toast.makeText(this@SignUpActivity, "Successfully Register!", Toast.LENGTH_LONG).show()

                } else {

                    // ketika akun sudah existing, maka tidak bisa registrasi
                    Toast.makeText(this@SignUpActivity, "User is already exist!", Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {

                // kalo error, tampilin toaster
                Toast.makeText(this@SignUpActivity, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}