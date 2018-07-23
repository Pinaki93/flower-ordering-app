package com.pinaki.example.rxjavatest.fragments

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinaki.example.rxjavatest.R
import com.pinaki.example.rxjavatest.base.BaseFragment
import com.pinaki.example.rxjavatest.bottomsheets.CategoryListingBottomSheet
import com.pinaki.example.rxjavatest.bottomsheets.CategoryListingModel
import com.pinaki.example.rxjavatest.bottomsheets.FlowerDescriptionBottomSheet
import com.pinaki.example.rxjavatest.listeners.OnItemClickListener
import com.pinaki.example.rxjavatest.model.Flower
import com.pinaki.example.rxjavatest.presenters.FlowerListingPresenter
import com.pinaki.example.rxjavatest.view.FlowerListingView
import com.pinaki.example.rxjavatest.viewcomponents.adapters.FlowerListingAdapter
import kotlinx.android.synthetic.main.fragment_flower_listing.*
import java.util.*
import kotlin.collections.ArrayList

class FlowerListingFragment : BaseFragment<FlowerListingView, FlowerListingPresenter>(), FlowerListingView, OnItemClickListener, View.OnClickListener {
    private var adapter: FlowerListingAdapter? = null
    private var list: List<Flower>? = null
    override fun getContentView(layoutInflater: LayoutInflater, container: ViewGroup?): View {
        return layoutInflater.inflate(R.layout.fragment_flower_listing, container, false)
    }

    override fun initView() {
        flower_listing_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = FlowerListingAdapter(Collections.emptyList(), this)
        adapter?.onItemClickListener = this
        flower_listing_recycler_view.adapter = adapter
        iv_filter.setOnClickListener(this)
    }

    override fun instantiatePresenter() {
        presenter = FlowerListingPresenter()
    }

    override fun showLoader(shouldLoad: Boolean) {
        if (shouldLoad)
            flowerListingLoaderView.startShimmer()
        else
            flowerListingLoaderView.stopShimmer()

        flowerListingLoaderView.visibility = if (shouldLoad) View.VISIBLE else View.GONE
    }

    override fun loadFlowers(list: List<Flower>) {
        this.list = list
        adapter?.setData(list)
    }

    override fun showEmptyView() {

    }

    override fun showNoInternetView() {

    }

    override fun showCategoryChooser(list : ArrayList<CategoryListingModel>) {
        CategoryListingBottomSheet.show(activity?.supportFragmentManager!!, this, list)
    }

    override fun onItemClick(pos: Int) {
        FlowerDescriptionBottomSheet.show(activity?.supportFragmentManager!!, this, list?.get(pos)!!, 1)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.iv_filter -> presenter?.onFilterClick()
        }
    }
}