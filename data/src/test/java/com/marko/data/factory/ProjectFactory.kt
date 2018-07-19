package com.marko.data.factory

import com.marko.data.entities.ProjectEntity
import com.marko.domain.entities.Project

object ProjectFactory {

	val project: Project
		get() = Project(
				id = DataFactory.randomString,
				name = DataFactory.randomString,
				fullName = DataFactory.randomString,
				ownerName = DataFactory.randomString,
				ownerAvatar = DataFactory.randomString,
				starCount = DataFactory.randomString,
				dateCreated = DataFactory.randomString,
				isBookmarked = DataFactory.randomBoolean
		)

	val projectEntity: ProjectEntity
	get() = ProjectEntity(
			id = DataFactory.randomString,
			name = DataFactory.randomString,
			fullName = DataFactory.randomString,
			ownerName = DataFactory.randomString,
			ownerAvatar = DataFactory.randomString,
			starCount = DataFactory.randomString,
			dateCreated = DataFactory.randomString,
			isBookmarked = DataFactory.randomBoolean
	)
}