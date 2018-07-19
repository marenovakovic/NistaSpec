package com.marko.domain.usecases.base

import com.marko.domain.executor.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> (private val postExecutionThread: PostExecutionThread) {

	private val disposables = CompositeDisposable()

	abstract fun buildCompletable(params: Params? = null): Completable

	fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
		val observable = buildCompletable(params)
				.subscribeOn(Schedulers.io())
				.observeOn(postExecutionThread.scheduler)
		disposables.add(observable.subscribeWith(observer))
	}

	fun dispose() = disposables.dispose()
}