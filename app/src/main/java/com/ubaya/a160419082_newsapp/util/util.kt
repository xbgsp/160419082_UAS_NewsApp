package com.ubaya.a160419082_newsapp.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419082_newsapp.R
import com.ubaya.a160419082_newsapp.model.NewsAppDatabase
import java.lang.Exception

val DB_NAME="news"

fun buildDB(context: Context): NewsAppDatabase {
    val db = Room.databaseBuilder(context, NewsAppDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()
    return db
}

fun ImageView.loadImage(url:String?,progressBar:ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_outline_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {

            }
        })
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoUrl(v: ImageView, url: String?, pb: ProgressBar)
{
    if(url!=null)
    {
        v.loadImage(url!!, pb)
    }
}

val MIGRATION_1_2 = object: Migration(1, 2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN status INTEGER DEFAULT 0")
    }
}