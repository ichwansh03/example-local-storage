package com.ichwan.samples.apollodb.domain

import com.ichwan.samples.apollodb.data.DetailCountry
import com.ichwan.samples.apollodb.data.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailCountry?
}