package com.marko.data.datasource

import javax.inject.Inject

open class ProjectsDataSourceFactory @Inject constructor(private val projectsRemoteSource: ProjectsRemoteSource,
													private val projectsCacheSource: ProjectsCacheSource) {

	open fun getDataSource(areProjectsCached: Boolean, isCacheExpired: Boolean): ProjectsDataSource {
		return if (areProjectsCached && !isCacheExpired) {
			projectsCacheSource
		} else {
			projectsRemoteSource
		}
	}

	open fun getCacheDataSource(): ProjectsDataSource = projectsCacheSource
}