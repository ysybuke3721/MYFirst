package com.slr.slrapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.utils.ToastUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 * Created by admin on 2016/8/17.
 */
public class mTimePickerView extends BasePickerView implements View.OnClickListener {
    public enum mType {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
    }// 四种选择模式，年月日时分，年月日，时分，月日时分

    mWheelTime mwheelTime;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnTimeSelectListener timeSelectListener;

    public mTimePickerView(Context context, mType type) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.mpickerview_time, contentContainer);
        // -----确定和取消按钮
        btnSubmit = findViewById(R.id.mbtnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = findViewById(R.id.mbtnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) findViewById(R.id.mtvTitle);
        // ----时间转轮
        final View timepickerview = findViewById(R.id.mtimepicker);
        mwheelTime = new mWheelTime(timepickerview, type);

        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
        mwheelTime.setPick(year, month, day, hours);


    }

    /**
     * 设置可以选择的时间范围
     * 要在setTime之前调用才有效果
     *
     * @param startYear 开始年份
     * @param endYear   结束年份
     */
    public void setRange(int startYear, int endYear) {
        mwheelTime.setStartYear(startYear);
        mwheelTime.setEndYear(endYear);
    }

    /**
     * 设置选中时间
     *
     * @param date 时间
     */
    public void setTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null)
            calendar.setTimeInMillis(System.currentTimeMillis());
        else
            calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
//        wheelTime.setPicker(year, month, day, hours, minute);
        mwheelTime.setPick(year,month,day,hours);
    }


//    /**
//     * 指定选中的时间，显示选择器
//     *
//     * @param date
//     */
//    public void show(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        if (date == null)
//            calendar.setTimeInMillis(System.currentTimeMillis());
//        else
//            calendar.setTime(date);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        wheelTime.setPicker(year, month, day, hours, minute);
    //   show();
//    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        mwheelTime.setCyclic(cyclic);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (timeSelectListener != null) {
                try {

                    Date date = mWheelTime.dateFormat.parse(mwheelTime.getTime()+"24:00:00");

                    // 判断所选时间跟系统时间的前后
                    if (date.getTime() < System.currentTimeMillis()){

                        timeSelectListener.onTimeSelect(date);
                        dismiss();
                        return;
                    }else{
                        ToastUtil.showTextToast("请选择之前的时间！");
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
    }

    public interface OnTimeSelectListener {
        void onTimeSelect(Date date);
    }

    public void setOnTimeSelectListener(OnTimeSelectListener timeSelectListener) {
        this.timeSelectListener = timeSelectListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }
}