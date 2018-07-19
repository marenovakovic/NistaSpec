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

class BookmarkProjectTest {

	private lateinit var bookmarkProject: BookmarkProject
	@Mock
	lateinit var postExecutionThread: PostExecutionThread
	@Mock
	lateinit var projectsRepository: ProjectsRepository

	private fun stubBookmarkProject(completable: Completable) {
		whenever(projectsRepository.bookmarkProject(any()))
				.thenReturn(completable)
	}

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		bookmarkProject = BookmarkProject(postExecutionThread, projectsRepository)
	}

	@Test
	fun doesBookmarkProjectCompletes() {
		stubBookmarkProject(Completable.complete())
		val observer = bookmarkProject.buildCompletable(
				BookmarkProject.Params.forProject(ProjectDataFactory.randomString)).test()
		observer.assertComplete()
	}

	@Test(expected = IllegalArgumentException::class)
	fun exceptionShouldBeThrownWhenNoIdIsPassed() {
		bookmarkProject.buildCompletable()
	}
}