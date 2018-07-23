package com.pinaki.example.rxjavatest.view

import com.pinaki.example.rxjavatest.base.BaseView
import com.pinaki.example.rxjavatest.bottomsheets.CategoryListingModel
import com.pinaki.example.rxjavatest.model.Flower

/**
 * Created by pinaki93 on 05/07/18.
 */
interface FlowerListingView : BaseView {
    fun showLoader(shouldLoad: Boolean)

    fun loadFlowers(list: List<Flower>)

    fun showEmptyView()

    fun showNoInternetView()

    fun showCategoryChooser(list: ArrayList<CategoryListingModel>)
}