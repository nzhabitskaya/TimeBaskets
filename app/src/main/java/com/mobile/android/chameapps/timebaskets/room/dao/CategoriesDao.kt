package com.mobile.android.chameapps.timebaskets.room.dao

import androidx.room.*
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import io.reactivex.Observable

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories ORDER BY timestamp DESC")
    fun findAll(): Observable<List<Category>>

    @Query("SELECT * FROM categories ORDER BY timestamp DESC LIMIT 1")
    fun findLast(): Observable<Category>

    @Query("SELECT * FROM categories WHERE id=:id")
    fun findById(id: String): Observable<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category): Long

    @Update
    fun update(category: Category): Int

    @Delete
    fun delete(category: Category)
}