package com.marko.data.factory

import java.util.*

object DataFactory {

	val randomInt: Int
	get() = Math.random().toInt()

	val randomLong: Long
	get() = randomInt.toLong()

	val randomString: String
	get() = UUID.randomUUID().toString()

	val randomBoolean: Boolean
	get() = Math.random() < 0.5
}