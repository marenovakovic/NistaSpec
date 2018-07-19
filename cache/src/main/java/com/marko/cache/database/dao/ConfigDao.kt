package com.marko.cache.database.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.marko.cache.constants.ConfigConstants
import com.marko.cache.entities.Config
import io.reactivex.Completable
import io.reactivex.Flowable

abstract class ConfigDao {

	@Query(ConfigConstants.QUERY_ALL_CONFIG)
	abstract fun getConfig(): Flowable<Config>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun saveConfig(config: Config): Completable
}