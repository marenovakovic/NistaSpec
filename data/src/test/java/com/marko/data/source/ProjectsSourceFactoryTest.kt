package com.marko.data.source

import com.marko.data.datasource.*
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectsSourceFactoryTest {

	private val cacheDataSource = mock<ProjectsCacheSource>()
	private val remoteDataSource = mock<ProjectsRemoteSource>()
	private val factory = ProjectsDataSourceFactory(remoteDataSource, cacheDataSource)

	@Test
	fun getDataSourceReturnsRemoteDataSourceWhenCacheIsExpired() {
		assertEquals(remoteDataSource, factory.getDataSource(true, true))
	}

	@Test
	fun getDataSourceReturnsRemoteDataSourceWhenCacheIsNotExpired() {
		assertEquals(remoteDataSource, factory.getDataSource(false, false))
	}

	@Test
	fun getDataSourceReturnsRemoteDataSourceWhenProjectsAreNotCached() {
		assertEquals(remoteDataSource, factory.getDataSource(false, true))
	}

	@Test
	fun getDataSourceReturnsCacheDataSourceWhenProjectsAreCached() {
		assertEquals(cacheDataSource, factory.getDataSource(true, false))
	}

	@Test
	fun getDataSourceReturnsRemoteDataSourceWhenProjectsAreCachedAndCacheIsExpired() {
		assertEquals(remoteDataSource, factory.getDataSource(true, true))
	}

	@Test
	fun testCacheDataSourceIsReturned() {
		assertEquals(cacheDataSource, factory.getCacheDataSource())
	}
}