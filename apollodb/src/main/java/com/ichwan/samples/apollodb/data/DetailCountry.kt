package com.ichwan.samples.apollodb.data

data class DetailCountry(
    val code: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val languages: List<String>?,
    val continent: String?
)
