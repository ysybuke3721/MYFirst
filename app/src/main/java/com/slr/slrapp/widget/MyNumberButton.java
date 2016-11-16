
package com.slr.slrapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.utils.LogUtils;

/**
 * 购物车商品数量、增加和减少控制按钮。
 */
public class MyNumberButton extends LinearLayout implements View.OnClickListener {
    //库存
    private int mInventory = Integer.MAX_VALUE / 2;
    //最大购买数，默认无限制
    private int mBuyMax = Integer.MAX_VALUE / 2;
    OnNumChangeListener onNumChangeListener;
    private EditText mCount;
    private OnWarnListener mOnWarnListener;

    public MyNumberButton(Context context) {
        this(context, null);
    }

    public MyNumberButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
//
//    public NumberButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }


    /**
     * 设置EditText文本变化监听
     *
     * @param onNumChangeListener
     */
    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener) {
        this.onNumChangeListener = onNumChangeListener;
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout, this);

        TextView addButton = (TextView) findViewById(R.id.button_add);
        addButton.setOnClickListener(this);
        TextView subButton = (TextView) findViewById(R.id.button_sub);
        subButton.setOnClickListener(this);

        mCount = ((EditText) findViewById(R.id.text_count));
        //限制输入的字数
        // mCount.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        //mCount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

        mCount.addTextChangedListener(new MyWatcher());
        mCount.setOnClickListener(this);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNumberButton);
        boolean editable = typedArray.getBoolean(R.styleable.MyNumberButton_editable, true);
        int buttonWidth = typedArray.getDimensionPixelSize(R.styleable.MyNumberButton_buttonWidth, -1);
        int textWidth = typedArray.getDimensionPixelSize(R.styleable.MyNumberButton_textWidth, -1);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.MyNumberButton_textSize, -1);
        int textColor = typedArray.getColor(R.styleable.MyNumberButton_textColor, 0xff000000);
        typedArray.recycle();

        setEditable(editable);
        mCount.setTextColor(textColor);
        subButton.setTextColor(textColor);
        addButton.setTextColor(textColor);

        if (textSize > 0)
            mCount.setTextSize(textSize);

        if (buttonWidth > 0) {
            LayoutParams textParams = new LayoutParams(buttonWidth, LayoutParams.MATCH_PARENT);
            subButton.setLayoutParams(textParams);
            addButton.setLayoutParams(textParams);
        }
        if (textWidth > 0) {
            LayoutParams textParams = new LayoutParams(textWidth, LayoutParams.MATCH_PARENT);
            mCount.setLayoutParams(textParams);
        }
    }

    public int getNumber() {
        try {
            return Integer.parseInt(String.valueOf(Integer.valueOf(mCount.getText().toString())));
        } catch (NumberFormatException e) {
        }
        mCount.setText("1");
        return 1;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int count = getNumber();
        if (id == R.id.button_sub) {
            if (count > 1) {
                if (onNumChangeListener != null) {
                    onNumChangeListener.onNumChange(MyNumberButton.this, count - 1);
                }

                //正常减
                mCount.setText(String.valueOf(count - 1));
                //设置EditText光标位置 为文本末端
                mCount.setSelection(String.valueOf(count).length());

            }

        } else if (id == R.id.button_add) {
            if (count < Math.min(mBuyMax, mInventory)) {
                if (onNumChangeListener != null) {
                    onNumChangeListener.onNumChange(MyNumberButton.this, count + 1);
                }

                //正常添加
                mCount.setText(String.valueOf(count + 1));
                //设置EditText光标位置 为文本末端
                mCount.setSelection(String.valueOf(count).length());

            } else if (mInventory < mBuyMax) {
                //库存不足
                warningForInventory();
            } else {
                //超过最大购买数
                warningForBuyMax();
            }

        } else if (id == R.id.text_count) {
            mCount.setSelection(mCount.getText().toString().length());
        }
    }

    private void onNumberInput() {
        //当前数量
        int count = getNumber();
        if (count <= 0) {
            //手动输入
            mCount.setText("1");
            return;
        }

        int limit = Math.min(mBuyMax, mInventory);
        if (count > limit) {
            //超过了数量
            mCount.setText(limit + "");
            if (mInventory < mBuyMax) {
                //库存不足
                warningForInventory();
            } else {
                //超过最大购买数
                warningForBuyMax();
            }
        }

    }

    /**
     * 超过的库存限制
     * Warning for inventory.
     */
    private void warningForInventory() {
        if (mOnWarnListener != null) mOnWarnListener.onWarningForInventory(mInventory);
    }

    /**
     * 超过的最大购买数限制
     * Warning for buy max.
     */
    private void warningForBuyMax() {
        if (mOnWarnListener != null) mOnWarnListener.onWarningForBuyMax(mBuyMax);
    }


    private void setEditable(boolean editable) {
        if (editable) {
            mCount.setFocusable(true);
            mCount.setKeyListener(new DigitsKeyListener());
        } else {
            mCount.setFocusable(false);
            mCount.setKeyListener(null);
        }
    }

    public MyNumberButton setCurrentNumber(int currentNumber) {
        if (currentNumber < 1) mCount.setText("1");
        mCount.setText("" + Math.min(Math.min(mBuyMax, mInventory), currentNumber));
        //让光标移动到最后
        mCount.setSelection(String.valueOf(currentNumber).length());
        return this;
    }

    public int getInventory() {
        return mInventory;
    }

    public MyNumberButton setInventory(int inventory) {
        mInventory = inventory;
        return this;
    }

    public int getBuyMax() {
        return mBuyMax;
    }

    public MyNumberButton setBuyMax(int buyMax) {
        mBuyMax = buyMax;
        return this;
    }

    public MyNumberButton setOnWarnListener(OnWarnListener onWarnListener) {
        mOnWarnListener = onWarnListener;
        return this;
    }


    private class MyWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            //TODO
            int limit = Math.min(mBuyMax, mInventory);
            String temString = s.toString();
            int count = 0;
            if (temString == null || temString.equals("")) {
                count = 1;
            } else {
                int temcount = Integer.parseInt(temString);
                LogUtils.LogI("chushu", getChuShu(temString.length()) + "");
                count = temcount % (getChuShu(temString.length()));
                LogUtils.LogI("jieguo", "" + count);
                String numString = String.valueOf(count);
                if (count <= 1) {
                    count = 1;
                } else if (count > limit) {
                    //超过了数量
                    count = limit;
                    if (mInventory < mBuyMax) {
                        //库存不足
                        warningForInventory();
                    } else {
                        //超过最大购买数
                        warningForBuyMax();
                    }
                }
                LogUtils.LogI("after2", count + "");
                //设置EditText光标位置 为文本末端
                mCount.setSelection(numString.length());

            }

            if (onNumChangeListener != null) {
                onNumChangeListener.onNumChange(MyNumberButton.this, count);
            }

            LogUtils.LogI("goumai", "" + count);
            mCount.removeTextChangedListener(this);
            setCurrentNumber(count);
            mCount.addTextChangedListener(this);

        }
    }


    //根据数字的长度获取一个除数,用来消除数字前边的0
    private int getChuShu(int length) {
        String a = "1";
        for (int i = 0; i < length; i++) {
            a = a + "0";
        }
        return Integer.parseInt(a);
    }

    public interface OnWarnListener {
        void onWarningForInventory(int inventory);

        void onWarningForBuyMax(int max);
    }

    public interface OnNumChangeListener {
        /**
         * 输入框中的数值改变事件
         *
         * @param view 整个AddAndSubView
         * @param num  输入框的数值
         */
        public void onNumChange(View view, int num);
    }

}
