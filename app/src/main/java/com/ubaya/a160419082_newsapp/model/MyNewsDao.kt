package com.ubaya.a160419082_newsapp.model

import androidx.room.*

@Dao
interface MyNewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg MyNews:MyNews)

    @Query("SELECT * FROM MyNews ORDER BY author DESC")
    suspend fun selectAllMyNews(): List<MyNews>

    @Query("SELECT * FROM MyNews WHERE idMyNews= :id")
    suspend fun selectMyNews(id:Int): MyNews

    @Query("UPDATE MyNews SET author=:author, title=:title, description=:desc, url=:imageUrl WHERE idMyNews=:idMyNews")
    suspend fun update(author:String, title:String, desc:String, imageUrl:String, idMyNews:Int)

    @Delete
    suspend fun deleteMyNews(MyNews: MyNews)
}