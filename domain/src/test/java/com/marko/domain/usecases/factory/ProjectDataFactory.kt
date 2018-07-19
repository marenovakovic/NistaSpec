package com.marko.domain.usecases.factory

import com.marko.domain.entities.Project
import java.util.*

object ProjectDataFactory {

	val randomString: String
	get() = UUID.randomUUID().toString()

	val randomBoolean: Boolean
	get() = Math.random() < 0.5

	val project: Project
	get() = Project(
			id = randomString,
			name = randomString,
			fullName = randomString,
			ownerName = randomString,
			ownerAvatar = randomString,
			starCount = randomString,
			dateCreated = randomString,
			isBookmarked = randomBoolean
	)

	fun makeProjects(howMuch: Int): List<Project> {
		val projects = mutableListOf<Project>()
		repeat(howMuch) {
			projects.add(project)
		}

		return projects
	}
}