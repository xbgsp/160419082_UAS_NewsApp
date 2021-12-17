package com.ubaya.a160419082_newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.a160419082_newsapp.model.News
import com.ubaya.a160419082_newsapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailNewsViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val newsLD = MutableLiveData<News>()
    private var job = Job()

    fun fetch(idNews:Int)
    {
        launch{
            val db = buildDB(getApplication())
            newsLD.value=db.newsDao().selectNews(idNews)
        }
    }

    fun update(author: String, title:String, desc: String, url:String, idNews: Int)
    {
        launch {
            val db = buildDB(getApplication())
            db.newsDao().update(author,title,desc,url,idNews)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//
}