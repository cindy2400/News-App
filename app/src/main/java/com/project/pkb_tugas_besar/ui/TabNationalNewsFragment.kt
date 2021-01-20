package com.project.pkb_tugas_besar.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pkb_tugas_besar.R
import com.project.pkb_tugas_besar.adapter.NationalNewsAdapter
import com.project.pkb_tugas_besar.data.NationalNewsResponse
import com.project.pkb_tugas_besar.network.ApiClient
import com.project.pkb_tugas_besar.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_national_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabNationalNewsFragment  : Fragment(){

    private var verticalLayout: LinearLayoutManager? = null
    private var rvNatNews: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_national_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNatNews = view.findViewById<View>(R.id.rv_nat_news) as RecyclerView
        rvNatNews!!.setHasFixedSize(true)

        getNatNews()
    }

    private fun showRVNatNews(natNews: NationalNewsResponse) {
        verticalLayout = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvNatNews?.layoutManager = verticalLayout
        rvNatNews?.adapter = NationalNewsAdapter(natNews.data)
    }


    private fun getNatNews(){
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        apiService.getNationalNews().enqueue(object: Callback<NationalNewsResponse> {
            override fun onResponse(
                call: Call<NationalNewsResponse>,
                response: Response<NationalNewsResponse>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        showRVNatNews(it)
                        progressBar4.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<NationalNewsResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: "+t.message)
            }

        })
    }
}