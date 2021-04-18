package com.example.project.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CardsTable")

data class Cards(
    @PrimaryKey
    @ColumnInfo(name = "card_number")
    @SerializedName("card_number")
    var card_number:String,
    @ColumnInfo(name = "card_holder")
    @SerializedName("card_holder")
    var card_holder:String,
    @ColumnInfo(name = "couplesId")
    @SerializedName("couplesId")
    var couplesId:Int,
    @ColumnInfo(name = "ex_date")
    @SerializedName("ex_date")
    var ex_date:Int,
    @ColumnInfo(name = "card_pass")
    @SerializedName("card_pass")
    var card_pass:Int,
)