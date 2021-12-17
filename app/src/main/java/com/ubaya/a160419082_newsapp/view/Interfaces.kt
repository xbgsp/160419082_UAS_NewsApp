package com.ubaya.a160419082_newsapp.view

import android.view.View
import com.ubaya.a160419082_newsapp.model.MyNews
import com.ubaya.a160419082_newsapp.model.User

// FOR NEWS & MYNEWS
interface ButtonDetailClickListener {
    fun onButtonDetailClick(v: View)
}

interface ButtonAddClickListener {
    fun onButtonAddClick(v: View)
}

interface ButtonEditClickListener {
    fun onButtonEditClick(v: View)
}

interface ButtonEditMyNewsClickListener {
    fun onButtonEditMyNewsClick(v: View, obj: MyNews)
}

interface ButtonDeleteClickListener {
    fun onButtonDeleteClick(v: View, obj: MyNews)
}

// FOR USER
interface ButtonLoginClickListener {
    fun onButtonLoginClick(v: View)
}

interface ButtonSubmitRegisterClickListener {
    fun onButtonSubmitRegisterClick(v: View)
}

interface ButtonRegisterClickListener {
    fun onButtonRegisterClick(v: View)
}

interface ButtonDetailProfileClickListener {
    fun onButtonDetailProfileClick(v: View)
}