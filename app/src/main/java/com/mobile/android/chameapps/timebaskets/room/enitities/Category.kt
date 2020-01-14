package com.mobile.android.chameapps.timebaskets.room.enitities

import androidx.room.*

@Entity(tableName = "categories", indices = [Index(value = ["id"], unique = true)])
class Category {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var title: String
    var timestamp: String? = null
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray? = null

    @Ignore
    constructor() {
        this.title = ""
        this.timestamp = null
    }

    constructor(title: String, timestamp: String, image: ByteArray) {
        this.title = title
        this.timestamp = timestamp
        this.image = image
    }
}
