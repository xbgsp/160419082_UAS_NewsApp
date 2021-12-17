package com.ubaya.a160419082_newsapp.view.account

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
import com.ubaya.a160419082_newsapp.databinding.FragmentLoginBinding
import com.ubaya.a160419082_newsapp.util.loadImage
import com.ubaya.a160419082_newsapp.view.ButtonLoginClickListener
import com.ubaya.a160419082_newsapp.view.ButtonRegisterClickListener
import com.ubaya.a160419082_newsapp.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class LoginFragment : Fragment(), ButtonLoginClickListener, ButtonRegisterClickListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater,R.layout.fragment_login,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.imageViewLoginPage.loadImage("https://as1.ftcdn.net/v2/jpg/00/88/43/58/1000_" +
                "F_88435847_HhglbcoGP5qOX3DfudP3hN5z95eTrHqz.jpg",view.progressBarLoginPage)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.listenerLogin = this
        dataBinding.listenerRegister = this
    }

    override fun onButtonLoginClick(v: View) {
        viewModel.fetch(txtUsernameLogin.text.toString(),txtPasswordLogin.text.toString())
        if(viewModel.user.value?.username == txtUsernameLogin.text.toString()) {
            viewModel.updateStatusOnline(txtUsernameLogin.text.toString())

            Snackbar.make(v, "Success Login!", Snackbar.LENGTH_LONG).setAction("Success", null).show()

            val action = LoginFragmentDirections.actionNewsList()
            Navigation.findNavController(v).navigate(action)
        }
        else
        {
            Snackbar.make(v, "Login Failed", Snackbar.LENGTH_LONG).setAction("Failed", null).show()
        }
    }

    override fun onButtonRegisterClick(v: View) {
        val action = LoginFragmentDirections.actionRegister()
        Navigation.findNavController(v).navigate(action)
    }
}