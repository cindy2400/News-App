package com.project.pkb_tugas_besar.data

import com.google.gson.annotations.SerializedName
import com.project.pkb_tugas_besar.data.model.InternationalNewsModel

data class InternationalNewsResponse (

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<InternationalNewsModel>

)