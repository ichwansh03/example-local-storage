package com.ichwan.samples.apollodb.data

data class CountriesState (
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: DetailCountry? = null
)