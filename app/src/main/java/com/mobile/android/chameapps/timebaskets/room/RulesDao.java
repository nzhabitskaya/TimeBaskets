package com.mobile.android.chameapps.timebaskets.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface RulesDao {

    @Query("SELECT * FROM rules ORDER BY timestamp DESC")
    Observable<List<Item>> findAll();

    @Query("SELECT * FROM rules ORDER BY timestamp DESC LIMIT 1")
    LiveData<Item> findLast();

    @Query("SELECT * FROM rules WHERE id=:id")
    Item findById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Item word);

    @Update
    int update(Item word);

    @Delete
    void delete(Item word);
}
