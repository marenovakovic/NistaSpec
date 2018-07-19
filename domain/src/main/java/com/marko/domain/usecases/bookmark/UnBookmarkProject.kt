package com.marko.domain.usecases.bookmark

import com.marko.domain.executor.PostExecutionThread
import com.marko.domain.repository.ProjectsRepository
import com.marko.domain.usecases.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UnBookmarkProject @Inject constructor(postExecutionThread: PostExecutionThread,
											private val projectsRepository: ProjectsRepository): CompletableUseCase<UnBookmarkProject.Params>(postExecutionThread) {

	override fun buildCompletable(params: Params?): Completable {
		if (params == null) {
			throw IllegalArgumentException("Params can't be null")
		}
		return projectsRepository.unBookmarkProject(params.projectId)
	}

	data class Params(val projectId: String) {
		companion object {
			fun forProject(projectId: String): Params = Params(projectId)
		}
	}
}