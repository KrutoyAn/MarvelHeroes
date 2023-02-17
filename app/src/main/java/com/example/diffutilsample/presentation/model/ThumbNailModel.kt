package com.example.diffutilsample.presentation.model

data class ThumbNailModel(
    val extension: String,
    val path: String,
)

fun ThumbNailModel.getImageUrl(): String {
    return "${this.path}.${this.extension}"
}