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

class MyNewsViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val myNewsLD = MutableLiveData<List<MyNews>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    private var job = Job()

    fun refresh() {
        loadingDoneLD.value = true
        loadingErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            myNewsLD.value = db.myNewsDao().selectAllMyNews()
        }
    }

    fun clearMyNews(myNews: MyNews) {
        launch{
            val db = buildDB(getApplication())
            db.myNewsDao().deleteMyNews(myNews)
            myNewsLD.value = db.myNewsDao().selectAllMyNews()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//
}