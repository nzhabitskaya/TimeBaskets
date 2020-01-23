package com.mobile.android.chameapps.timebaskets.room.enitities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "items", indices = [Index(value = ["id"], unique = true)])
class Item {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var title: String
    var timestamp: String? = null
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
