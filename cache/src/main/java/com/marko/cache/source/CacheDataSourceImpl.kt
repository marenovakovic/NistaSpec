package com.marko.cache.source

import com.marko.cache.database.database.ProjectsDatabase
import com.marko.cache.entities.Config
import com.marko.cache.mapper.CacheProjectMapper
import com.marko.data.datasource.ProjectsCacheDataSource
import com.marko.data.entities.ProjectEntity
import com.marko.domain.extensions.now
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CacheDataSourceImpl @Inject constructor(private val database: ProjectsDatabase,
											  private val mapper: CacheProjectMapper): ProjectsCacheDataSource {
	override fun getProjects(): Observable<List<ProjectEntity>> {
		return database.cachedProjectsDao()
				.getProjects()
				.toObservable()
				.map { mapper.mapFromCache(it) }
	}

	override fun saveProjects(projects: List<ProjectEntity>): Completable {
		return database.cachedProjectsDao()
				.insertProjects(mapper.mapToCache(projects))
	}

	override fun clearProjects(): Completable {
		return database.cachedProjectsDao()
				.deleteProjects()
	}

	override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
		return database.cachedProjectsDao()
				.getBookmarkedProjects()
				.toObservable()
				.map { mapper.mapFromCache(it) }
	}

	override fun bookmarkProject(projectId: String): Completable {
		return database.cachedProjectsDao()
				.bookmarkProject(true, projectId)
	}

	override fun unBookmarkProject(projectId: String): Completable {
		return database.cachedProjectsDao()
				.bookmarkProject(false, projectId)
	}

	override fun areProjectsCached(): Single<Boolean> {
		return database.cachedProjectsDao()
				.getProjects()
				.isEmpty
				.map { !it }
	}

	override fun setLastCacheTime(lastCacheTime: Long): Completable {
		return database.configDao()
				.saveConfig(Config(lastCacheTime))
	}

	override fun isCacheExpired(): Single<Boolean> {
		return database.configDao()
				.getConfig()
				.single(Config(lastCachedTime = 0))
				.map { now - it.lastCachedTime >= 60 * 10 * 1000 }
	}
}