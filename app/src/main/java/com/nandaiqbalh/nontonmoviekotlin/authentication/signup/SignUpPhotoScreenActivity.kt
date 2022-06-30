package com.nandaiqbalh.nontonmoviekotlin.authentication.signup

import android.R.attr
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.home.HomeActivity
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs
import java.util.*


class SignUpPhotoScreenActivity : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_CAPTURE = 1 // akan digunakan ketika pencarian foto
    var statusAdd: Boolean = false // akan digunakan untuk status penambahan foto
    lateinit var filePath : Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference

    lateinit var sharedPrefs: SharedPrefs

    lateinit var btnSave: Button
    lateinit var btnSkip: Button
    lateinit var btnUpload : ImageView

    lateinit var tvWelcome: TextView

    lateinit var ivProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo)


        // init
        init()
        
        // button pressed
        mainButton()
        
    }
    
    private fun init (){
        
        btnSave = findViewById(R.id.btn_save)
        btnSkip = findViewById(R.id.btn_skip)
        btnUpload = findViewById(R.id.btn_upload)

        tvWelcome = findViewById(R.id.tv_welcome)
        tvWelcome.setText("Welcome,\n" + intent.getStringExtra("name"))

        ivProfile = findViewById(R.id.iv_profile)

        sharedPrefs = SharedPrefs(this)

        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        
    }
    
    private fun mainButton(){

        btnSkip.setOnClickListener {
            var intent = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btnSave.setOnClickListener {
            if (filePath != null){

                // progress upload
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                // upload
                var ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath).addOnSuccessListener {
                    progressDialog.dismiss()
                    Toast.makeText(this@SignUpPhotoScreenActivity, "Uploaded", Toast.LENGTH_LONG)
                        .show()

                    // simpan URL ke shared prefs
                    ref.downloadUrl.addOnSuccessListener {
                        sharedPrefs.setValue("url", it.toString())
                    }

                    var intent = Intent(this@SignUpPhotoScreenActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this@SignUpPhotoScreenActivity, "Failed", Toast.LENGTH_LONG).show()
                    }

                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload " + progress.toInt() + " %")
                    }

            } else {

                // kasih tau kalo user kalo user belum mencari fotonya
                Toast.makeText(this@SignUpPhotoScreenActivity, "Please insert a picture first!", Toast.LENGTH_LONG).show()
            }
        }

        btnUpload.setOnClickListener {

            if (statusAdd){
                statusAdd = false
                btnSave.visibility = View.VISIBLE
                btnUpload.setImageResource(R.drawable.ic_btn_delete)
                ivProfile.setImageResource(R.drawable.ic_user_prof)
                
            } else {
                // kalau status add masih false, masukkan ke dexter
                Dexter.withActivity(this).withPermission(android.Manifest.permission.CAMERA).withListener(this).check()
            }
        }
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        TODO("Not yet implemented")
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
        Toast.makeText(this@SignUpPhotoScreenActivity, "Permission denied!", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this@SignUpPhotoScreenActivity, "Upload profile picture later?", Toast.LENGTH_LONG).show()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === REQUEST_IMAGE_CAPTURE && requestCode === RESULT_OK) {

            var bitmap = data?.extras?.get("data") as Bitmap
            statusAdd = true

            filePath = data.getData()!!
            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.centerCropTransform())
                .into(ivProfile)

            btnSave.visibility = View.VISIBLE
            btnUpload.setImageResource(R.drawable.ic_btn_delete);
        }
    }
}