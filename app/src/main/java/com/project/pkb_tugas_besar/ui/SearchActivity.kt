package com.project.pkb_tugas_besar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.pkb_tugas_besar.R
import com.project.pkb_tugas_besar.adapter.AllNewsVerticalAdapter
import com.project.pkb_tugas_besar.adapter.SearchAdapter
import com.project.pkb_tugas_besar.data.AllNewsResponse
import com.project.pkb_tugas_besar.data.SearchNewsResponse
import com.project.pkb_tugas_besar.network.ApiClient
import com.project.pkb_tugas_besar.network.ApiInterface
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView

    private var vertiLayouts: LinearLayoutManager? = null
    private var rvSearchNews: RecyclerView? = null

    private var verticalLayouts: LinearLayoutManager? = null
    private var rvAllNews: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchView = findViewById(R.id.search_view)

        searchView.isIconifiedByDefault = false
        searchView.isFocusable = true
        searchView.isIconified = false
        searchView.requestFocusFromTouch()

        rvSearchNews = findViewById(R.id.rv_search_news)
        rvAllNews = findViewById(R.id.rv_all_newss)

        getAllNews()

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    getSearchNews(query)
                    progressBar5.visibility = View.VISIBLE
                    rv_search_news.visibility = View.VISIBLE
                    rv_all_newss.visibility = View.GONE
                    tv_no_news.visibility = View.GONE
                    return false
                }
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            }
        )
    }

    private fun showRVSearch(searchNews: SearchNewsResponse) {
        vertiLayouts = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSearchNews?.layoutManager = vertiLayouts
        rvSearchNews?.adapter = SearchAdapter(searchNews.data)
        if(rvSearchNews?.adapter!!.itemCount == 0){
            tv_no_news.visibility = View.VISIBLE
        }else{
            tv_no_news.visibility = View.GONE
        }
    }

    private fun showRVAllNews(allNews: AllNewsResponse) {
        verticalLayouts = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvAllNews?.layoutManager = verticalLayouts
        rvAllNews?.adapter = AllNewsVerticalAdapter(allNews.data)
    }

    private fun getSearchNews(query : String){
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        apiService.getSearchNews(query).enqueue(object: Callback<SearchNewsResponse> {
            override fun onResponse(
                call: Call<SearchNewsResponse>,
                response: Response<SearchNewsResponse>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        showRVSearch(it)
                        rv_all_newss.visibility = View.GONE
                        progressBar5.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<SearchNewsResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: "+t.message)
            }
        })
    }

    private fun getAllNews(){
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        apiService.getAllNews().enqueue(object: Callback<AllNewsResponse> {
            override fun onResponse(
                call: Call<AllNewsResponse>,
                response: Response<AllNewsResponse>
            ) {
                response.body()?.let {
                    if (it.status == 200) {
                        showRVAllNews(it)
                    }
                }
            }

            override fun onFailure(call: Call<AllNewsResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.d("TAG", "onFailure: "+t.message)
            }
        })
    }

}