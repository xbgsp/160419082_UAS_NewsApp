package com.ubaya.a160419082_newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.FragmentAddNewsBinding
import com.ubaya.a160419082_newsapp.model.MyNews
import com.ubaya.a160419082_newsapp.util.NotificationHelper
import com.ubaya.a160419082_newsapp.viewmodel.DetailMyNewsViewModel
import kotlinx.android.synthetic.main.fragment_add_news.*

class AddNewsFragment : Fragment(), ButtonAddClickListener {
    private lateinit var viewModel: DetailMyNewsViewModel
    private lateinit var dataBinding: FragmentAddNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_news, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_news, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailMyNewsViewModel::class.java)
        dataBinding.myNews = MyNews("", "", "", "")
        dataBinding.listenerAddNews = this
    }

    override fun onButtonAddClick(v: View) {
        viewModel.addMyNews(dataBinding.myNews!!)
        Snackbar.make(v, "Success Insert Data!", Snackbar.LENGTH_LONG).setAction("Success", null).show()

        val action = AddNewsFragmentDirections.actionMyNews()
        Navigation.findNavController(v).navigate(action)

        NotificationHelper(v.context).createNotification("News Added!", "Don't forget to read the news!!")
    }
}