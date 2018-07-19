package com.marko.cache.mapper

import com.marko.cache.entities.CachedProject
import com.marko.data.entities.ProjectEntity

class CacheProjectMapper: CacheMapper<CachedProject, ProjectEntity> {
	override fun mapFromCache(cacheModel: CachedProject): ProjectEntity {
		return ProjectEntity(
				id = cacheModel.id,
				name = cacheModel.name,
				fullName = cacheModel.fullName,
				ownerName = cacheModel.ownerName,
				ownerAvatar = cacheModel.ownerAvatar,
				dateCreated = cacheModel.dateCreated,
				starCount = cacheModel.starCount
		)
	}

	override fun mapFromCache(cacheModels: List<CachedProject>): List<ProjectEntity> {
		return cacheModels.map { mapFromCache(it) }
	}

	override fun mapToCache(entityModel: ProjectEntity): CachedProject {
		return CachedProject(
				id = entityModel.id,
				name = entityModel.name,
				fullName = entityModel.fullName,
				ownerName = entityModel.ownerName,
				ownerAvatar = entityModel.ownerAvatar,
				dateCreated = entityModel.dateCreated,
				starCount = entityModel.starCount,
				isBookmarked = false
		)
	}

	override fun mapToCache(entityModels: List<ProjectEntity>): List<CachedProject> {
		return entityModels.map { mapToCache(it) }
	}
}