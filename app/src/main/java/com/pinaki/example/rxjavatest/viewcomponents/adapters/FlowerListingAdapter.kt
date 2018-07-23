package com.pinaki.example.rxjavatest.viewcomponents.adapters

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinaki.example.rxjavatest.ImageUtil
import com.pinaki.example.rxjavatest.R
import com.pinaki.example.rxjavatest.listeners.OnItemClickListener
import com.pinaki.example.rxjavatest.model.Flower
import kotlinx.android.synthetic.main.flower_list_item.view.*


class FlowerListingAdapter(var flowers: List<Flower>, private val fragment: Fragment) : RecyclerView.Adapter<FlowerListingAdapter.FlowerListingViewHolder>() {

    public var onItemClickListener: OnItemClickListener? = null
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FlowerListingViewHolder {
        val contentView = LayoutInflater.from(p0.context).inflate(R.layout.flower_list_item, p0, false)
        return FlowerListingViewHolder(contentView)
    }

    override fun getItemCount(): Int = flowers.size

    override fun onBindViewHolder(p0: FlowerListingViewHolder, p1: Int) {
        p0.setData(flowers[p1], fragment, onItemClickListener)
    }

    fun setData(flowers: List<Flower>) {
        this.flowers = flowers
        notifyDataSetChanged()
    }

    class FlowerListingViewHolder(private val contentView: View) : RecyclerView.ViewHolder(contentView) {
        fun setData(flower: Flower, fragment: Fragment, onClickListener: OnItemClickListener?) {

            contentView.let {
                it.tv_title.text = flower.name
                it.tv_subtitle.text = "$ ${flower.price}"

                ImageUtil.loadImage(flower.imgUrl, it.two_line_image, fragment, R.drawable.circle_gray)

                it.setOnClickListener {
                    onClickListener?.onItemClick(adapterPosition)
                }
            }
        }
    }
}