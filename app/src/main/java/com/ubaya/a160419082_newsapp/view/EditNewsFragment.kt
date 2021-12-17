package com.ubaya.a160419082_newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.FragmentEditNewsBinding
import com.ubaya.a160419082_newsapp.model.MyNews
import com.ubaya.a160419082_newsapp.viewmodel.DetailMyNewsViewModel

class EditNewsFragment : Fragment(), ButtonEditMyNewsClickListener {
    private lateinit var viewModel: DetailMyNewsViewModel
    private lateinit var dataBinding: FragmentEditNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_edit_news, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_news, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailMyNewsViewModel::class.java)
        val idNews = EditNewsFragmentArgs.fromBundle(requireArguments()).idNews
        viewModel.fetch(idNews)
        observeViewModel()

        dataBinding.listenerEditMyNews = this
    }

    fun observeViewModel(){
        viewModel.myNewsLD.observe(viewLifecycleOwner, Observer {
            dataBinding.myNews = it
        })
    }

    override fun onButtonEditMyNewsClick(v: View, obj: MyNews) {
        viewModel.update(obj.author, obj.title, obj.description, obj.url, obj.idMyNews)
        Snackbar.make(v, "Success Edit Data!", Snackbar.LENGTH_LONG).setAction("Success", null).show()

        val action = EditNewsFragmentDirections.actionMyNews2()
        Navigation.findNavController(v).navigate(action)
    }
}