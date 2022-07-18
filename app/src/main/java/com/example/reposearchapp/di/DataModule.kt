package com.example.reposearchapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.reposearchapp.data.repository.AccessTokenRepository
import com.example.reposearchapp.data.repository.AccessTokenRepository.Companion.ACCESS_TOKEN_DATA_STORE
import com.example.reposearchapp.data.repository.SearchRepository
import com.example.reposearchapp.util.GithubLanguageColorUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun provideAccessTokenPreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(ACCESS_TOKEN_DATA_STORE) }
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccessTokenRepository(
        dataStore: DataStore<Preferences>
    ): AccessTokenRepository {
        return AccessTokenRepository(dataStore)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        githubLanguageColorUtil: GithubLanguageColorUtil
    ): SearchRepository {
        return SearchRepository(githubLanguageColorUtil)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GithubLanguageColorUtilModule {

    @Singleton
    @Provides
    fun provideGithubLanguageColorUtil(
        @ApplicationContext application: Context,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): GithubLanguageColorUtil =
        GithubLanguageColorUtil(
            application,
            defaultDispatcher
        )
}
