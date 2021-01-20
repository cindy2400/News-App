package com.project.pkb_tugas_besar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.pkb_tugas_besar.R
import com.project.pkb_tugas_besar.adapter.AllNewsHorizontalAdapter
import com.project.pkb_tugas_besar.adapter.ViewPagerAdapter
import com.project.pkb_tugas_besar.data.AllNewsResponse
import com.project.pkb_tugas_besar.network.ApiClient
import com.project.pkb_tugas_besar.network.ApiInterface
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private var tablayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    private var horiLayouts: LinearLayoutManager? = null
    private var rvAllNews: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        addViewPager()

        rvAllNews = findViewById(R.id.rv_all_news)

        et_search.setOnClickListener(this)

        getAllNews()
    }

    private fun showRVAllNews(allNews: AllNewsResponse) {
        horiLayouts = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAllNews?.layoutManager = horiLayouts
        rvAllNews?.adapter = AllNewsHorizontalAdapter(allNews.data)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.et_search -> {
                val search = Intent(this, SearchActivity::class.java)
                startActivity(search)
            }
        }
    }

    private fun addViewPager(){

        tablayout = findViewById<View>(R.id.tabLayout) as TabLayout
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager


        val adapter = ViewPagerAdapter(
            supportFragmentManager
        )

        adapter.addFragment(TabNationalNewsFragment(), "Nasional")
        adapter.addFragment(TabInternationalNewsFragment(), "Internasional")

        viewPager!!.adapter = adapter
        tablayout!!.setupWithViewPager(viewPager)

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
                        progressBar.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(call: Call<AllNewsResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "Failed to load data", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "onFailure: "+t.message)
            }

        })
    }

}