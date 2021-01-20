package com.project.pkb_tugas_besar.data

import com.google.gson.annotations.SerializedName
import com.project.pkb_tugas_besar.data.model.AllNewsModel
import com.project.pkb_tugas_besar.data.model.NationalNewsModel

data class NationalNewsResponse (

    @SerializedName("status")
    val status: Int,
    @SerializedName("data")
    val data: List<NationalNewsModel>

)