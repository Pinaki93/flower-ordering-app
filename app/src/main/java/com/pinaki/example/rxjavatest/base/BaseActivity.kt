package com.pinaki.example.rxjavatest.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


public abstract class BaseActivity<V : BaseView, P : BasePresenterImpl<V>> : AppCompatActivity(), BaseView {

    private var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instantiateView()

        instantiatePresenter()
        presenter?.bindView(this as V) // should throw the class cast exception if the view interface is not implemented
        presenter?.init()
    }

    /**
     * set the content view,
     * initialize the view objects if required
     */
    abstract fun instantiateView()

    /**
     * Instantiate the presenter with whatever parameters are required
     */
    abstract fun instantiatePresenter()

    override fun onResume() {
        super.onResume()
        presenter?.refresh()
    }
}