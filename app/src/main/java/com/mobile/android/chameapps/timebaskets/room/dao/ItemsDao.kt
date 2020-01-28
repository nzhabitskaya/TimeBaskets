package com.mobile.android.chameapps.timebaskets.room.dao

import androidx.room.*
import com.mobile.android.chameapps.timebaskets.room.enitities.Item
import io.reactivex.Observable

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items ORDER BY timestamp DESC")
    fun findAll(): Observable<List<Item>>

    @Query("SELECT * FROM items ORDER BY timestamp DESC LIMIT 1")
    fun findLast(): Observable<List<Item>>

    @Query("SELECT * FROM items WHERE category_id=:id")
    fun findByCategoryId(id: Long): Observable<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item): Long

    @Update
    fun update(item: Item): Int

    @Delete
    fun delete(item: Item)
}