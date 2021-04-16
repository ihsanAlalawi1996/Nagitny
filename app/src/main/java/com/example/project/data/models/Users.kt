package com.example.project.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "usersTable")

data class Users(
    @PrimaryKey
        @ColumnInfo(name = "username")
    @SerializedName("username")
    var username:String,
    @ColumnInfo(name = "password")
    @SerializedName("password")
    var password:String,
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    var phone:Int,
    @ColumnInfo(name = "picture")
    @SerializedName("picture")
    var picture:String
)