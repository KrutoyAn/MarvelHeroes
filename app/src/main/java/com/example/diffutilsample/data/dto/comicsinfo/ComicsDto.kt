package com.example.diffutilsample.data.dto.comicsinfo

import com.example.diffutilsample.data.dto.ThumbNailDto
import com.example.diffutilsample.data.dto.heroinfo.mapToThumbnailEntity
import com.example.diffutilsample.data.dto.heroinfo.mapToThumbnailModel
import com.example.diffutilsample.presentation.model.СomicsModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ComicsDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String?,
    @SerialName("thumbnail")
    val thumbnail: ThumbNailDto
)

fun ComicsDto.mapToModel(): СomicsModel {
    return СomicsModel(
        id = id.toLong(),
        name = title.ifEmpty { "Oh, Comics on editing" },
        thumbnail = thumbnail.mapToThumbnailEntity().mapToThumbnailModel()
    )
}