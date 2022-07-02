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


class TicketDetailAdapter(private var data: List<Checkout>,
                          private val listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<TicketDetailAdapter.ViewHolder>() {

    private lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_ticket_detail_white, parent, false)
        return ViewHolder(inflatedView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(data[position], listener, contextAdapter)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var tvSeatsTicketDetail: TextView = view.findViewById(R.id.tv_seats_ticket_detail)


        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context: Context){

            tvSeatsTicketDetail.text = "Seat No. ${data.kursi}"

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }


}
