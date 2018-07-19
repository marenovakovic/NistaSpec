package com.marko.cache.entities

import android.arch.persistence.room.Entity
import com.marko.cache.constants.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(val lastCachedTime: Long)