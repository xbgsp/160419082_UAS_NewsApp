package com.ubaya.a160419082_newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.FragmentNewsDetailBinding
import com.ubaya.a160419082_newsapp.util.loadImage
import com.ubaya.a160419082_newsapp.viewmodel.DetailNewsViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*
import kotlinx.android.synthetic.main.fragment_news_detail.view.*

class NewsDetailFragment : Fragment() {
    private lateinit var viewModel:DetailNewsViewModel
    private lateinit var dataBinding:FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentNewsDetailBinding>(inflater,R.layout.fragment_news_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        val newsId = NewsDetailFragmentArgs.fromBundle(requireArguments()).idNews
        viewModel.fetch(newsId)

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            dataBinding.news = it
        })
    }
}