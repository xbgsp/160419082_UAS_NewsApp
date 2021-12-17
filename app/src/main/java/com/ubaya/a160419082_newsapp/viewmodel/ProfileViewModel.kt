package com.ubaya.a160419082_newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubaya.a160419082_newsapp.model.User
import com.ubaya.a160419082_newsapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val user = MutableLiveData<User>()
    val profile = MutableLiveData<User>()
    val loadingErrorLD = MutableLiveData<Boolean>()
    val loadingDoneLD = MutableLiveData<Boolean>()

    private var job = Job()

    fun fetch(username:String,password:String){
        launch{
            val db = buildDB(getApplication())
            user.value = db.userDao().selectUser(username,password)
        }
    }

    fun addUser(user: User){
        launch {
            val db = buildDB(getApplication())
            db.userDao().insertUser(user)
        }
    }

    fun refresh(){
        loadingErrorLD.value = false
        loadingDoneLD.value = true
        launch{
            val db = buildDB(getApplication())
            profile.value = db.userDao().selectStatusUser()
        }
    }

    fun selectUser(userId: Int){
        launch {
            val db = buildDB(getApplication())
            user.value= db.userDao().selectUserDetail(userId)
        }
    }

    fun updateStatusOnline(username: String){
        launch{
            val db = buildDB(getApplication())
            db.userDao().updateStatus(username)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main//
}