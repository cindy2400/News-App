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
import com.project.pkb_tugas_besar.adapter.InternationalNewsAdapter
import com.project.pkb_tugas_besar.data.InternationalNewsResponse
import com.project.pkb_tugas_besar.network.ApiClient
import com.project.pkb_tugas_besar.network.ApiInterface
import kotlinx.android.synthetic.main.fragment_international_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabInternationalNewsFragment  : Fragment(), View.OnClickListener {

    private var verticalLayout: LinearLayoutManager? = null
    private var rvInterNews: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_international_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvInterNews = view.findViewById<View>(R.id.rv_inter_news) as RecyclerView
        rvInterNews!!.setHasFixedSize(true)

        getInterNews()
    }

    private fun showRVInterNews(interNews: InternationalNewsResponse) {
        verticalLayout = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvInterNews?.layoutManager = verticalLayout
        rvInterNews?.adapter =
            InternationalNewsAdapter(interNews.data)
    }

    override fun onClick(v: View?) {

    }

    private fun getInterNews(){
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        apiService.getInternationalNews().enqueue(object: Callback<InternationalNewsResponse> {
            override fun onResponse(
                call: Call<InternationalNewsResponse>,
                response: Response<InternationalNewsResponse>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        showRVInterNews(it)
                        progressBar2.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<InternationalNewsResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: "+t.message)
            }

        })
    }

}