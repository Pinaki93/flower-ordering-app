package com.pinaki.example.rxjavatest.presenters

import com.pinaki.example.rxjavatest.base.BasePresenterImpl
import com.pinaki.example.rxjavatest.bottomsheets.CategoryListingModel
import com.pinaki.example.rxjavatest.data.FlowerRepository
import com.pinaki.example.rxjavatest.getImageUrl
import com.pinaki.example.rxjavatest.model.Flower
import com.pinaki.example.rxjavatest.view.FlowerListingView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by pinaki93 on 05/07/18.
 */
class FlowerListingPresenter : BasePresenterImpl<FlowerListingView>() {

    private val flowerRepository = FlowerRepository()
    private val TAG = "FlowerListingPresenter"
    private var list: List<Flower>? = null

    override fun init() {
        if (!getView().isOnline()) {
            getView().showNoInternetView()
            return
        }

        getView().showLoader(true)
        val d = flowerRepository.getFlowers()
                .subscribeOn(Schedulers.io())
                .flatMapIterable {
                    return@flatMapIterable it
                }
                .map {
                    // converting API model
                    return@map Flower(it.category, it.price, it.instructions, it.photo, it.name, it.productId, getImageUrl(it.photo))
                }
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.list = it
                    getView().showLoader(false)

                    if (it == null || it.isEmpty()) {
                        getView().showEmptyView()
                    } else {
                        getView().loadFlowers(it)
                    }
                }, {
                    Timber.tag(TAG).e(it)
                    getView().showEmptyView()
                })

        addDisposables(d)
    }

    override fun refresh() {

    }

    fun onFilterClick() {
        val categoryList = getSortedCategoryList()

        if (!categoryList.isEmpty())
            getView().showCategoryChooser(categoryList)
    }

    private fun getSortedCategoryList(): ArrayList<CategoryListingModel> {
        val catMap = HashMap<String, Int>()

        val categoryList = ArrayList<CategoryListingModel>()
        if (list != null) {

            // run an iteration on list and
            // select unique categories
            list?.forEach({
                val category = it.category

                if (catMap.containsKey(category)) {
                    var count = catMap[category]!!
                    catMap[category] = ++count
                    return@forEach
                }

                catMap[category] = 1
            })

            // make a list out of unique categories with their counts
            catMap.keys.forEach {
                categoryList.add(CategoryListingModel(it, catMap[it]!!))
            }

            // sort the list
            categoryList.sortWith(compareBy {
                it.catname
            })

        }
        return categoryList
    }
}