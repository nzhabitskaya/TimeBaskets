package com.mobile.android.chameapps.timebaskets.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao;
import com.mobile.android.chameapps.timebaskets.room.enitities.Category;

@Database(entities = {Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoriesDao categoriesDao();
}