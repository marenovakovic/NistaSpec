package com.marko.data.datasource

import com.marko.data.entities.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemoteDataSource {
	fun getProjects(): Observable<List<ProjectEntity>>
}