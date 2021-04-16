package com.example.project.data.repositories

import android.util.Log
import com.example.my_first_task.data.models.Items
import com.example.project.common.App
import com.example.project.data.models.DataBase
import com.example.project.data.models.Users

class HomeRepository {

    fun addItemtoDB(model: Items){

        DataBase.getDatabaseInstance(App.instance).BaseDao().intestIntoItems(model)

    }
    fun getProfileName(phone: Int):String?{

        return DataBase.getDatabaseInstance(App.instance).BaseDao().getUserName(phone)

    }

    fun getPassword(phone: Int):String{

        return DataBase.getDatabaseInstance(App.instance).BaseDao().getPassword(phone)

    }


}