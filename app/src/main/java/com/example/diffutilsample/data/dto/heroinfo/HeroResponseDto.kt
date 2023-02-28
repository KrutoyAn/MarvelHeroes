package com.example.diffutilsample.data.dto.heroinfo

import com.example.diffutilsample.data.dto.MarvelPagingDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroResponseDto(
    @SerialName("copyright")
    val copyright: String,
    @SerialName("data")
    val pagingInfo: MarvelPagingDto
)