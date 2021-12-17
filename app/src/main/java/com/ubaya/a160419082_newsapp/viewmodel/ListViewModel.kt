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

class ListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val newsLD = MutableLiveData<List<News>>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    private var job = Job()

    fun refresh() {
        loadingDoneLD.value = true
        loadingErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            newsLD.value = db.newsDao().selectAllNews()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//
}