package com.mobile.android.chameapps.timebaskets.room.di;

import android.app.Application;

import androidx.room.Room;

import com.mobile.android.chameapps.timebaskets.room.AppDatabase;
import com.mobile.android.chameapps.timebaskets.room.dao.CategoriesDao;
import com.mobile.android.chameapps.timebaskets.room.dao.ItemsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by n.zhabitskaya on 9/30/18.
 */

@Module
public class RoomModule {

    private AppDatabase database;

    public RoomModule(Application mApplication) {
        database = Room.databaseBuilder(mApplication, AppDatabase.class, "todos-db").build();
    }

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    CategoriesDao providesCategoriesDao(AppDatabase database) {
        return database.categoriesDao();
    }

    @Singleton
    @Provides
    ItemsDao providesItemsDao(AppDatabase database) {
        return database.itemsDao();
    }
}
