package com.pinaki.example.rxjavatest.bottomsheets

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pinaki.example.rxjavatest.ImageUtil
import com.pinaki.example.rxjavatest.R
import com.pinaki.example.rxjavatest.model.Flower
import com.pinaki.example.rxjavatest.viewcomponents.buttons.QuantityChooserView
import kotlinx.android.synthetic.main.bottom_sheet_flower_description.*

/**
 * Created by pinaki93 on 11/07/18.
 */
class FlowerDescriptionBottomSheet : BottomSheetDialogFragment(), QuantityChooserView.OnQuantityChangeListener {
    private var model: Flower? = null
    private var quantity: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = arguments?.getParcelable(ARG_MODEL)
        quantity = arguments?.getInt(ARG_QUANTITY)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.bottom_sheet_flower_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (model != null) {
            ImageUtil.loadImage(model?.imgUrl!!, iv_flower, activity!!, R.drawable.circle_gray)
            tv_title.text = model?.name
            tv_subtitle.text = "$ ${model?.price}"
            tv_category.text = model?.category
            tv_instructions.text = model?.instructions
        }

        quantity_picker.setOnQuantityChangeListener(this)
    }

    override fun getTheme() = R.style.BottomSheetDialogTheme

    override fun onIncrement(newQuantity: Int) {
        if (!btn_add_to_cart.isEnabled)
            btn_add_to_cart.isEnabled = true
    }

    override fun onDecrement(newQuantity: Int) {
        if (newQuantity == 0)
            btn_add_to_cart.isEnabled = false
    }

    companion object {
        @JvmField
        val ARG_MODEL = "model"

        @JvmField
        val ARG_QUANTITY = "quantity"

        @JvmField
        val TARGET_FRAGMENT_REQ = 1000

        @JvmField
        val TAG = FlowerDescriptionBottomSheet::class.java.simpleName

        fun show(fm: FragmentManager, targetFrag: Fragment, flower: Flower, quantity: Int = 0): FlowerDescriptionBottomSheet {
            val instance = FlowerDescriptionBottomSheet()
            instance.setTargetFragment(targetFrag, TARGET_FRAGMENT_REQ)

            val args = Bundle()
            args.putParcelable(ARG_MODEL, flower)
            args.putInt(ARG_QUANTITY, quantity)

            instance.arguments = args

            instance.show(fm, TAG)
            return instance
        }
    }
}