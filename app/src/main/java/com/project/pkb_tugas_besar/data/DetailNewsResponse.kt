package com.project.pkb_tugas_besar.data

import com.google.gson.annotations.SerializedName
import com.project.pkb_tugas_besar.data.model.DetailNewsModel

data class DetailNewsResponse (

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<DetailNewsModel>

)