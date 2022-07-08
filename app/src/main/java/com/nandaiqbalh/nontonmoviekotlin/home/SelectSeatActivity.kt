package com.nandaiqbalh.nontonmoviekotlin.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.checkout.CheckoutActivity
import com.nandaiqbalh.nontonmoviekotlin.model.Checkout
import com.nandaiqbalh.nontonmoviekotlin.model.Film

class SelectSeatActivity : AppCompatActivity() {

    private lateinit var tvTitleFilm: TextView

    private lateinit var btnBookTicket: Button
    private lateinit var btnBack: ImageView

    private var total: Int = 0
    private var dataList = ArrayList<Checkout>()


    private lateinit var kursiA1: ImageView
    private lateinit var kursiA2: ImageView
    private lateinit var kursiA3: ImageView
    private lateinit var kursiA4: ImageView
    private lateinit var kursiA5: ImageView
    private lateinit var kursiA6: ImageView

    private lateinit var kursiB1: ImageView
    private lateinit var kursiB2: ImageView
    private lateinit var kursiB3: ImageView
    private lateinit var kursiB4: ImageView
    private lateinit var kursiB5: ImageView
    private lateinit var kursiB6: ImageView

    private lateinit var kursiC1: ImageView
    private lateinit var kursiC2: ImageView
    private lateinit var kursiC3: ImageView
    private lateinit var kursiC4: ImageView
    private lateinit var kursiC5: ImageView
    private lateinit var kursiC6: ImageView

    private lateinit var kursiD1: ImageView
    private lateinit var kursiD2: ImageView
    private lateinit var kursiD3: ImageView
    private lateinit var kursiD4: ImageView
    private lateinit var kursiD5: ImageView
    private lateinit var kursiD6: ImageView

    private lateinit var kursiE1: ImageView
    private lateinit var kursiE2: ImageView
    private lateinit var kursiE3: ImageView
    private lateinit var kursiE4: ImageView
    private lateinit var kursiE5: ImageView
    private lateinit var kursiE6: ImageView

    private var statusA1: Boolean = false
    private var statusA2: Boolean = false
    private var statusA3: Boolean = false
    private var statusA4: Boolean = false
    private var statusA5: Boolean = false
    private var statusA6: Boolean = false

    private var statusB1: Boolean = false
    private var statusB2: Boolean = false
    private var statusB3: Boolean = false
    private var statusB4: Boolean = false
    private var statusB5: Boolean = false
    private var statusB6: Boolean = false

    private var statusC1: Boolean = false
    private var statusC2: Boolean = false
    private var statusC3: Boolean = false
    private var statusC4: Boolean = false
    private var statusC5: Boolean = false
    private var statusC6: Boolean = false

    private var statusD1: Boolean = false
    private var statusD2: Boolean = false
    private var statusD3: Boolean = false
    private var statusD4: Boolean = false
    private var statusD5: Boolean = false
    private var statusD6: Boolean = false

    private var statusE1: Boolean = false
    private var statusE2: Boolean = false
    private var statusE3: Boolean = false
    private var statusE4: Boolean = false
    private var statusE5: Boolean = false
    private var statusE6: Boolean = false

    private var isAddedA1: Boolean = false
    private var isAddedA2: Boolean = false
    private var isAddedA3: Boolean = false
    private var isAddedA4: Boolean = false
    private var isAddedA5: Boolean = false
    private var isAddedA6: Boolean = false

    private var isAddedB1: Boolean = false
    private var isAddedB2: Boolean = false
    private var isAddedB3: Boolean = false
    private var isAddedB4: Boolean = false
    private var isAddedB5: Boolean = false
    private var isAddedB6: Boolean = false

    private var isAddedC1: Boolean = false
    private var isAddedC2: Boolean = false
    private var isAddedC3: Boolean = false
    private var isAddedC4: Boolean = false
    private var isAddedC5: Boolean = false
    private var isAddedC6: Boolean = false

    private var isAddedD1: Boolean = false
    private var isAddedD2: Boolean = false
    private var isAddedD3: Boolean = false
    private var isAddedD4: Boolean = false
    private var isAddedD5: Boolean = false
    private var isAddedD6: Boolean = false

