package com.example.diffutilsample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Word(
    val
    id: Int,
    val word: String
) : Parcelable