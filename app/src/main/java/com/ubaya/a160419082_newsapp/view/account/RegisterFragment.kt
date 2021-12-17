package com.ubaya.a160419082_newsapp.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.databinding.FragmentRegisterBinding
import com.ubaya.a160419082_newsapp.model.User
import com.ubaya.a160419082_newsapp.util.loadImage
import com.ubaya.a160419082_newsapp.view.ButtonRegisterClickListener
import com.ubaya.a160419082_newsapp.view.ButtonSubmitRegisterClickListener
import com.ubaya.a160419082_newsapp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment(), ButtonSubmitRegisterClickListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater,R.layout.fragment_register,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.user = User("","","","-", 0)
        dataBinding.listenerRegister = this
    }

    override fun onButtonSubmitRegisterClick(v: View) {
        viewModel.addUser(dataBinding.user!!)
        Snackbar.make(v, "Success Register!", Snackbar.LENGTH_LONG).setAction("Success", null).show()

        val action = RegisterFragmentDirections.actionLogin2()
        Navigation.findNavController(v).navigate(action)
    }
}