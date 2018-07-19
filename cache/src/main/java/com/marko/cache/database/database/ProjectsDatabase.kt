package com.marko.cache.database.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.marko.cache.constants.DatabaseConstants
import com.marko.cache.database.dao.ConfigDao
import com.marko.cache.database.dao.ProjectsDao
import com.marko.cache.entities.CachedProject
import com.marko.cache.entities.Config
import javax.inject.Inject

@Database(entities = [
	(CachedProject::class),
	(Config::class)
], version = DatabaseConstants.DATABASE_VERSION)
abstract class ProjectsDatabase @Inject constructor(): RoomDatabase() {

	abstract fun cachedProjectsDao(): ProjectsDao
	abstract fun configDao(): ConfigDao

	private var INSTANCE: ProjectsDatabase? = null
	private val lock = Any()

	private fun getInstance(context: Context): ProjectsDatabase {
		if (INSTANCE == null) {
			synchronized(lock) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(
							context.applicationContext,
							ProjectsDatabase::class.java,
							DatabaseConstants.DATABASE_NAME
					)
							.build()
				}
				return INSTANCE as ProjectsDatabase
			}
		}
		return INSTANCE as ProjectsDatabase
	}
}