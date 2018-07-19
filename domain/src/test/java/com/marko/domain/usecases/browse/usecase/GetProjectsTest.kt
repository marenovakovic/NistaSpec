package com.marko.domain.usecases.browse.usecase

import com.marko.domain.entities.Project
import com.marko.domain.executor.PostExecutionThread
import com.marko.domain.repository.ProjectsRepository
import com.marko.domain.usecases.browse.GetProjects
import com.marko.domain.usecases.factory.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {

	private lateinit var getProjects: GetProjects
	@Mock
	lateinit var postExecutionThread: PostExecutionThread
	@Mock
	lateinit var projectsRepository: ProjectsRepository

	private fun stubGetProjects(observable: Observable<List<Project>>) {
		whenever(projectsRepository.getProjects())
				.thenReturn(observable)
	}

	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		getProjects = GetProjects(postExecutionThread, projectsRepository)
	}

	@Test
	fun doesGetProjectsComplete() {
		stubGetProjects(Observable.just(ProjectDataFactory.makeProjects(3)))
		val observer = getProjects.buildObservable().test()
		observer.assertComplete()
	}

	@Test
	fun checkGetProjectsValue() {
		val projects = ProjectDataFactory.makeProjects(3)
		stubGetProjects(Observable.just(projects))
		val observer = getProjects.buildObservable().test()
		observer.assertValue(projects)
	}
}