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
import com.project.pkb_tugas_besar.data.model.InternationalNewsModel
import kotlinx.android.synthetic.main.item_news_vertical.view.*

class InternationalNewsAdapter (private val interNews: List<InternationalNewsModel>) : RecyclerView.Adapter<InternationalNewsAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_news_vertical, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return interNews.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(interNews[position])
        holder.cardViewNews.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNewsActivity::class.java)
            intent.putExtra("link", interNews[position].newsLink)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardViewNews = itemView.findViewById<View>(R.id.cv_news_ver) as CardView

        fun bind(interNews: InternationalNewsModel) {
            Glide.with(itemView).load(interNews.newsPoster).into(itemView.iv_item_vertical)
            itemView.tv_news_name_item_ver.text = interNews.newsName
            itemView.tv_category_item_ver.text = interNews.newsType
            itemView.tv_time_item_ver.text = interNews.newsTime
        }
    }

}