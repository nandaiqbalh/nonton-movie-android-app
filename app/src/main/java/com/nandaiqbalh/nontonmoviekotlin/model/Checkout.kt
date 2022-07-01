package com.nandaiqbalh.nontonmoviekotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Checkout (

    var kursi: String? = "",
    var harga: String? = "",
) : Parcelable