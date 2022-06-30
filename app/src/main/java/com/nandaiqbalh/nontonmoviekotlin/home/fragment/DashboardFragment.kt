package com.nandaiqbalh.nontonmoviekotlin.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.adapter.NowPlayingAdapter
import com.nandaiqbalh.nontonmoviekotlin.model.Film
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    // variables

    private lateinit var sharedPrefs: SharedPrefs
    private lateinit var mDatabase: DatabaseReference

    private lateinit var tvName: TextView
    private lateinit var tvSaldo:TextView
    private lateinit var ivProfile: ImageView

    private lateinit var rvNowPlaying: RecyclerView
    private lateinit var rvComingSoon: RecyclerView

    private var dataList = ArrayList<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // init
        init(view)

        // atur data
        aturData()

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun init(view: View){

        sharedPrefs = SharedPrefs(requireActivity().applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        tvName = view.findViewById(R.id.tv_name)
        tvSaldo = view.findViewById(R.id.tv_saldo)
        ivProfile = view.findViewById(R.id.iv_profile)

        rvNowPlaying = view.findViewById(R.id.rv_now_playing)
        rvComingSoon = view.findViewById(R.id.rv_coming_soon)

    }

    private fun aturData(){
        tvName.setText(sharedPrefs.getValue("name"))
        Log.d("Nama", "Nama: " + sharedPrefs.getValue("name"))
        Log.d("Saldo", "Saldo: " + sharedPrefs.getValue("saldo"))


        if (sharedPrefs.getValue("saldo") != ""){

            currencyFormatter(sharedPrefs.getValue("saldo")!!.toDouble(), tvSaldo)
        }

        Glide.with(this)
            .load(sharedPrefs.getValue("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfile)

        rvNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvComingSoon.layoutManager = LinearLayoutManager(context) // defaultnya udah vertical, jadi gaperlu atur2

        // getData
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // clear datalist agar tidak ada data yang duplikasi
                dataList.clear()

                for (getDataSnapshot in snapshot.children){

                    // tampung jika ada valuenya
                    var film = getDataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)

                    rvNowPlaying.adapter  = NowPlayingAdapter(dataList){

                        // jika dipencet akan ke halaman detail

                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_LONG).show();
            }

        })
    }

    private fun currencyFormatter(harga: Double, textView: TextView){
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}