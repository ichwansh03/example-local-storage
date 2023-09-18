package com.ichwan.samples.apollodb.domain

import com.ichwan.samples.apollodb.data.DetailCountry

class GetCountryUseCase(private val countryClient: CountryClient) {

    suspend fun execute(code: String): DetailCountry? {
        return countryClient.getCountry(code)
    }
}