package com.example.my_first_task.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ItemTable")

data class Items (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemId")
    @SerializedName("itemId")
    var itemId:Int,
    @ColumnInfo(name = "couplesId")
    @SerializedName("couplesId")
    var couplesId:Int,
    @ColumnInfo(name = "image")
    @SerializedName("image")
    var image:String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name:String,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    var price:Double,
    @ColumnInfo(name = "address")
    @SerializedName("address")
    var address:String,
    @ColumnInfo(name = "importance")
    @SerializedName("importance")
    var importance:Int,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    var status:String,
    @ColumnInfo(name = "percentage")
    @SerializedName("percentage")
    var percentage:Double)
