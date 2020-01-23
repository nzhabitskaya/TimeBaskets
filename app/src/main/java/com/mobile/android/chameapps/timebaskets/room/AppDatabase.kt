package com.mobile.android.chameapps.timebaskets.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao
import com.mobile.android.chameapps.timebaskets.room.dao.ItemsDao
import com.mobile.android.chameapps.timebaskets.room.enitities.Category
import com.mobile.android.chameapps.timebaskets.room.enitities.Item

@Database(
    entities = [Category::class, Item::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoriesDao(): CategoriesDao?
    abstract fun itemsDao(): ItemsDao?
}