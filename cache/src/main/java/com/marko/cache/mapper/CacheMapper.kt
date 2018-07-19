package com.marko.cache.mapper

interface CacheMapper<C, E> {

	fun mapFromCache(cacheModel: C): E
	fun mapFromCache(cacheModels: List<C>): List<E>

	fun mapToCache(entityModel: E): C
	fun mapToCache(entityModels: List<E>): List<C>
}