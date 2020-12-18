package com.example.project.ui.Home

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.data.bases.BaseViewModel
import com.example.my_first_task.data.models.Items
import com.example.project.data.models.Users
import com.example.project.data.repositories.SplashRepository
import io.paperdb.Paper


class HomeViewModel : BaseViewModel() {

    val repository = SplashRepository()
    val users = MutableLiveData<Users>()


    fun addItem(holderFragment:Int,desFragemnt: Fragment) {
        Home.instance.supportFragmentManager.beginTransaction().apply {
            replace(holderFragment,desFragemnt)
            addToBackStack(null)
            commit()
        }
    }
    fun postItem(){
        var items= Items(
            Paper.book().read("mkey",0),"","",0.0,"",0,"",0.0)
     repository.addItemtoDB(items)

    }




    }




