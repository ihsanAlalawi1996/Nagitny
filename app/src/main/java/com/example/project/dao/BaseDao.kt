package com.example.project.dao


import androidx.room.*
import com.example.project.data.models.Cards
import com.example.project.data.models.History
import com.example.project.data.models.Items
import com.example.project.data.models.Users


@Dao
interface BaseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInUsers(model:Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun intestIntoItems(model:Items)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInCards(card: Cards)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertInHistory(history: History)

    @Query("select * from usersTable where phone=:phone and password=:pass")
    fun checkInfo(phone:Int,pass:String):Users?

    @Query("select username from usersTable where phone=:phone ")
    fun getUserName(phone:Int):String?

    @Query("select password from usersTable where phone=:phone ")
    fun getPassword(phone:Int):String

    @Query("select * from ItemsTable where couplesId=:phone ")
    fun getAllItems(phone:Int):List<Items>

    @Query("select * from CardsTable where couplesId=:phone ")
    fun getAllCards(phone:Int):List<Cards>

    @Query("select * from HistoryTable where couplesId=:phone ")
    fun getAllTransactions(phone: Int): List<History>


    @Query("delete  from Cardstable where card_number=:cardNumber")
    fun deleteCard(cardNumber: String?)

    @Query("delete  from itemstable where itemId=:itemId")
    fun deleteItem(itemId: Int)



}
