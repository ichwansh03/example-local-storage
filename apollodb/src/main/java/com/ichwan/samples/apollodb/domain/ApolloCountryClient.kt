package com.ichwan.samples.apollodb.domain

import com.apollographql.apollo3.ApolloClient
import com.ichwan.samples.apollodb.CountriesQuery
import com.ichwan.samples.apollodb.CountryQuery
import com.ichwan.samples.apollodb.data.DetailCountry
import com.ichwan.samples.apollodb.data.SimpleCountry

class ApolloCountryClient(private val apolloClient: ApolloClient): CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data?.countries?.map { it!!.toSimpledCountry() } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data?.country?.toDetailedCountry()
    }
}