package com.example.project.dao


import androidx.room.*
import com.example.my_first_task.data.models.Items
import com.example.project.data.models.Users


@Dao
interface BaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInUsers(model:Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun intestIntoItems(model:Items)


    @Query("select * from usersTable where phone=:phone and password=:pass")
    fun getInfo(phone:Int,pass:String):Users?

}
