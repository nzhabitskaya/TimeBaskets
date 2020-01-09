package com.mobile.android.chameapps.timebaskets.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rules", indices = [Index(value = ["id"], unique = true)])
class Item {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var title: String
    var description: String
    var timestamp: String? = null
    var categoryId: Long = 0

    @Ignore
    constructor() {
        this.title = ""
        this.description = ""
        this.timestamp = null
    }

    constructor(title: String, description: String, timestamp: String, categoryId: Long) {
        this.title = title
        this.description = description
        this.timestamp = timestamp
        this.categoryId = categoryId
    }
}
