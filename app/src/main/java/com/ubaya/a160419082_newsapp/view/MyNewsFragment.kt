package com.ubaya.a160419082_newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.model.MyNews
import com.ubaya.a160419082_newsapp.viewmodel.MyNewsViewModel
import kotlinx.android.synthetic.main.fragment_my_news.*

class MyNewsFragment : Fragment() {
    private lateinit var viewModel: MyNewsViewModel
    private val myNewsAdapter = MyNewsAdapter(arrayListOf(), { item -> doClick(item)})

    fun doClick(item:Any)
    {
        viewModel.clearMyNews(item as MyNews)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAdd.setOnClickListener {
            val action = MyNewsFragmentDirections.actionAddNews()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(MyNewsViewModel::class.java)
        viewModel.refresh()

        recViewMyNews.layoutManager= LinearLayoutManager(context)
        recViewMyNews.adapter = myNewsAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.myNewsLD.observe(viewLifecycleOwner, Observer {
            myNewsAdapter.updateMyNewsList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer {
            txtErrorMyNews.visibility = if (it) View.VISIBLE else View.GONE

        })

        viewModel.loadingDoneLD.observe(viewLifecycleOwner, Observer {
            if (it){
                progressLoadMyNews.visibility = View.GONE
                recViewMyNews.visibility = View.VISIBLE
            }
            else{
                progressLoadMyNews.visibility = View.VISIBLE
                recViewMyNews.visibility = View.GONE
            }
        })
    }
}