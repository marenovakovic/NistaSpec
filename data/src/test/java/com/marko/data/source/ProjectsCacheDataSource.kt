package com.marko.data.source

import com.marko.data.datasource.ProjectsCacheDataSource
import com.marko.data.datasource.ProjectsCacheSource
import com.marko.data.entities.ProjectEntity
import com.marko.data.factory.DataFactory
import com.marko.data.factory.ProjectFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test

class ProjectsCacheDataSource {

	private val cache = mock<ProjectsCacheDataSource>()
	private val source = ProjectsCacheSource(cache)

	@Test
	fun testGetProjectsComplete() {
		stubGetProjects(Observable.just(listOf(ProjectFactory.projectEntity)))

		source.getProjects().test().assertComplete()
	}

	@Test
	fun testGetProjectsReturnedData() {
		val entities = listOf(ProjectFactory.projectEntity)

		stubGetProjects(Observable.just(entities))

		source.getProjects().test().assertValue(entities)
	}

	@Test
	fun testIsCorrectFunctionCalledToGetProjects() {
		stubGetProjects(Observable.just(listOf(ProjectFactory.projectEntity)))

		source.getProjects().test()
		verify(cache).getProjects()
	}

	@Test
	fun testGetBookmarkedProjectsComplete() {
		stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.projectEntity)))

		source.getBookmarkedProjects().test().assertComplete()
	}

	@Test
	fun testGetBookmarkedProjectsReturnedData() {
		val entities = listOf(ProjectFactory.projectEntity)

		stubGetBookmarkedProjects(Observable.just(entities))

		source.getBookmarkedProjects().test().assertValue(entities)
	}

	@Test
	fun testIsCorrectFunctionCalledToGetBookmarkedProjects() {
		stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.projectEntity)))

		source.getBookmarkedProjects().test()
		verify(cache).getBookmarkedProjects()
	}

	@Test
	fun testBookmarkProject() {
		stubBookmarkProject(Completable.complete())

		source.bookmarkProject(DataFactory.randomString).test().assertComplete()
	}

	@Test
	fun testIsCorrectFunctionCalledToBookmarkProject() {
		stubBookmarkProject(Completable.complete())

		source.bookmarkProject(DataFactory.randomString).test()
		verify(cache).bookmarkProject(DataFactory.randomString)
	}

	@Test
	fun testSaveProjectsCompletes() {
		stubSaveProjects(Completable.complete())
		stubSaveCachedTime(Completable.complete())

		source.saveProjects(listOf()).test().assertComplete()
	}

	@Test
	fun testIsCorrectFunctionCalledToSaveProjects() {
		stubSaveProjects(Completable.complete())
		stubSaveCachedTime(Completable.complete())

		source.saveProjects(listOf()).test()
		verify(cache).saveProjects(listOf())
	}

	@Test
	fun testClearProjectsCompletes() {
		stubClearProjects(Completable.complete())

		source.clearProjects().test().assertComplete()
	}

	@Test
	fun testIsCorrectFunctionCalledToClearProjects() {
		stubClearProjects(Completable.complete())

		source.clearProjects().test()
		verify(cache).clearProjects()
	}

	@Test
	fun testUnBookmarkProjectCompletes() {
		stubUnBookmarkProject(Completable.complete())

		source.unBookmarkProject(DataFactory.randomString).test().assertComplete()
	}

	@Test
	fun testIsCorrectFunctionCalledToUnBookmarkProject() {
		stubUnBookmarkProject(Completable.complete())
		val projectId = DataFactory.randomString

		source.unBookmarkProject(projectId).test()
		verify(cache).unBookmarkProject(projectId)
	}

	private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
		whenever(cache.getProjects())
				.thenReturn(observable)
	}

	private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
		whenever(cache.getBookmarkedProjects())
				.thenReturn(observable)
	}

	private fun stubBookmarkProject(completable: Completable) {
		whenever(cache.bookmarkProject(DataFactory.randomString))
				.thenReturn(completable)
	}

	private fun stubUnBookmarkProject(completable: Completable) {
		whenever(cache.unBookmarkProject(any()))
				.thenReturn(completable)
	}

	private fun stubSaveProjects(completable: Completable) {
		whenever(cache.saveProjects(any()))
				.thenReturn(completable)
	}

	private fun stubClearProjects(completable: Completable) {
		whenever(cache.clearProjects())
				.thenReturn(completable)
	}

	private fun stubSaveCachedTime(completable: Completable) {
		whenever(cache.setLastCacheTime(any()))
				.thenReturn(completable)
	}
}