package com.nandaiqbalh.nontonmoviekotlin.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.adapter.CheckoutAdapter
import com.nandaiqbalh.nontonmoviekotlin.home.HomeActivity
import com.nandaiqbalh.nontonmoviekotlin.model.Checkout
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs

class CheckoutActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView

    private lateinit var btnCancel: Button
    private lateinit var btnCheckout: Button

    private lateinit var rvCheckout: RecyclerView

    private var dataList = ArrayList<Checkout>()
    private var total: Int = 0

    private lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        // init
        init()

        // MEMASUKKAN DATA DARI INTENT KE DALAM DATALIST
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        // menjumlahkan total harga
        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }

        // menambahkan data (total harga yang harus dibayar only)
        dataList.add(Checkout("Total harus dibayar", total.toString()))

        rvCheckout.layoutManager = LinearLayoutManager(this)
        rvCheckout.adapter = CheckoutAdapter(dataList){
            // do something
        }

        mainButton()

        for (i in dataList.indices){
            Log.d("Datalist", "" + dataList[i].kursi.toString())

        }

    }

    private fun init(){

        btnBack = findViewById(R.id.btn_back)

        btnCancel = findViewById(R.id.btn_cancel)
        btnCheckout = findViewById(R.id.btn_checkout_now)

        rvCheckout = findViewById(R.id.rv_checkout)

        sharedPrefs = SharedPrefs(this)

    }

    private fun mainButton(){

        btnBack.setOnClickListener {
            var intent = Intent(this@CheckoutActivity, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        btnCancel.setOnClickListener {
            var intent = Intent(this@CheckoutActivity, HomeActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        // checkout now
        btnCheckout.setOnClickListener {
            var intent = Intent(this@CheckoutActivity, CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }

    }
}