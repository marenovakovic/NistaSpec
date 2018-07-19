package com.marko.cache.database.dao

import android.arch.persistence.room.*
import com.marko.cache.constants.DatabaseConstants
import com.marko.cache.entities.CachedProject
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
abstract class ProjectsDao {

	@Query(DatabaseConstants.QUERY_ALL_PROJECTS)
	abstract fun getProjects(): Flowable<List<CachedProject>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertProjects(projects: List<CachedProject>): Completable

	@Query(DatabaseConstants.DELETE_PROJECTS)
	abstract fun deleteProjects(): Completable

	@Query(DatabaseConstants.QUERY_BOOKMARKED_PROJECTS)
	abstract fun getBookmarkedProjects(): Flowable<List<CachedProject>>

	@Query(DatabaseConstants.BOOKMARK_PROJECT)
	abstract fun bookmarkProject(isBookmarked: Boolean, projectId: String): Completable
}