package com.example.diffutilsample.data.dto.comicsinfo

import com.example.diffutilsample.data.dto.comicsinfo.ComicsWrapperDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicsWrapperResponse(
    @SerialName("data")
    val info: ComicsWrapperDto
)