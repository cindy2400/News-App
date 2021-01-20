package com.project.pkb_tugas_besar.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NationalNewsModel (

    @SerializedName("judul")
    val newsName: String,
    @SerializedName("link")
    val newsLink: String,
    @SerializedName("poster")
    val newsPoster: String,
    @SerializedName("tipe")
    val newsType: String,
    @SerializedName("waktu")
    val newsTime: String

) : Parcelable