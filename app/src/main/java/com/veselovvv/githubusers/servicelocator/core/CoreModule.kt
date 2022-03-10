package com.veselovvv.githubusers.servicelocator.core

import android.content.Context
import com.google.gson.Gson
import com.veselovvv.githubusers.core.ResourceProvider
import com.veselovvv.githubusers.presentation.main.MainViewModel
import com.veselovvv.githubusers.presentation.main.NavigationCommunication
import com.veselovvv.githubusers.presentation.main.Navigator
import com.veselovvv.githubusers.presentation.users.UserCache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class CoreModule : BaseModule<MainViewModel> {
    private companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    lateinit var gson: Gson
    lateinit var resourceProvider: ResourceProvider
    lateinit var userCache: UserCache
    lateinit var navigator: Navigator
    lateinit var navigationCommunication: NavigationCommunication
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .build()

        gson = Gson()
        resourceProvider = ResourceProvider.Base(context)
        userCache = UserCache.Base(context)
        navigator = Navigator.Base(context)
        navigationCommunication = NavigationCommunication.Base()
    }

    fun <T> makeService(clazz: Class<T>): T = retrofit.create(clazz)
    override fun getViewModel() = MainViewModel(navigator, navigationCommunication)
}