package com.marko.data.datasource

import com.marko.data.entities.ProjectEntity
import com.marko.domain.extensions.now
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class ProjectsCacheSource @Inject constructor(private val projectsCacheDataSource: ProjectsCacheDataSource): ProjectsDataSource {

	override fun getProjects(): Observable<List<ProjectEntity>> {
		return projectsCacheDataSource.getProjects()
	}

	override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
		return projectsCacheDataSource.getBookmarkedProjects()
	}

	override fun bookmarkProject(projectId: String): Completable {
		return projectsCacheDataSource.bookmarkProject(projectId)
	}

	override fun unBookmarkProject(projectId: String): Completable {
		return projectsCacheDataSource.unBookmarkProject(projectId)
	}

	override fun saveProjects(projects: List<ProjectEntity>): Completable {
		return projectsCacheDataSource.saveProjects(projects)
				.andThen(projectsCacheDataSource.setLastCacheTime(now))
	}

	override fun clearProjects(): Completable {
		return projectsCacheDataSource.clearProjects()
	}
}