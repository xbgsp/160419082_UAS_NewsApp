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
import com.google.android.material.snackbar.Snackbar
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.FragmentProfileDetailBinding
import com.ubaya.a160419082_newsapp.model.User
import com.ubaya.a160419082_newsapp.util.loadImage
import com.ubaya.a160419082_newsapp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile_detail.*
import kotlinx.android.synthetic.main.fragment_profile_detail.view.*

class ProfileDetailFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentProfileDetailBinding>(inflater,R.layout.fragment_profile_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val userId = ProfileDetailFragmentArgs.fromBundle(requireArguments()).idUser
        viewModel.selectUser(userId)
        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.user.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }
}