package com.marko.remote.source

import com.marko.data.datasource.ProjectsRemoteDataSource
import com.marko.data.entities.ProjectEntity
import com.marko.remote.mapper.ProjectRemoteMapper
import com.marko.remote.networking.GitHubService
import io.reactivex.Observable
import javax.inject.Inject

open class RemoteDataSourceImpl @Inject constructor(private val gitHubService: GitHubService,
											   private val projectRemoteMapper: ProjectRemoteMapper): ProjectsRemoteDataSource {

	override fun getProjects(): Observable<List<ProjectEntity>> {
		return gitHubService.searchRepositories(QUERY, SORT, ORDER)
				.map { projectRemoteMapper.mapToData(it.projects) }
	}

	companion object {
		private const val QUERY = "language:kotlin"
		private const val SORT = "star"
		private const val ORDER = "desc"
	}
}