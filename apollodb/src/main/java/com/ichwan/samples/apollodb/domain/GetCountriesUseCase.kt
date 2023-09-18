package com.ichwan.samples.apollodb.domain

import com.ichwan.samples.apollodb.data.SimpleCountry

class GetCountriesUseCase(private val countryClient: CountryClient) {

    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}