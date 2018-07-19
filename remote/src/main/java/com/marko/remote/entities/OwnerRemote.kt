package com.marko.remote.entities

import com.google.gson.annotations.SerializedName

data class OwnerRemote(
		@SerializedName("login")
		val ownerName: String,
		@SerializedName("avatar_url")
		val avatar: String
)