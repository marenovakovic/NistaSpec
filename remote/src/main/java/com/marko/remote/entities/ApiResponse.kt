package com.marko.remote.entities

import com.google.gson.annotations.SerializedName

data class ApiResponse(
		@SerializedName("items")
		val projects: List<ProjectRemote>
)