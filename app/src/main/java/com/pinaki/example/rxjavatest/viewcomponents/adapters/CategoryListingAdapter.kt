package com.pinaki.example.rxjavatest.viewcomponents.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinaki.example.rxjavatest.R
import com.pinaki.example.rxjavatest.bottomsheets.CategoryListingModel
import kotlinx.android.synthetic.main.category_list_item.view.*

public class CategoryListingAdapter(private var list: ArrayList<CategoryListingModel>) : RecyclerView.Adapter<CategoryListingAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false))
    }

    override fun getItemCount() = list.count()

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, pos: Int) {
        viewHolder.bind(list[pos])
    }

    class CategoryViewHolder(private val contentView: View) : RecyclerView.ViewHolder(contentView) {
        fun bind(model: CategoryListingModel) {
            contentView.tv_title.text = model.catname
            contentView.tv_count.text = model.count.toString()
        }
    }
}