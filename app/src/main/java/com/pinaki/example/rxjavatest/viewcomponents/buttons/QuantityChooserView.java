package com.pinaki.example.rxjavatest.viewcomponents.buttons;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.pinaki.example.rxjavatest.R;
import com.pinaki.example.rxjavatest.StringUtils;

/**
 * Created by pinaki93 on 11/07/18.
 */

public class QuantityChooserView extends LinearLayout implements View.OnClickListener, TextWatcher {

    public static final int THRESHOLD_QUANTITY = 0;
    private EditText etQuantity;
    private OnQuantityChangeListener onQuantityChangeListener;

    public QuantityChooserView(Context context) {
        super(context);
        init();
    }

    public QuantityChooserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.quantity_layout, this, true);

        View incrementButton = findViewById(R.id.increment_button);
        View decrementButton = findViewById(R.id.decrement_button);

        incrementButton.setOnClickListener(this);
        decrementButton.setOnClickListener(this);

        etQuantity = findViewById(R.id.et_quantity);
        etQuantity.addTextChangedListener(this);
        putCursorToEnd();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.increment_button) {
            onIncrement();
        } else if (id == R.id.decrement_button) {
            onDecrement();
        }
    }

    private void onIncrement() {
        setQuantity(getQuantity() + 1);

        if (onQuantityChangeListener != null)
            onQuantityChangeListener.onIncrement(getQuantity());

    }

    private void onDecrement() {
        if (getQuantity() == THRESHOLD_QUANTITY)
            return;

        setQuantity(getQuantity() - 1);

        if (onQuantityChangeListener != null)
            onQuantityChangeListener.onDecrement(getQuantity());
    }

    public int getQuantity() {
        return Integer.parseInt(etQuantity.getText().toString());
    }

    public void setQuantity(int quantity) {
        etQuantity.setText(String.valueOf(quantity));
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
        putCursorToEnd();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String content = editable.toString();
        if (StringUtils.isEmpty(content))
            setQuantity(THRESHOLD_QUANTITY);

        putCursorToEnd();

    }

    private void putCursorToEnd() {
        etQuantity.setSelection(etQuantity.getText().length());
    }

    public interface OnQuantityChangeListener {
        public void onIncrement(int newQuantity);

        public void onDecrement(int newQuantity);
    }
}
