package com.marko.data.datasource

import com.marko.data.entities.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectsRemoteSource @Inject constructor(private val projectsRemoteDataSource: ProjectsRemoteDataSource): ProjectsDataSource {
	override fun getProjects(): Observable<List<ProjectEntity>> {
		return projectsRemoteDataSource.getProjects()
	}

	override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
		throw UnsupportedOperationException("Getting bookmarket projects is not allowed in RemoteDataSource")
	}

	override fun bookmarkProject(projectId: String): Completable {
		throw UnsupportedOperationException("Bookmarking project is not allowed in RemoteDataSource")
	}

	override fun unBookmarkProject(projectId: String): Completable {
		throw UnsupportedOperationException("Un bookmarking project is not allowed in RemoteDataSource")
	}

	override fun saveProjects(projects: List<ProjectEntity>): Completable {
		throw UnsupportedOperationException("Saving projects is not allowed in RemoteDataSource")
	}

	override fun clearProjects(): Completable {
		throw UnsupportedOperationException("Clearing projects is not allowed in RemoteDataSource")
	}
}