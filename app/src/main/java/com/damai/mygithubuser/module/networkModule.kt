package com.damai.mygithubuser.module

import com.damai.mygithubuser.BuildConfig
import com.damai.mygithubuser.core.ApplicationDispatchersProvider
import com.damai.mygithubuser.core.SchedulerProvider
import com.damai.mygithubuser.data.service.MainService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
private const val TIMEOUT: Long = 60
private const val BASE_URL = "https://api.github.com"

val networkModule = module {
    single { GsonBuilder().create() }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder().apply{
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            cache(null)
            addInterceptor(logging)
            addInterceptor{ chain ->
                val request = requestBuilder(chain)
                chain.proceed(request)
            }
        }.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(MainService::class.java) }
    factory<SchedulerProvider> { ApplicationDispatchersProvider() }
}

private fun requestBuilder(chain: Interceptor.Chain): Request {
    val original = chain.request()
    return original.newBuilder()
        .header("Accept", "application/vnd.github.v3+json")
        .header("User-Agent", "damaisubimawanto")
        .build()
}