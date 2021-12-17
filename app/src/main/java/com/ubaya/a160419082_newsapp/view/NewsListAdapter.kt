package com.ubaya.a160419082_newsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.NewsListItemBinding
import com.ubaya.a160419082_newsapp.model.News
import com.ubaya.a160419082_newsapp.util.loadImage
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(val newsList:ArrayList<News>): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>(), ButtonDetailClickListener {
    class NewsViewHolder(val view: NewsListItemBinding):RecyclerView.ViewHolder(view.root)

    fun updateNewsList(newNewsList:List<News>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<NewsListItemBinding>(inflater,R.layout.news_list_item,parent,false)
        return NewsViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.news = newsList[position]
        holder.view.listenerDetail = this

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = NewsListFragmentDirections.actionNewsDetail(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}