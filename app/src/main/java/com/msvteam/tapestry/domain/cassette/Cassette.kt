package com.msvteam.tapestry.domain.cassette

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cassettes")
data class Cassette(
    var cid: String = UNDEFINED_STR,
    var title: String = UNDEFINED_STR,
    var modelId: Long? = UNDEFINED_ID,
    var boxId: Long? = UNDEFINED_ID,
    var ratingPhysical: Int = 3,
    var ratingAudio: Int = 3,
    @PrimaryKey(autoGenerate = true) var id: Long? = null

) {
    companion object {
        val UNDEFINED_ID: Long? = null
        val UNDEFINED_STR: String = ""
    }
}
