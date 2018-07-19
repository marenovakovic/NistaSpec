package com.marko.domain.usecases.bookmark

import com.marko.domain.executor.PostExecutionThread
import com.marko.domain.repository.ProjectsRepository
import com.marko.domain.usecases.factory.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnBookmarkProjectTest {

	private lateinit var unBookmarkProject: UnBookmarkProject
	@Mock
	lateinit var postExecutionThread: PostExecutionThread
	@Mock
	lateinit var projectsRepository: ProjectsRepository

	private fun stubUnBookmarkProject(completable: Completable) {
		whenever(projectsRepository.unBookmarkProject(any()))
				.thenReturn(completable)
	}

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		unBookmarkProject = UnBookmarkProject(postExecutionThread, projectsRepository)
	}

	@Test
	fun doesUnBookmarkProjectCompletes() {
		stubUnBookmarkProject(Completable.complete())
		val observer = unBookmarkProject.buildCompletable(
				UnBookmarkProject.Params.forProject(ProjectDataFactory.randomString)).test()
		observer.assertComplete()
	}

	@Test(expected = IllegalArgumentException::class)
	fun exceptionShouldBeThrownIsNoParamsWerePassed() {
		unBookmarkProject.buildCompletable()
	}
}