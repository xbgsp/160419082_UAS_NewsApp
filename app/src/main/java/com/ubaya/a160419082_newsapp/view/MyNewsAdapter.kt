package com.ubaya.a160419082_newsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.MynewsListItemBinding
import com.ubaya.a160419082_newsapp.model.MyNews
import kotlinx.android.synthetic.main.fragment_my_news.view.*
import kotlinx.android.synthetic.main.mynews_list_item.view.*

class MyNewsAdapter(val myNewsList:ArrayList<MyNews>, val adapterOnClick:(Any)-> Unit): RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder>(), ButtonEditClickListener, ButtonDeleteClickListener {
    class MyNewsViewHolder(val view: MynewsListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateMyNewsList(newBookList:List<MyNews>){
        myNewsList.clear()
        myNewsList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<MynewsListItemBinding>(inflater, R.layout.mynews_list_item, parent,false)
        return MyNewsViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyNewsViewHolder, position: Int) {
        holder.view.myNews = myNewsList[position]
        holder.view.listenerEdit = this
        holder.view.listenerDelete = this
    }

    override fun getItemCount(): Int {
        return myNewsList.size
    }

    override fun onButtonEditClick(v: View) {
        val action = MyNewsFragmentDirections.actionEditNewsFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonDeleteClick(v: View, obj: MyNews) {
        adapterOnClick(obj)
        Snackbar.make(v, "Success Delete Data!", Snackbar.LENGTH_LONG).setAction("Success", null).show()
    }
}