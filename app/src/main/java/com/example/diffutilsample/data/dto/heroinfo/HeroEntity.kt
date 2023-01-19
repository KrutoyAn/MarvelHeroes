package com.example.diffutilsample.data.dto.heroinfo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.ThumbNailModel
import kotlinx.serialization.SerialName

@Entity
data class HeroEntity(
    @PrimaryKey
    @SerialName("id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded
    val thumbnail: ThumbNailEntity,
)

fun HeroEntity.mapToModel(): HeroModel {
    return HeroModel(
        id = id,
        name = name,
        thumbnail = thumbnail.mapToThumbnailModel()
    )
}

fun ThumbNailEntity.mapToThumbnailModel(): ThumbNailModel {
    return ThumbNailModel(
        extension = extension,
        path = path
    )
}