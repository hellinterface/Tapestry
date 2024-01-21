package com.msvteam.tapestry.domain.box

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boxes")
data class Box(
    var name: String = UNDEFINED_STR,
    @PrimaryKey(autoGenerate = true) var id: Long? = null
) {
    companion object {
        val UNDEFINED_STR = ""
    }
}
