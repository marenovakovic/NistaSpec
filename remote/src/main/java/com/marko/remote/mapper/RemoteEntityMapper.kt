package com.marko.remote.mapper

interface RemoteEntityMapper<in R, out D> {
	fun mapToData(remoteEntity: R): D
	fun mapToData(remoteEntities: List<R>): List<D>
}