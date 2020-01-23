package com.mobile.android.chameapps.timebaskets.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import io.reactivex.Observable

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items ORDER BY timestamp DESC")
    fun findAll(): Observable<List<Item>>

    @Query("SELECT * FROM items ORDER BY timestamp DESC LIMIT 1")
    fun findLast(): LiveData<Item>

    @Query("SELECT * FROM items WHERE id=:id")
    fun findById(id: String?): Item

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Item): Long

    @Update
    fun update(word: Item): Int

    @Delete
    fun delete(word: Item)
}