package com.marko.data.repository

import com.marko.data.datasource.ProjectsCacheDataSource
import com.marko.data.datasource.ProjectsDataSourceFactory
import com.marko.data.mapper.ProjectMapper
import com.marko.domain.entities.Project
import com.marko.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(private val mapper: ProjectMapper,
												 private val projectsCacheDataSource: ProjectsCacheDataSource,
												 private val factory: ProjectsDataSourceFactory): ProjectsRepository {

	override fun getProjects(): Observable<List<Project>> {
		return Observables.zip(
				projectsCacheDataSource.areProjectsCached()
						.toObservable(),
				projectsCacheDataSource.isCacheExpired()
						.toObservable()) { areProjectsCached: Boolean, isCacheExpired: Boolean ->
			Pair(areProjectsCached, isCacheExpired)
		}
				.flatMap {
					factory.getDataSource(it.first, it.second)
							.getProjects()
				}
				.flatMap { projects ->
					factory.getCacheDataSource()
							.saveProjects(projects)
							.andThen(Observable.just(projects))
				}
				.map {
					mapper.mapFromEntity(it)
				}
	}

	override fun bookmarkProject(projectId: String): Completable {
		return factory.getCacheDataSource().bookmarkProject(projectId)
	}

	override fun unBookmarkProject(projectId: String): Completable {
		return factory.getCacheDataSource().unBookmarkProject(projectId)
	}

	override fun getBookmarkedProjects(): Observable<List<Project>> {
		return factory.getCacheDataSource().getBookmarkedProjects()
				.map {
					mapper.mapFromEntity(it)
				}
	}
}