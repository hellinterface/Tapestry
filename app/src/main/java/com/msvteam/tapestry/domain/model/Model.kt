package com.msvteam.tapestry.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "models")
data class Model(
    var title: String = UNDEFINED_STR,
    var time: Int = 0,
    var tapeType: Int = 1,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long? = null
) {
    companion object {
        val UNDEFINED_STR = ""
    }
}
