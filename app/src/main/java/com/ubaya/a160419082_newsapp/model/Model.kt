package com.ubaya.a160419082_newsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @ColumnInfo(name="author")
    var author:String,
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="description")
    var description:String,
    @ColumnInfo(name="url")
    var url:String
){
    @PrimaryKey(autoGenerate = true)
    var idNews:Int = 0
}

@Entity
data class MyNews(
    @ColumnInfo(name="author")
    var author:String,
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="description")
    var description:String,
    @ColumnInfo(name="url")
    var url:String
) {
    @PrimaryKey(autoGenerate = true)
    var idMyNews:Int = 0
}

@Entity
data class User(
    @ColumnInfo(name = "username")
    var username:String,
    @ColumnInfo(name = "email")
    var email:String,
    @ColumnInfo(name = "pass")
    var pass:String,
    @ColumnInfo(name = "imageUrl")
    var imageUrl:String,
    @ColumnInfo(name = "status")
    var status:Int
){
    @PrimaryKey(autoGenerate = true)
    var idUser:Int=0
}