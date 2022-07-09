package com.nandaiqbalh.nontonmoviekotlin.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.model.Wallet
import java.text.NumberFormat
import java.util.*


class WalletAdapter(private var data: List<Wallet>,
                    private val listener: (Wallet) -> Unit)
    : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    private lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_transaksi, parent, false)
        return ViewHolder(inflatedView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(data[position], listener, contextAdapter)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var tvTitleTransaksi: TextView = view.findViewById(R.id.tv_title_transaksi)
        private var tvDateTransaksi:TextView = view.findViewById(R.id.tv_date_and_time)
        private var tvMoney: TextView = view.findViewById(R.id.tv_title_money)

        fun bindItem(data: Wallet, listener: (Wallet) -> Unit, context: Context){

            val localID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)

            val moneyTampil = formatRupiah.format(data.money!!.toDouble())
            if (data.status.equals("0")){
                tvMoney.text = "- ${moneyTampil}"
                tvMoney.setTextColor(Color.RED)
            } else {
                tvMoney.text = "+ ${moneyTampil}"
            }

            tvTitleTransaksi.text = data.title
            tvDateTransaksi.text = data.date

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }


}
