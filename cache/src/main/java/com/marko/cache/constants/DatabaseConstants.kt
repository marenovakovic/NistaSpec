package com.marko.cache.constants

object DatabaseConstants {

	const val TABLE_NAME = "projects"

	const val DATABASE_NAME = "projects.db"
	const val DATABASE_VERSION = 1

	// Columns
	const val IS_BOOKMARKED_COLUMN = "is_bookmarked"
	const val ID_COLUMN = "project_id"

	const val QUERY_ALL_PROJECTS = "SELECT * FROM $TABLE_NAME"
	const val DELETE_PROJECTS = "DELETE * FROM $TABLE_NAME"
	const val QUERY_BOOKMARKED_PROJECTS = "SELECT * FROM $TABLE_NAME WHERE $IS_BOOKMARKED_COLUMN = 1"
	const val BOOKMARK_PROJECT = "UPDATE $TABLE_NAME SET $IS_BOOKMARKED_COLUMN = :isBookmarked WHERE" +
			"$ID_COLUMN = :projectId"
}