package com.msvteam.tapestry.domain.tracklist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracklists")
data class Tracklist(
    var cassetteId: Long = -1,
    @PrimaryKey(autoGenerate = true) var id: Long? = null
)
