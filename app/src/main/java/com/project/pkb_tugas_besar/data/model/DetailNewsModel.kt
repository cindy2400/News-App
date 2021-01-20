package com.project.pkb_tugas_besar.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailNewsModel (

    @SerializedName("body")
    val newsBody: String,
    @SerializedName("judul")
    val newsName: String,
    @SerializedName("poster")
    val newsPoster: String

) : Parcelable