    private var isAddedE1: Boolean = false
    private var isAddedE2: Boolean = false
    private var isAddedE3: Boolean = false
    private var isAddedE4: Boolean = false
    private var isAddedE5: Boolean = false
    private var isAddedE6: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)

        val data = intent.getParcelableExtra<Film>("data")

        // init
        init()
        initSeats()

        // set data
        setData(data)

        // main button
        mainButton(data)


    }

    private fun init() {
        tvTitleFilm = findViewById(R.id.tv_title_film)

        btnBookTicket = findViewById(R.id.btn_book_ticket)
        btnBack = findViewById(R.id.btn_back)

    }

    private fun initSeats() {
        kursiA1 = findViewById(R.id.a1)
        kursiA2 = findViewById(R.id.a2)
        kursiA3 = findViewById(R.id.a3)
        kursiA4 = findViewById(R.id.a4)
        kursiA5 = findViewById(R.id.a5)
        kursiA6 = findViewById(R.id.a6)

        kursiB1 = findViewById(R.id.b1)
        kursiB2 = findViewById(R.id.b2)
        kursiB3 = findViewById(R.id.b3)
        kursiB4 = findViewById(R.id.b4)
        kursiB5 = findViewById(R.id.b5)
        kursiB6 = findViewById(R.id.b6)

        kursiC1 = findViewById(R.id.c1)
        kursiC2 = findViewById(R.id.c2)
        kursiC3 = findViewById(R.id.c3)
        kursiC4 = findViewById(R.id.c4)
        kursiC5 = findViewById(R.id.c5)
        kursiC6 = findViewById(R.id.c6)

        kursiD1 = findViewById(R.id.d1)
        kursiD2 = findViewById(R.id.d2)
        kursiD3 = findViewById(R.id.d3)
        kursiD4 = findViewById(R.id.d4)
        kursiD5 = findViewById(R.id.d5)
        kursiD6 = findViewById(R.id.d6)

        kursiE1 = findViewById(R.id.e1)
        kursiE2 = findViewById(R.id.e2)
        kursiE3 = findViewById(R.id.e3)
        kursiE4 = findViewById(R.id.e4)
        kursiE5 = findViewById(R.id.e5)
        kursiE6 = findViewById(R.id.e6)
    }

    private fun setData(data: Film?) {

        tvTitleFilm.setText(data?.judul)
    }

    private fun mainButton(data: Film?) {

        // back
        btnBack.setOnClickListener {
            var intent = Intent(this@SelectSeatActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        checkoutKursiA()
        checkoutKursiB()
        checkoutKursiC()
        checkoutKursiD()
        checkoutKursiE()

        btnBookTicket.setOnClickListener {

            var intent = Intent(this@SelectSeatActivity, CheckoutActivity::class.java).putExtra("data", dataList).putExtra("datas", data)
            startActivity(intent)
        }

    }

    private fun beliTiket(total: Int) {

        if (total == 0 ){
            btnBookTicket.setText("Checkout")
            btnBookTicket.visibility = View.INVISIBLE
        } else {
            btnBookTicket.setText("Checkout (${total})")
            btnBookTicket.visibility = View.VISIBLE
        }
    }


    private fun checkoutKursiA() {
        kursiA1.setOnClickListener {
            if (statusA1){
                kursiA1.setImageResource(R.drawable.shape_line_empty_seats)
                statusA1 = false
                total -= 1
                beliTiket(total)

            } else {
                kursiA1.setImageResource(R.drawable.shape_line_selected_seats)
                statusA1 = true
                total += 1
                beliTiket(total)

                if (isAddedA1 == false){
                    // set data
                    val data = Checkout("A1", "30000")
                    dataList.add(data)

                    isAddedA1 = true
                }


            }
        }

        kursiA2.setOnClickListener {
            if (statusA2){
                kursiA2.setImageResource(R.drawable.shape_line_empty_seats)
                statusA2 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiA2.setImageResource(R.drawable.shape_line_selected_seats)
                statusA2 = true
                total += 1
                beliTiket(total)

                if (isAddedA2 == false){
                    // set data
                    val data = Checkout("A2", "30000")
                    dataList.add(data)

                    isAddedA2 = true
                }
            }
        }

        kursiA3.setOnClickListener {
            if (statusA3){
                kursiA3.setImageResource(R.drawable.shape_line_empty_seats)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiA3.setImageResource(R.drawable.shape_line_selected_seats)
                statusA3 = true
                total += 1
                beliTiket(total)

                if (isAddedA3 == false){
                    // set data
                    val data = Checkout("A3", "30000")
                    dataList.add(data)

                    isAddedA3 = true
                }
            }
        }

        kursiA4.setOnClickListener {
            if (statusA4){
                kursiA4.setImageResource(R.drawable.shape_line_empty_seats)
                statusA4 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiA4.setImageResource(R.drawable.shape_line_selected_seats)
                statusA4 = true
                total += 1
                beliTiket(total)

                if (isAddedA4 == false){
                    // set data
                    val data = Checkout("A4", "30000")
                    dataList.add(data)

                    isAddedA4 = true
                }
            }
        }

        kursiA5.setOnClickListener {
            if (statusA5){
                kursiA5.setImageResource(R.drawable.shape_line_empty_seats)
                statusA5 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiA5.setImageResource(R.drawable.shape_line_selected_seats)
                statusA5 = true
                total += 1
                beliTiket(total)

                if (isAddedA5 == false){
                    // set data
                    val data = Checkout("A5", "30000")
                    dataList.add(data)

                    isAddedA5 = true
                }
            }
        }

        kursiA6.setOnClickListener {
            if (statusA6){
                kursiA6.setImageResource(R.drawable.shape_line_empty_seats)
                statusA6 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiA6.setImageResource(R.drawable.shape_line_selected_seats)
                statusA6 = true
                total += 1
                beliTiket(total)

                if (isAddedA6 == false){
                    // set data
                    val data = Checkout("A6", "30000")
                    dataList.add(data)

                    isAddedA6 = true
                }
            }
        }
    }

    private fun checkoutKursiB() {
        kursiB1.setOnClickListener {
            if (statusB1){
                kursiB1.setImageResource(R.drawable.shape_line_empty_seats)
                statusB1 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiB1.setImageResource(R.drawable.shape_line_selected_seats)
                statusB1 = true
                total += 1
                beliTiket(total)

                if (isAddedB1 == false){
                    // set data
                    val data = Checkout("B1", "35000")
                    dataList.add(data)

                    isAddedB1 = true
                }
            }
        }

        kursiB2.setOnClickListener {
            if (statusB2){
                kursiB2.setImageResource(R.drawable.shape_line_empty_seats)
                statusB2 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiB2.setImageResource(R.drawable.shape_line_selected_seats)
                statusB2 = true
                total += 1
                beliTiket(total)

                if (isAddedB2 == false){
                    // set data
                    val data = Checkout("B2", "35000")
                    dataList.add(data)

                    isAddedB2 = true
                }
            }
        }

        kursiB3.setOnClickListener {
            if (statusB3){
                kursiB3.setImageResource(R.drawable.shape_line_empty_seats)
                statusB3 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiB3.setImageResource(R.drawable.shape_line_selected_seats)
                statusB3 = true
                total += 1
                beliTiket(total)

                if (isAddedB3 == false){
                    // set data
                    val data = Checkout("B3", "35000")
                    dataList.add(data)

                    isAddedB3 = true
                }
            }
        }

        kursiB4.setOnClickListener {
            if (statusB4){
                kursiB4.setImageResource(R.drawable.shape_line_empty_seats)
                statusB4 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiB4.setImageResource(R.drawable.shape_line_selected_seats)
                statusB4 = true
                total += 1
                beliTiket(total)

                if (isAddedB4 == false){
                    // set data
                    val data = Checkout("B4", "35000")
                    dataList.add(data)

                    isAddedB4 = true
                }
            }
        }

        kursiB5.setOnClickListener {
            if (statusB5){
                kursiB5.setImageResource(R.drawable.shape_line_empty_seats)
                statusB5 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiB5.setImageResource(R.drawable.shape_line_selected_seats)
                statusB5 = true
                total += 1
                beliTiket(total)

                if (isAddedB5 == false){
                    // set data
                    val data = Checkout("B5", "35000")
                    dataList.add(data)

                    isAddedB5 = true
                }
            }
        }

        kursiB6.setOnClickListener {
            if (statusB6){
                kursiB6.setImageResource(R.drawable.shape_line_empty_seats)
                statusB6 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiB6.setImageResource(R.drawable.shape_line_selected_seats)
                statusB6 = true
                total += 1
                beliTiket(total)

                if (isAddedB6 == false){
                    // set data
                    val data = Checkout("B6", "35000")
                    dataList.add(data)

                    isAddedB6 = true
                }
            }
        }
    }

    private fun checkoutKursiC() {
        kursiC1.setOnClickListener {
            if (statusC1){
                kursiC1.setImageResource(R.drawable.shape_line_empty_seats)
                statusC1 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiC1.setImageResource(R.drawable.shape_line_selected_seats)
                statusC1 = true
                total += 1
                beliTiket(total)


                if (isAddedC1 == false){
                    // set data
                    val data = Checkout("C1", "40000")
                    dataList.add(data)

                    isAddedC1 = true
                }
            }
        }

        kursiC2.setOnClickListener {
            if (statusC2){
                kursiC2.setImageResource(R.drawable.shape_line_empty_seats)
                statusC2 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiC2.setImageResource(R.drawable.shape_line_selected_seats)
                statusC2 = true
                total += 1
                beliTiket(total)


                if (isAddedC2 == false){
                    // set data
                    val data = Checkout("C2", "40000")
                    dataList.add(data)

                    isAddedC2 = true
                }
            }
        }

        kursiC3.setOnClickListener {
            if (statusC3){
                kursiC3.setImageResource(R.drawable.shape_line_empty_seats)
                statusC3 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiC3.setImageResource(R.drawable.shape_line_selected_seats)
                statusC3 = true
                total += 1
                beliTiket(total)


                if (isAddedC3 == false){
                    // set data
                    val data = Checkout("C3", "40000")
                    dataList.add(data)

                    isAddedC3 = true
                }
            }
        }

        kursiC4.setOnClickListener {
            if (statusC4){
                kursiC4.setImageResource(R.drawable.shape_line_empty_seats)
                statusC4 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiC4.setImageResource(R.drawable.shape_line_selected_seats)
                statusC4 = true
                total += 1
                beliTiket(total)


                if (isAddedC4 == false){
                    // set data
                    val data = Checkout("C4", "40000")
                    dataList.add(data)

                    isAddedC4 = true
                }
            }
        }

        kursiC5.setOnClickListener {
            if (statusC5){
                kursiC5.setImageResource(R.drawable.shape_line_empty_seats)
                statusC5 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiC5.setImageResource(R.drawable.shape_line_selected_seats)
                statusC5 = true
                total += 1
                beliTiket(total)


                if (isAddedC5 == false){
                    // set data
                    val data = Checkout("C5", "40000")
                    dataList.add(data)

                    isAddedC5 = true
                }
            }
        }

        kursiC6.setOnClickListener {
            if (statusC6){
                kursiC6.setImageResource(R.drawable.shape_line_empty_seats)
                statusC6 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiC6.setImageResource(R.drawable.shape_line_selected_seats)
                statusC6 = true
                total += 1
                beliTiket(total)


                if (isAddedC6 == false){
                    // set data
                    val data = Checkout("C6", "40000")
                    dataList.add(data)

                    isAddedC6 = true
                }
            }
        }
    }

    private fun checkoutKursiD() {
        kursiD1.setOnClickListener {
            if (statusD1){
                kursiD1.setImageResource(R.drawable.shape_line_empty_seats)
                statusD1 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiD1.setImageResource(R.drawable.shape_line_selected_seats)
                statusD1 = true
                total += 1
                beliTiket(total)


                if (isAddedD1 == false){
                    // set data
                    val data = Checkout("D1", "45000")
                    dataList.add(data)

                    isAddedD1 = true
                }
            }
        }

        kursiD2.setOnClickListener {
            if (statusD2){
                kursiD2.setImageResource(R.drawable.shape_line_empty_seats)
                statusD2 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiD2.setImageResource(R.drawable.shape_line_selected_seats)
                statusD2 = true
                total += 1
                beliTiket(total)


                if (isAddedD2 == false){
                    // set data
                    val data = Checkout("D2", "45000")
                    dataList.add(data)

                    isAddedD2 = true
                }
            }
        }

        kursiD3.setOnClickListener {
            if (statusD3){
                kursiD3.setImageResource(R.drawable.shape_line_empty_seats)
                statusD3 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiD3.setImageResource(R.drawable.shape_line_selected_seats)
                statusD3 = true
                total += 1
                beliTiket(total)


                if (isAddedD3 == false){
                    // set data
                    val data = Checkout("D3", "45000")
                    dataList.add(data)

                    isAddedD3 = true
                }
            }
        }

        kursiD4.setOnClickListener {
            if (statusD4){
                kursiD4.setImageResource(R.drawable.shape_line_empty_seats)
                statusD4 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiD4.setImageResource(R.drawable.shape_line_selected_seats)
                statusD4 = true
                total += 1
                beliTiket(total)


                if (isAddedD4 == false){
                    // set data
                    val data = Checkout("D4", "45000")
                    dataList.add(data)

                    isAddedD4 = true
                }
            }
        }

        kursiD5.setOnClickListener {
            if (statusD5){
                kursiD5.setImageResource(R.drawable.shape_line_empty_seats)
                statusD5 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiD5.setImageResource(R.drawable.shape_line_selected_seats)
                statusD5 = true
                total += 1
                beliTiket(total)


                if (isAddedD5 == false){
                    // set data
                    val data = Checkout("D5", "45000")
                    dataList.add(data)

                    isAddedD5 = true
                }
            }
        }

        kursiD6.setOnClickListener {
            if (statusD6){
                kursiD6.setImageResource(R.drawable.shape_line_empty_seats)
                statusD6 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiD6.setImageResource(R.drawable.shape_line_selected_seats)
                statusD6 = true
                total += 1
                beliTiket(total)


                if (isAddedD6 == false){
                    // set data
                    val data = Checkout("D6", "45000")
                    dataList.add(data)

                    isAddedD6 = true
                }
            }
        }
    }

    private fun checkoutKursiE() {
        kursiE1.setOnClickListener {
            if (statusE1){
                kursiE1.setImageResource(R.drawable.shape_line_empty_seats)
                statusE1 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiE1.setImageResource(R.drawable.shape_line_selected_seats)
                statusE1 = true
                total += 1
                beliTiket(total)


                if (isAddedE1 == false){
                    // set data
                    val data = Checkout("E1", "50000")
                    dataList.add(data)

                    isAddedE1 = true
                }
            }
        }

        kursiE2.setOnClickListener {
            if (statusE2){
                kursiE2.setImageResource(R.drawable.shape_line_empty_seats)
                statusE2 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiE2.setImageResource(R.drawable.shape_line_selected_seats)
                statusE2 = true
                total += 1
                beliTiket(total)


                if (isAddedE2 == false){
                    // set data
                    val data = Checkout("E2", "50000")
                    dataList.add(data)

                    isAddedE2 = true
                }
            }
        }

        kursiE3.setOnClickListener {
            if (statusE3){
                kursiE3.setImageResource(R.drawable.shape_line_empty_seats)
                statusE3 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiE3.setImageResource(R.drawable.shape_line_selected_seats)
                statusE3 = true
                total += 1
                beliTiket(total)


                if (isAddedE3 == false){
                    // set data
                    val data = Checkout("E3", "50000")
                    dataList.add(data)

                    isAddedE3 = true
                }
            }
        }

        kursiE4.setOnClickListener {
            if (statusE4){
                kursiE4.setImageResource(R.drawable.shape_line_empty_seats)
                statusE4 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiE4.setImageResource(R.drawable.shape_line_selected_seats)
                statusE4 = true
                total += 1
                beliTiket(total)


                if (isAddedE4 == false){
                    // set data
                    val data = Checkout("E4", "50000")
                    dataList.add(data)

                    isAddedE4 = true
                }
            }
        }

        kursiE5.setOnClickListener {
            if (statusE5){
                kursiE5.setImageResource(R.drawable.shape_line_empty_seats)
                statusE5 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiE5.setImageResource(R.drawable.shape_line_selected_seats)
                statusE5 = true
                total += 1
                beliTiket(total)


                if (isAddedE5 == false){
                    // set data
                    val data = Checkout("E5", "50000")
                    dataList.add(data)

                    isAddedE5 = true
                }
            }
        }

        kursiE6.setOnClickListener {
            if (statusE6){
                kursiE6.setImageResource(R.drawable.shape_line_empty_seats)
                statusE6 = false
                total -= 1
                beliTiket(total)
            } else {
                kursiE6.setImageResource(R.drawable.shape_line_selected_seats)
                statusE6 = true
                total += 1
                beliTiket(total)


                if (isAddedE6 == false){
                    // set data
                    val data = Checkout("E6", "50000")
                    dataList.add(data)

                    isAddedE6 = true
                }
            }
        }
    }
}