package com.example.littlelemonfinal

import android.view.Menu
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val desc: String,
    val category: String,
    val image: String,
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItem")
    fun getAllItems(): LiveData<List<MenuItem>>

    @Insert
    fun insertItems(vararg menuItems: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1)
abstract  class LittleLemonDatabase : RoomDatabase() {
    abstract fun menuItemDao() : MenuItemDao
}