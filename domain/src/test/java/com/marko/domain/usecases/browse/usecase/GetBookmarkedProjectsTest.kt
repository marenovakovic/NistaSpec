package com.marko.domain.usecases.browse.usecase

import com.marko.domain.entities.Project
import com.marko.domain.executor.PostExecutionThread
import com.marko.domain.repository.ProjectsRepository
import com.marko.domain.usecases.browse.GetBookmarkedProjects
import com.marko.domain.usecases.factory.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBookmarkedProjectsTest {

	private lateinit var getBookmarkedProjects: GetBookmarkedProjects
	@Mock
	lateinit var postExecutionThread: PostExecutionThread
	@Mock
	lateinit var projectsRepository: ProjectsRepository

	private fun stubGetBookmarkedProjects(observable: Observable<List<Project>>) {
		whenever(projectsRepository.getProjects())
				.thenReturn(observable)
	}

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getBookmarkedProjects = GetBookmarkedProjects(postExecutionThread, projectsRepository)
	}

	@Test
	fun doesGetBookmarkedProjectsComplete() {
		stubGetBookmarkedProjects(Observable.just(ProjectDataFactory.makeProjects(3)))
		val observer = getBookmarkedProjects.buildObservable().test()
		observer.assertComplete()
	}

	@Test
	fun checkGetBookmarkedProjectsReturnValue() {
		val projects = ProjectDataFactory.makeProjects(3)
		stubGetBookmarkedProjects(Observable.just(projects))
		val observer = getBookmarkedProjects.buildObservable().test()
		observer.assertValue(projects)
	}
}