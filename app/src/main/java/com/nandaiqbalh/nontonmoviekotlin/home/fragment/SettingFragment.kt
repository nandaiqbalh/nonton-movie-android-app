package com.nandaiqbalh.nontonmoviekotlin.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nandaiqbalh.nontonmoviekotlin.R
import com.nandaiqbalh.nontonmoviekotlin.utils.SharedPrefs
import com.nandaiqbalh.nontonmoviekotlin.wallet.MyWalletActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
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

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ivProfile: ImageView

    private lateinit var sharedPrefs: SharedPrefs

    private lateinit var btnMyWallet: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_setting, container, false)

        // init
        init(view)

        // atur data
        aturData()

        // btn triggered
        mainButton(view)

        return view
    }

    private fun init(view: View){

        tvName = view.findViewById(R.id.tv_name_setting)
        tvEmail = view.findViewById(R.id.tv_email_setting)
        ivProfile = view.findViewById(R.id.iv_profile_setting)

        btnMyWallet = view.findViewById(R.id.tv_my_wallet_setting)

        sharedPrefs = SharedPrefs(requireActivity().applicationContext)
    }

    private fun aturData(){

        tvName.text = sharedPrefs.getValue("name")
        tvEmail.text = sharedPrefs.getValue("email")

        if(sharedPrefs.getValue("url") != ""){

            Glide.with(this)
                .load(sharedPrefs.getValue("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)
        }

    }

    private fun mainButton(view: View){

        btnMyWallet.setOnClickListener {
            val intent = Intent(activity, MyWalletActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}