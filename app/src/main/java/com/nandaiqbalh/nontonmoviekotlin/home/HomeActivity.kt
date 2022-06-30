package com.nandaiqbalh.nontonmoviekotlin.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.home.fragment.DashboardFragment
import com.nandaiqbalh.nontonmoviekotlin.home.fragment.SettingFragment
import com.nandaiqbalh.nontonmoviekotlin.home.fragment.TicketFragment

class HomeActivity : AppCompatActivity() {

    lateinit var menu1: ImageView
    lateinit var menu2: ImageView
    lateinit var menu3: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // init
        init()

        // button pressed
        mainButton()

        // default fragment
        val fragmentDashboard = DashboardFragment()
        setFragment(fragmentDashboard)
    }

    private fun init(){

        menu1 = findViewById(R.id.iv_menu1)
        menu2 = findViewById(R.id.iv_menu2)
        menu3 = findViewById(R.id.iv_menu3)

    }

    private fun mainButton() {

        menu1.setOnClickListener {
            val fragmentDashboard = DashboardFragment()
            setFragment(fragmentDashboard)

            changeIcon(menu1, R.drawable.ic_home_active)
            changeIcon(menu2, R.drawable.ic_ticket)
            changeIcon(menu3, R.drawable.ic_profile)
        }

        menu2.setOnClickListener {
            val fragmentTicket = TicketFragment()
            setFragment(fragmentTicket)

            changeIcon(menu1, R.drawable.ic_home)
            changeIcon(menu2, R.drawable.ic_ticket_active)
            changeIcon(menu3, R.drawable.ic_profile)
        }

        menu3.setOnClickListener {
            val fragmentSetting = SettingFragment()
            setFragment(fragmentSetting)

            changeIcon(menu1, R.drawable.ic_home)
            changeIcon(menu2, R.drawable.ic_ticket)
            changeIcon(menu3, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()

    }

    private fun changeIcon(iv: ImageView, resourceInt: Int){
        iv.setImageResource(resourceInt)
    }
}