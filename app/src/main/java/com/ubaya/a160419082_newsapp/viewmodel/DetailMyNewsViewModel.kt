package com.ubaya.a160419082_newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.a160419082_newsapp.model.MyNews
import com.ubaya.a160419082_newsapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailMyNewsViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val myNewsLD = MutableLiveData<MyNews>()
    private var job = Job()

    fun fetch(idNews:Int)
    {
        launch{
            val db = buildDB(getApplication())
            myNewsLD.value=db.myNewsDao().selectMyNews(idNews)
        }
    }

    fun update(author: String, title:String, desc: String, url:String, idNews: Int)
    {
        launch {
            val db = buildDB(getApplication())
            db.myNewsDao().update(author,title,desc,url,idNews)
        }
    }

    fun addMyNews(myNews: MyNews) {
        launch {
            val db = buildDB(getApplication())
            db.myNewsDao().insertAll(myNews)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//
}