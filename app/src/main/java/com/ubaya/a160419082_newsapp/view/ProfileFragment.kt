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
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.FragmentProfileBinding
import com.ubaya.a160419082_newsapp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ButtonDetailProfileClickListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,R.layout.fragment_profile,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.listenerDetail = this

        viewModel.refresh()

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
            if(viewModel.profile.value?.status == 1){
                btnDetailProfile.visibility = View.VISIBLE
            }
            else{
                btnDetailProfile.visibility = View.GONE
            }
        })
    }

    override fun onButtonDetailProfileClick(v: View) {
        val action = ProfileFragmentDirections.actionProfileDetail(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}