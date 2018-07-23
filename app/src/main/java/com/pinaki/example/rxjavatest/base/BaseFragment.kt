package com.pinaki.example.rxjavatest.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinaki.example.rxjavatest.isOnline

public abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : Fragment(), BaseView {

    public var presenter: P? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        instantiatePresenter()
        if (presenter == null)
            throw RuntimeException("presenter not instantiated")

        presenter?.bindView(this as V)
        presenter?.init()
    }

    abstract fun getContentView(layoutInflater: LayoutInflater, container: ViewGroup?): View

    abstract fun instantiatePresenter()

    /**
     * Child fragments can override this method to initiate
     * view components like setting adapter to recycler view etc
     */
    open fun initView(){

    }
    override fun onResume() {
        super.onResume()
        presenter!!.refresh()
    }

    override fun isOnline(): Boolean {
        if (context != null)
            return isOnline(context!!)

        return false
    }
}