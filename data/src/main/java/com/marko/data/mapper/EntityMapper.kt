package com.marko.data.mapper

interface EntityMapper<E, D> {

	fun mapFromEntity(entity: E): D

	fun mapFromEntity(entities: List<E>): List<D>

	fun mapToEntity(domain: D): E

	fun mapToEntity(domain: List<D>): List<E>
}