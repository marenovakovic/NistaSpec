package com.marko.remote.mapper

import com.marko.data.entities.ProjectEntity
import com.marko.remote.entities.ProjectRemote

open class ProjectRemoteMapper: RemoteEntityMapper<ProjectRemote, ProjectEntity> {
	override fun mapToData(remoteEntity: ProjectRemote): ProjectEntity {
		return ProjectEntity(
				id = remoteEntity.id,
				name = remoteEntity.projectName,
				fullName = remoteEntity.projectFullName,
				dateCreated = remoteEntity.dateCreated,
				starCount = remoteEntity.starCount.toString(),
				ownerName = remoteEntity.owner.ownerName,
				ownerAvatar = remoteEntity.owner.avatar
		)
	}

	override fun mapToData(remoteEntities: List<ProjectRemote>): List<ProjectEntity> {
		return remoteEntities.map { mapToData(it) }
	}
}