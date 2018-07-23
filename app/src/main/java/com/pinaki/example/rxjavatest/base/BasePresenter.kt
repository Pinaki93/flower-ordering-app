package com.pinaki.example.rxjavatest.base

public interface BasePresenter<V : BaseView> {

    fun getView(): V

    fun bindView(view: V)

    fun unbindView()

    fun init()

    fun refresh()
}