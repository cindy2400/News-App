package com.project.pkb_tugas_besar.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.pkb_tugas_besar.ui.DetailNewsActivity
import com.project.pkb_tugas_besar.R
import com.project.pkb_tugas_besar.data.model.AllNewsModel
import kotlinx.android.synthetic.main.item_news_vertical.view.*

class AllNewsVerticalAdapter(private val allNews: List<AllNewsModel>) : RecyclerView.Adapter<AllNewsVerticalAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news_vertical, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allNews.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(allNews[position])
        holder.cardviewNews.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNewsActivity::class.java)
            intent.putExtra("link", allNews[position].newsLink)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardviewNews = itemView.findViewById<View>(R.id.cv_news_ver) as CardView

        fun bind(news: AllNewsModel) {
            Glide.with(itemView).load(news.newsPoster).into(itemView.iv_item_vertical)
            itemView.tv_news_name_item_ver.text = news.newsName
            itemView.tv_category_item_ver.text = news.newsType
            itemView.tv_time_item_ver.text = news.newsTime
        }
    }
}