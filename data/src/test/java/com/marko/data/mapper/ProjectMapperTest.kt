package com.marko.data.mapper

import com.marko.data.entities.ProjectEntity
import com.marko.data.factory.ProjectFactory
import com.marko.domain.entities.Project
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProjectMapperTest {

	private val projectMapper= mock<ProjectMapper>()

	@Test
	fun testMapToEntity() {
		val domainModel = ProjectFactory.project
		val entity = projectMapper.mapToEntity(domainModel)

		assertEqualData(entity, domainModel)
	}

	@Test
	fun testMapFromEntity() {
		val entity = ProjectFactory.projectEntity
		val domainModel = projectMapper.mapFromEntity(entity)

		assertEqualData(entity, domainModel)
	}

	private fun assertEqualData(entity: ProjectEntity,
								domainModel: Project) {
		assertEquals(entity.id, domainModel.id)
		assertEquals(entity.name, domainModel.name)
		assertEquals(entity.fullName, domainModel.fullName)
		assertEquals(entity.starCount, domainModel.starCount)
		assertEquals(entity.dateCreated, domainModel.dateCreated)
		assertEquals(entity.ownerName, domainModel.ownerName)
		assertEquals(entity.ownerAvatar, domainModel.ownerAvatar)
//		assertEquals(entity.isBookmarked, domainModel.isBookmarked)
	}
}