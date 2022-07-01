package com.nandaiqbalh.nontonmoviekotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.model.Checkout
import java.text.NumberFormat
import java.util.*


class CheckoutAdapter(private var data: List<Checkout>,
                      private val listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    private lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return ViewHolder(inflatedView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(data[position], listener, contextAdapter)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var tvSeatsCheckout: TextView = view.findViewById(R.id.tv_seats_checkout)
        private var tvHargaCheckout:TextView = view.findViewById(R.id.tv_harga_checkout)


        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context: Context){

            val localID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvHargaCheckout.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total")){
                // ketika data mulai dengan "Total" maka yang ditampilkan hanya total, dan hapus gambar kursinya
                tvSeatsCheckout.text = data.kursi
                tvSeatsCheckout.setCompoundDrawables(null, null, null, null)
            } else {
                tvSeatsCheckout.text = "Seat No. ${data.kursi}"
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }


}
