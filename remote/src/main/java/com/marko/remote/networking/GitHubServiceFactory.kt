package com.marko.remote.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GitHubServiceFactory {

	fun makeGitHubService(isDebugging: Boolean): GitHubService {
		val loggingInterceptor = makeHttpLoggingInterceptor(isDebugging)
		val okHttpClient = makeOkHttpClient(loggingInterceptor)
		return makeRetrofitService(okHttpClient)
	}

	private fun makeRetrofitService(okHttpClient: OkHttpClient): GitHubService {
		return Retrofit.Builder()
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttpClient)
				.baseUrl(BASE_URL)
				.build()
				.create(GitHubService::class.java)
	}

	private fun makeHttpLoggingInterceptor(isDebugging: Boolean): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			level = if (isDebugging) {
				HttpLoggingInterceptor.Level.BODY
			} else {
				HttpLoggingInterceptor.Level.NONE
			}
		}
	}

	private fun makeOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
		return OkHttpClient.Builder()
				.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
				.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
				.addInterceptor(loggingInterceptor)
				.build()
	}

	companion object {
		private const val BASE_URL = "https://api.github.com/"
		private const val WRITE_TIMEOUT = 15L
		private const val READ_TIMEOUT = 15L
	}
}