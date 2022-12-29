package com.example.diffutilsample.data.dto.heroinfo

import com.example.diffutilsample.data.dto.heroinfo.HeroInfoDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroInfoWrapperDto(
    @SerialName("results")
    val results: List<HeroInfoDto>
)