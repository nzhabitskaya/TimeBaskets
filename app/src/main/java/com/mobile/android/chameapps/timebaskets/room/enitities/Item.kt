package com.mobile.android.chameapps.timebaskets.room.enitities

import androidx.room.*

@Entity(tableName = "items", indices = [Index(value = ["title"], unique = true)])
class Item {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "title")
    var title: String

    @ColumnInfo(name = "timestamp")
    var timestamp: String? = null

    @ColumnInfo(name = "category_id")
    var categoryId: Long = 0

    @Ignore
    constructor() {
        this.title = ""
        this.timestamp = null
    }

    constructor(title: String, timestamp: String, categoryId: Long) {
        this.title = title
        this.timestamp = timestamp
        this.categoryId = categoryId
    }
}
