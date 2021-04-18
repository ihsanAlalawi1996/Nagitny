package com.example.project.data.repositories

import android.util.Log
import com.example.project.common.App
import com.example.project.data.models.*

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

    fun addCard(card: Cards) {
        return DataBase.getDatabaseInstance(App.instance).BaseDao().insertInCards(card)
    }

    fun addTransaction(history: History) {
        return DataBase.getDatabaseInstance(App.instance).BaseDao().insertInHistory(history)
    }

    fun getAllItems(phone: Int): List<Items>? {
        return DataBase.getDatabaseInstance(App.instance).BaseDao().getAllItems(phone)
    }

    fun getAllCards(phone: Int): List<Cards>? {
        return DataBase.getDatabaseInstance(App.instance).BaseDao().getAllCards(phone)

    }
    fun getAllTransactions(phone: Int): List<History> {
        return DataBase.getDatabaseInstance(App.instance).BaseDao().getAllTransactions(phone)

    }
    fun deleteCard(cardNumber: String?) {
        DataBase.getDatabaseInstance(App.instance).BaseDao().deleteCard(cardNumber)
    }

    fun deleteItem(itemId: Int) {
        DataBase.getDatabaseInstance(App.instance).BaseDao().deleteItem(itemId)

    }


}