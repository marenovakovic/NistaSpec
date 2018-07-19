package com.marko.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
	val scheduler: Scheduler
}