package com.marko.cache.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.marko.cache.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_NAME)
data class CachedProject(
		@PrimaryKey
		@ColumnInfo(name = DatabaseConstants.ID_COLUMN)
		val id: String,
		val name: String,
		val fullName: String,
		val starCount: String,
		val dateCreated: String,
		val ownerName: String,
		val ownerAvatar: String,
		@ColumnInfo(name = DatabaseConstants.IS_BOOKMARKED_COLUMN)
		val isBookmarked: Boolean
)