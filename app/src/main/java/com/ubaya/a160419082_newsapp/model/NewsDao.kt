package com.ubaya.a160419082_newsapp.model

import androidx.room.*

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg News:News)

    @Query("SELECT * FROM News ORDER BY author DESC")
    suspend fun selectAllNews(): List<News>

    @Query("SELECT * FROM News WHERE idNews= :id")
    suspend fun selectNews(id:Int): News

    @Query("UPDATE News SET author=:author, title=:title, description=:desc, url=:imageUrl WHERE idNews=:idNews")
    suspend fun update(author:String, title:String, desc:String, imageUrl:String, idNews:Int)

    @Delete
    suspend fun deleteNews(News: News)
}