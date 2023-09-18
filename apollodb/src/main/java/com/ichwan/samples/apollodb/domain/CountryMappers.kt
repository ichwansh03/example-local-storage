package com.ichwan.samples.apollodb.domain

import com.ichwan.samples.apollodb.CountriesQuery
import com.ichwan.samples.apollodb.CountryQuery
import com.ichwan.samples.apollodb.data.DetailCountry
import com.ichwan.samples.apollodb.data.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailCountry {
    return DetailCountry(
        code = code,
        name = name,
        emoji = emoji,
        currency = currency,
        languages = languages?.mapNotNull { it?.name },
        continent = continent?.name
    )
}

fun CountriesQuery.Country.toSimpledCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        currency = currency
    )
}