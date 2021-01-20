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
import kotlinx.android.synthetic.main.item_news_horizontal.view.*

class AllNewsHorizontalAdapter(private val allNews: List<AllNewsModel>) : RecyclerView.Adapter<AllNewsHorizontalAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news_horizontal, parent, false)
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

        val cardviewNews = itemView.findViewById<View>(R.id.cv_news_hor) as CardView

        fun bind(news: AllNewsModel) {
            Glide.with(itemView).load(news.newsPoster).into(itemView.iv_news_hor)
            itemView.tv_news_name_hor.text = news.newsName
            itemView.tv_category_hor.text = news.newsType
            itemView.tv_time_hor.text = news.newsTime
        }
    }
}