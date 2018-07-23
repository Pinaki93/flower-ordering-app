package com.pinaki.example.rxjavatest.bottomsheets

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinaki.example.rxjavatest.R
import com.pinaki.example.rxjavatest.viewcomponents.adapters.CategoryListingAdapter
import kotlinx.android.synthetic.main.bottom_sheet_category_listing.*

public class CategoryListingBottomSheet : BottomSheetDialogFragment() {

    private var list: ArrayList<CategoryListingModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = arguments?.getParcelableArrayList<CategoryListingModel>(ARG_CATEGORY_LIST)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.bottom_sheet_category_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category_listing_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        if (list != null)
            category_listing_recycler_view.adapter = CategoryListingAdapter(list!!)
        else
            dismissAllowingStateLoss()
    }

    override fun getTheme() = R.style.BottomSheetDialogTheme

    companion object {

        @JvmField
        val TAG = CategoryListingBottomSheet::class.java.simpleName

        private const val ARG_CATEGORY_LIST = "CategoryList"

        fun show(fragmentManager: FragmentManager, fragment: Fragment, list: ArrayList<CategoryListingModel>) {
            val args = Bundle()
            args.putParcelableArrayList(ARG_CATEGORY_LIST, list)

            val instance = CategoryListingBottomSheet()
            instance.arguments = args

            instance.show(fragmentManager, TAG)
        }
    }
}


/**
 * ViewModel for Category Listing
 */
data class CategoryListingModel(val catname: String, val count: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(catname)
        parcel.writeInt(count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryListingModel> {
        override fun createFromParcel(parcel: Parcel): CategoryListingModel {
            return CategoryListingModel(parcel)
        }

        override fun newArray(size: Int): Array<CategoryListingModel?> {
            return arrayOfNulls(size)
        }
    }

}
