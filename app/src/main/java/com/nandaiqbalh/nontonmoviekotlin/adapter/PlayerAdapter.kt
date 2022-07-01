package com.nandaiqbalh.nontonmoviekotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.model.Plays


class PlayerAdapter(private var data: List<Plays>,
                    private val listener: (Plays) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_who_plays, parent, false)
        return ViewHolder(inflatedView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItem(data[position], listener, contextAdapter)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private var ivPlayer: ImageView = view.findViewById(R.id.iv_photo)
        private var tvPlayer: TextView = view.findViewById(R.id.tv_name)



        fun bindItem(data: Plays, listener: (Plays) -> Unit, context: Context){

            tvPlayer.text = data.nama

            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPlayer)

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }


}
