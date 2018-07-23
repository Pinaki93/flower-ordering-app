package com.pinaki.example.rxjavatest.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

public abstract class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

    private var view: V? = null
    private val compositeDisposable = CompositeDisposable()

    override fun getView(): V {
        if (view == null)
            throw RuntimeException("Yet to bind a view")

        return view!!
    }

    override fun bindView(view: V) {
        if (this.view != null)
            throw RuntimeException("Trying to bind a view while a view has been previously bound")

        this.view = view
    }

    override fun unbindView() {
        this.view = null

        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }

    public fun addDisposables(vararg disposables: Disposable) {
        if (!disposables.isEmpty()) {
            disposables.forEach {
                compositeDisposable.add(it)
            }
        }
    }
}