package com.mobile.android.chameapps.timebaskets.room.enitities

import androidx.room.*

@Entity(tableName = "categories")
class Category {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "title")
    var title: String

    @ColumnInfo(name = "timestamp")
    var timestamp: String

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var byteArray: ByteArray?

    @Ignore
    constructor() {
        this.title = ""
        this.timestamp = ""
        this.byteArray = null
    }

    constructor(title: String, timestamp: String, byteArray: ByteArray) {
        this.title = title
        this.timestamp = timestamp
        this.byteArray = byteArray
    }
}
