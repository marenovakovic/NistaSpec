package com.marko.domain.usecases.base

import com.marko.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> (private val postExecutionThread: PostExecutionThread) {

	private val disposables = CompositeDisposable()

	abstract fun buildObservable(params: Params? = null): Observable<T>

	fun execute(observer: DisposableObserver<T>, params: Params? = null) {
		val observable = this.buildObservable(params)
				.subscribeOn(Schedulers.io())
				.observeOn(postExecutionThread.scheduler)
		disposables.add(observable.subscribeWith(observer))
	}

	fun dispose() = disposables.dispose()
}