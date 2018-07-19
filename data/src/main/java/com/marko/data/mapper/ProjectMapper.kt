package com.marko.data.mapper

import com.marko.data.entities.ProjectEntity
import com.marko.domain.entities.Project

open class ProjectMapper: EntityMapper<ProjectEntity, Project> {
	override fun mapFromEntity(entity: ProjectEntity): Project {
		return Project(
				id = entity.id,
				name = entity.name,
				fullName = entity.fullName,
				dateCreated = entity.dateCreated,
				ownerName = entity.ownerName,
				ownerAvatar = entity.ownerAvatar,
				starCount = entity.starCount,
				isBookmarked = false
		)
	}

	override fun mapFromEntity(entities: List<ProjectEntity>): List<Project> {
		return entities.map { mapFromEntity(it) }
	}

	override fun mapToEntity(domain: Project): ProjectEntity {
		return ProjectEntity(
				id = domain.id,
				name = domain.name,
				fullName = domain.fullName,
				dateCreated = domain.dateCreated,
				ownerName = domain.ownerName,
				ownerAvatar = domain.ownerAvatar,
				starCount = domain.starCount
		)
	}

	override fun mapToEntity(domain: List<Project>): List<ProjectEntity> {
		return domain.map { mapToEntity(it) }
	}
}