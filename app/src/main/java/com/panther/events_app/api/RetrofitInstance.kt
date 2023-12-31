package com.panther.events_app.api

import com.google.gson.GsonBuilder
import com.panther.events_app.BASE_URL
import com.panther.events_app.CURRENT_SESSION_TOKEN
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private val eventsSharedPref = EventsSharedPreference().getSharedPref()

    private val retrofit by lazy {
        val  logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client((client))
            .build()

    }
    private val authRetrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor {chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $CURRENT_SESSION_TOKEN").build()
                chain.proceed(request)
            }
            .build()


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client((client))
            .build()

    }

    val apiService : EventsApi by lazy {
        retrofit.create(EventsApi::class.java)
    }
    val apiServiceAuth: EventsApi by lazy {
        authRetrofit.create(EventsApi::class.java)
    }

}

