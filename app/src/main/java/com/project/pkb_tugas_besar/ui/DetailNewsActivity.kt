package com.project.pkb_tugas_besar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.project.pkb_tugas_besar.R
import com.project.pkb_tugas_besar.data.DetailNewsResponse
import com.project.pkb_tugas_besar.network.ApiClient
import com.project.pkb_tugas_besar.network.ApiInterface
import kotlinx.android.synthetic.main.activity_detail_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailNewsActivity : AppCompatActivity() {

    lateinit var link : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        link = intent.getStringExtra("link")

        getDetailNews(link)

    }

    private fun getDetailNews(link : String){
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        apiService.getDetailNews(link).enqueue(object: Callback<DetailNewsResponse> {
            override fun onResponse(
                call: Call<DetailNewsResponse>,
                response: Response<DetailNewsResponse>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        progressBar3.visibility = View.GONE
                        for(data in it.data){

                            Glide.with(this@DetailNewsActivity).load(data.newsPoster).into(iv_detail_news)

                            if(data.newsBody != null) {
                                val body = data.newsBody
                                var body2 = body.replace("\n", "")
                                body2 = body2.trimIndent()
                                tv_news_body.text = body2
                            }else {
                                tv_news_body.text = data.newsBody
                            }


                            if(data.newsName != null) {
                                val name = data.newsName
                                var name2 = name.replace("\n", "")
                                name2 = name2.trimIndent()
                                tv_news_name.text = name2
                            }else {
                                tv_news_name.text = data.newsName
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DetailNewsResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: "+t.message)
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}