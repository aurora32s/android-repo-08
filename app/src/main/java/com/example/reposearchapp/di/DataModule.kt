package com.example.reposearchapp.di

import android.content.Context
import com.example.reposearchapp.data.remote.AccessApi
import com.example.reposearchapp.data.remote.GithubApi
import com.example.reposearchapp.data.remote.GithubServiceInterceptor
import com.example.reposearchapp.data.repository.AccessTokenRepository
import com.example.reposearchapp.data.repository.SearchRepository
import com.example.reposearchapp.util.GithubLanguageColorUtil
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccessTokenRepository(
        @ApplicationContext application: Context,
        accessApi: AccessApi
    ): AccessTokenRepository {
        return AccessTokenRepository(application, accessApi)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        githubLanguageColorUtil: GithubLanguageColorUtil,
        githubApi: GithubApi
    ): SearchRepository {
        return SearchRepository(githubLanguageColorUtil, githubApi)
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

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    fun provideInterceptor(
        accessTokenRepository: AccessTokenRepository
    ): GithubServiceInterceptor {
        return GithubServiceInterceptor(accessTokenRepository)
    }
}

@Qualifier
annotation class AccessApiOkHttpClient

@Qualifier
annotation class GithubApiOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @AccessApiOkHttpClient
    @Provides
    fun provideAccessApiOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @GithubApiOkHttpClient
    @Provides
    fun provideGithubApiOkHttpClient(
        interceptor: GithubServiceInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideAccessApi(
        @AccessApiOkHttpClient
        okHttpClient: OkHttpClient
    ): AccessApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.parse("application/json")!!))
            .baseUrl("https://github.com/")
            .build()
            .create(AccessApi::class.java)
    }

    @Provides
    fun provideGithubApi(
        @GithubApiOkHttpClient
        okHttpClient: OkHttpClient
    ): GithubApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.parse("application/json")!!))
            .baseUrl("https://api.github.com")
            .build()
            .create(GithubApi::class.java)
    }
}