package com.example.project.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TransactionsTable")

data class Transactions (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movement_id")
    @SerializedName("movement_id")
    var movement_id:Int,
    @ColumnInfo(name = "sender_id")
    @SerializedName("sender_id")
    var sender_id:Int,
    @ColumnInfo(name = "sender_name")
    @SerializedName("sender_name")
    var sender_name:String,
    @ColumnInfo(name = "receiver_name")
    @SerializedName("receiver_name")
    var receiver_name:String,
    @ColumnInfo(name = "receiver_id")
    @SerializedName("receiver_id")
    var receiver_id:Int,
    @ColumnInfo(name = "item_name")
    @SerializedName("item_name")
    var item_name:String,
    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    var amount:Double,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date:String,
)