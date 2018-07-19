package com.marko.data.source

import com.marko.data.datasource.ProjectsRemoteDataSource
import com.marko.data.datasource.ProjectsRemoteSource
import com.marko.data.entities.ProjectEntity
import com.marko.data.factory.DataFactory
import com.marko.data.factory.ProjectFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test

class ProjectsRemoteDataSourceTest {

	private val remote = mock<ProjectsRemoteDataSource>()
	private val source = ProjectsRemoteSource(remote)

	@Test
	fun testGetProjectsCompletes() {
		stubGetProjects(Observable.just(listOf()))

		source.getProjects().test().assertComplete()
	}

	@Test
	fun testIsCorrectCunctionCalledForGetProjects() {
		stubGetProjects(Observable.just(listOf()))

		source.getProjects().test()
		verify(remote).getProjects()
	}

	@Test
	fun testGetProjectsReturnedData() {
		val projects = listOf(ProjectFactory.projectEntity)
		stubGetProjects(Observable.just(projects))

		source.getProjects().test().assertValue(projects)
	}

	@Test(expected = UnsupportedOperationException::class)
	fun testExceptionIsThrownWhenCallingBookmarkProject() {
		val projectId = DataFactory.randomString
		source.bookmarkProject(projectId).test()
	}

	@Test(expected = UnsupportedOperationException::class)
	fun testExceptionIsThrownWhenCallingSaveProjects() {
		source.saveProjects(listOf()).test()
	}

	@Test(expected = UnsupportedOperationException::class)
	fun testExceptionIsThrownWhenCallingUnBookmarkProject() {
		val projectId = DataFactory.randomString
		source.unBookmarkProject(projectId).test().assertError(UnsupportedOperationException::class.java)
	}

	@Test(expected = UnsupportedOperationException::class)
	fun testExceptionIsThrownWhenCallingClearProjects() {
		source.clearProjects().test()
	}

	@Test(expected = UnsupportedOperationException::class)
	fun testExceptionIsThrownWhenCallingGetBookmarkedkProjects() {
		source.getBookmarkedProjects().test()
	}

	private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
		whenever(remote.getProjects())
				.thenReturn(observable)
	}
}