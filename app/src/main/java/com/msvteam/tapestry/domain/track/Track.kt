package com.msvteam.tapestry.domain.track

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class Track(
    var title: String = "",
    var artist: String = "",
    var tracklistId: Long = -1,
    @PrimaryKey(autoGenerate = true) var id: Long? = null
)
