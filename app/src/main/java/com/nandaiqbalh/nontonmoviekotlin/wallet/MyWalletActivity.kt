package com.nandaiqbalh.nontonmoviekotlin.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.adapter.WalletAdapter
import com.nandaiqbalh.nontonmoviekotlin.home.HomeActivity
import com.nandaiqbalh.nontonmoviekotlin.home.fragment.SettingFragment
import com.nandaiqbalh.nontonmoviekotlin.model.Wallet

class MyWalletActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView

    private lateinit var rvTransaksi: RecyclerView

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet2)

        // init
        init()

        // dummy
        tambahDummyData()

        rvTransaksi.layoutManager = LinearLayoutManager(this)
        rvTransaksi.adapter = WalletAdapter(dataList){

        }

        // mainButton
        mainButton()

    }

    private fun init(){

        btnBack = findViewById(R.id.btn_back)
        rvTransaksi = findViewById(R.id.rv_transaction)
    }

    private fun mainButton(){

        btnBack.setOnClickListener {
            val intent = Intent(this@MyWalletActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun tambahDummyData(){
        dataList.add(
            Wallet(
                "Coco", "Sabtu, 22 Juni 2022", 60000.0, "0"
            )
        )
        dataList.add(
            Wallet(
                "Dunkirk", "Minggu, 16 Juni 2022", 60000.0, "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up via Gopay", "Sabtu, 15 Juni 2022", 1000000.0, "1"
            )
        )
        dataList.add(
            Wallet(
                "Greyhound", "Jumat, 14 Juni 2022", 60000.0, "0"
            )
        )
    }
}