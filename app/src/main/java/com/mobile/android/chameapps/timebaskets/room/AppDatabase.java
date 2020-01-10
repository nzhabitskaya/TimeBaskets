package com.mobile.android.chameapps.timebaskets.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mobile.android.chameapps.timebaskets.room.dao.RulesDao;
import com.mobile.android.chameapps.timebaskets.room.enitities.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RulesDao rulesDao();
}