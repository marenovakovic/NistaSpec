package com.marko.data.repository

import com.marko.data.datasource.ProjectsCacheDataSource
import com.marko.data.datasource.ProjectsDataSource
import com.marko.data.datasource.ProjectsDataSourceFactory
import com.marko.data.entities.ProjectEntity
import com.marko.data.factory.DataFactory
import com.marko.data.factory.ProjectFactory
import com.marko.data.mapper.ProjectMapper
import com.marko.domain.entities.Project
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ProjectRepositoryImplTest {

	private val mapper = mock<ProjectMapper>()
	private val projectsCacheDataSource = mock<ProjectsCacheDataSource>()
	private val factory = mock<ProjectsDataSourceFactory>()
	private val source = mock<ProjectsDataSource>()
	private val repository = ProjectsRepositoryImpl(mapper, projectsCacheDataSource, factory)

	@Before
	fun setUp() {
		stubFactory()
		stubCacheDataSource()
		stubIsCacheExpired(Single.just(false))
		stubAreProjectsCached(Single.just(false))
		stubSaveProjects(Completable.complete())
	}

	@Test
	fun getProjectsComplete() {
		stubGetProjects(Observable.just(listOf(ProjectFactory.projectEntity)))
		stubMapper(ProjectFactory.project, any())

		repository.getProjects().test().assertComplete()
	}

	@Test
	fun testGetProjectsReturnedData() {
		val projectEntity = ProjectFactory.projectEntity
		val project = ProjectFactory.project

		stubGetProjects(Observable.just(listOf(projectEntity)))
		stubMapperList(listOf(project), listOf(projectEntity))

		repository.getProjects().test().assertValue(listOf(project))
	}

	@Test
	fun getBookmarkedProjectsComplete() {
		stubGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.projectEntity)))
		stubMapper(ProjectFactory.project, any())

		repository.getBookmarkedProjects().test().assertComplete()
	}

	@Test
	fun testGetBookmarkedProjectsReturnedData() {
		val projectEntity = ProjectFactory.projectEntity
		val project = ProjectFactory.project

		stubGetBookmarkedProjects(Observable.just(listOf(projectEntity)))
		stubMapperList(listOf(project), listOf(projectEntity))

		repository.getBookmarkedProjects().test().assertValue(listOf(project))
	}

	@Test
	fun bookmarkProjectCompletes() {
		stubBookmarkProject(Completable.complete())

		repository.bookmarkProject(DataFactory.randomString).test().assertComplete()
	}

	@Test
	fun unBookmarkProjectCompletes() {
		stubUnBookmarkProject(Completable.complete())

		repository.unBookmarkProject(DataFactory.randomString).test().assertComplete()
	}

	private fun stubFactory() {
		whenever(factory.getDataSource(any(), any()))
				.thenReturn(source)
	}

	private fun stubCacheDataSource() {
		whenever(factory.getCacheDataSource())
				.thenReturn(source)
	}

	private fun stubSaveProjects(completable: Completable) {
		whenever(source.saveProjects(any()))
				.thenReturn(completable)
	}

	private fun stubMapper(domainModel: Project, entity: ProjectEntity) {
		whenever(mapper.mapFromEntity(entity))
				.thenReturn(domainModel)
	}

	private fun stubMapperList(domainModels: List<Project>, entities: List<ProjectEntity>) {
		whenever(mapper.mapFromEntity(entities))
				.thenReturn(domainModels)
	}

	private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
		whenever(source.getProjects())
				.thenReturn(observable)
	}

	private fun stubGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
		whenever(source.getBookmarkedProjects())
				.thenReturn(observable)
	}

	private fun stubIsCacheExpired(single: Single<Boolean>) {
		whenever(projectsCacheDataSource.isCacheExpired())
				.thenReturn(single)
	}

	private fun stubAreProjectsCached(single: Single<Boolean>) {
		whenever(projectsCacheDataSource.areProjectsCached())
				.thenReturn(single)
	}

	private fun stubBookmarkProject(completable: Completable) {
		whenever(source.bookmarkProject(any()))
				.thenReturn(completable)
	}

	private fun stubUnBookmarkProject(completable: Completable) {
		whenever(source.unBookmarkProject(any()))
				.thenReturn(completable)
	}
}