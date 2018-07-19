package com.marko.domain.usecases.browse

import com.marko.domain.entities.Project
import com.marko.domain.executor.PostExecutionThread
import com.marko.domain.repository.ProjectsRepository
import com.marko.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetProjects @Inject constructor(postExecutionThread: PostExecutionThread,
				  private val projectsRepository: ProjectsRepository): ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

	override fun buildObservable(params: Nothing?): Observable<List<Project>> = projectsRepository.getProjects()
}