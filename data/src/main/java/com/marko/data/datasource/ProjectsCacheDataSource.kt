package com.marko.data.datasource

import com.marko.data.entities.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsCacheDataSource {
	fun getProjects(): Observable<List<ProjectEntity>>
	fun saveProjects(projects: List<ProjectEntity>): Completable
	fun clearProjects(): Completable
	fun getBookmarkedProjects(): Observable<List<ProjectEntity>>
	fun bookmarkProject(projectId: String): Completable
	fun unBookmarkProject(projectId: String): Completable
	fun areProjectsCached(): Single<Boolean>
	fun setLastCacheTime(lastCacheTime: Long): Completable
	fun isCacheExpired(): Single<Boolean>
}