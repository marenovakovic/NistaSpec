package source

import com.marko.data.entities.ProjectEntity
import com.marko.remote.entities.ApiResponse
import com.marko.remote.mapper.ProjectRemoteMapper
import com.marko.remote.networking.GitHubService
import com.marko.remote.source.RemoteDataSourceImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import factory.DataFactoryRemote
import factory.ProjectFactoryRemote
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

class RemoteSourceTest {

	private val service = mock<GitHubService>()
	private val mapper = ProjectRemoteMapper()
//	private val loggingInterceptor = mock<HttpLoggingInterceptor>()
//	private val okHttpClient = mock<OkHttpClient>()
	private val source = RemoteDataSourceImpl(service, mapper)

	@Test
	fun getProjectsCompletes() {
		val projectEntities = listOf(ProjectFactoryRemote.projectEntity)
		stubGetProjects(Observable.just(projectEntities))

		source.getProjects().test().assertComplete()
	}

	@Test
	fun testServiceSourceGetProjectsCompletes() {
		val apiResponse = ProjectFactoryRemote.apiResponse
		stubSourceGetProjects(Observable.just(apiResponse))

		service.searchRepositories(
				DataFactoryRemote.string,
				DataFactoryRemote.string,
				DataFactoryRemote.string
		).test().assertComplete()
	}

	private fun stubGetProjects(observable: Observable<List<ProjectEntity>>) {
		whenever(source.getProjects())
				.thenReturn(observable)
	}

	private fun stubSourceGetProjects(observable: Observable<ApiResponse>) {
		whenever(service.searchRepositories(any(), any(), any()))
				.thenReturn(observable)
	}
}