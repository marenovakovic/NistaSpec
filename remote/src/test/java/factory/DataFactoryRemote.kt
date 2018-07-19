package factory

import java.util.*

object DataFactoryRemote {

	val string: String
	get() = UUID.randomUUID().toString()

	val long: Long
	get() = Math.random().toLong()

	val boolean: Boolean
	get() = Math.random() < 0.5
}