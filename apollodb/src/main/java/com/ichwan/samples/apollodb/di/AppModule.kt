package com.ichwan.samples.apollodb.di

import com.apollographql.apollo3.ApolloClient
import com.ichwan.samples.apollodb.domain.ApolloCountryClient
import com.ichwan.samples.apollodb.domain.CountryClient
import com.ichwan.samples.apollodb.domain.GetCountriesUseCase
import com.ichwan.samples.apollodb.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Singleton
    @Provides
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Singleton
    @Provides
    fun provideGetCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @Singleton
    @Provides
    fun provideGetCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }
}