package com.example.project.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "HistoryTable")

data class History (
    @PrimaryKey
    @ColumnInfo(name = "movement_id")
    @SerializedName("movement_id")
    var movement_id:Int,
    @ColumnInfo(name = "couplesId")
    @SerializedName("couplesId")
    var couplesId:Int,
    @ColumnInfo(name = "sender_name")
    @SerializedName("sender_name")
    var sender_name:String,
    @ColumnInfo(name = "item_name")
    @SerializedName("item_name")
    var item_name:String,
    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    var amount:Int,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date:String,
)