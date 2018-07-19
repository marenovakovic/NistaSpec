package com.marko.remote.entities

import com.google.gson.annotations.SerializedName

data class ProjectRemote(
		val id: String,
		@SerializedName("name")
		val projectName: String,
		@SerializedName("full_name")
		val projectFullName: String,
		val owner: OwnerRemote,
		@SerializedName("created_at")
		val dateCreated: String,
		@SerializedName("stargazer_count")
		val starCount: Long
)