package com.example.diffutilsample.data.dto.comicsinfo

import com.example.diffutilsample.data.dto.comicsinfo.ComicsDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicsWrapperDto(
    @SerialName("results")
    val results: List<ComicsDto>
)