package com.ubaya.a160419082_newsapp.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser():List<User>

    @Query("SELECT * FROM user WHERE idUser=:userId")
    suspend fun selectUserDetail(userId: Int):User

    @Query("SELECT * FROM user where username =:username AND pass=:password")
    suspend fun selectUser(username:String,password: String):User

    @Query("SELECT * FROM user where status=1")
    suspend fun selectStatusUser():User

    @Query("UPDATE user SET username=:username, email=:email, pass=:password, imageUrl=:url WHERE idUser=:idUser")
    suspend fun update(username:String, email:String, password:String, url:String, idUser:Int)

    @Query("UPDATE user SET status=1 WHERE username=:username")
    suspend fun updateStatus(username: String)

    @Delete
    suspend fun deleteUser(user: User)
}