package com.example.diffutilsample.data.dto.heroinfo

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class ThumbNailEntity(
    @ColumnInfo(name = "extension")
    val extension: String,
    @ColumnInfo(name = "path")
    val path: String,
)


fun ThumbNailEntity.getImageUrl(): String {
    return "${this.path}.${this.extension}"
}