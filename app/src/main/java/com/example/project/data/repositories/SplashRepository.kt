package com.example.project.data.repositories

import android.util.Log
import com.example.my_first_task.data.models.Items
import com.example.project.common.App
import com.example.project.data.models.DataBase
import com.example.project.data.models.Users

class SplashRepository {

    fun registerNewUser(model:Users){
        DataBase.getDatabaseInstance(App.instance).BaseDao().insertInUsers(model)

    }
    fun checkInfo(phone:Int,pass:String):Boolean{

        var users=DataBase.getDatabaseInstance(App.instance).BaseDao().getInfo(phone,pass)

        return users?.password != null
    }

}