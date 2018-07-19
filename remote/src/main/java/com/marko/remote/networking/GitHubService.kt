package com.marko.remote.networking

import com.marko.remote.entities.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

	@GET("search/repositories")
	fun searchRepositories(
			@Query("q")query: String,
			@Query("sort") sortBy: String,
			@Query("order") orderBy: String
	): Observable<ApiResponse>
}