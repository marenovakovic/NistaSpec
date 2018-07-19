package mapper

import com.marko.data.entities.ProjectEntity
import com.marko.remote.entities.ProjectRemote
import com.marko.remote.mapper.ProjectRemoteMapper
import factory.ProjectFactoryRemote
import org.junit.Test
import kotlin.test.assertEquals

class ProjectRemoteMapperTest {

	private val mapper = ProjectRemoteMapper()

	@Test
	fun mapToDataTest() {
		val projectRemote = ProjectFactoryRemote.projectRemote
		val projectEntity = mapper.mapToData(projectRemote)

		assertEqualValues(projectRemote, projectEntity)
	}

	@Test
	fun mapToDataListsTest() {
		val projectRemotes = listOf(ProjectFactoryRemote.projectRemote)
		val projectEntities = mapper.mapToData(projectRemotes)

		for ((i, projectEntity) in projectEntities.withIndex()) {
			assertEqualValues(projectRemotes[i], projectEntity)
		}
	}

	private fun assertEqualValues(projectRemote: ProjectRemote, projectEntity: ProjectEntity) {
		assertEquals(projectRemote.id, projectEntity.id)
		assertEquals(projectRemote.projectName, projectEntity.name)
		assertEquals(projectRemote.projectFullName, projectEntity.fullName)
		assertEquals(projectRemote.starCount.toString(), projectEntity.starCount)
		assertEquals(projectRemote.dateCreated, projectEntity.dateCreated)
		assertEquals(projectRemote.owner.ownerName, projectEntity.ownerName)
		assertEquals(projectRemote.owner.avatar, projectEntity.ownerAvatar)
	}
}