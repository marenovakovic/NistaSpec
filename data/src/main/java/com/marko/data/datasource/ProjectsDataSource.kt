package com.marko.data.datasource

import com.marko.data.entities.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataSource {
	fun getProjects(): Observable<List<ProjectEntity>>
	fun getBookmarkedProjects(): Observable<List<ProjectEntity>>
	fun bookmarkProject(projectId: String): Completable
	fun unBookmarkProject(projectId: String): Completable
	fun saveProjects(projects: List<ProjectEntity>): Completable
	fun clearProjects(): Completable
}