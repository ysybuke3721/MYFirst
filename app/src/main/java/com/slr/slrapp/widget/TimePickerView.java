package com.slr.slrapp.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.slr.slrapp.R;
import com.slr.slrapp.utils.ToastUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 */
public class TimePickerView extends BasePickerView implements View.OnClickListener {
    public enum Type {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
    }// 四种选择模式，年月日时分，年月日，时分，月日时分

    WheelTime wheelTime;
    private View btnSubmit, btnCancel;
    private TextView tvTitle;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnTimeSelectListener timeSelectListener;

    public TimePickerView(Context context, Type type) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.pickerview_time, contentContainer);
        // -----确定和取消按钮
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //顶部标题
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        // ----时间转轮
        final View timepickerview = findViewById(R.id.timepicker);
        wheelTime = new WheelTime(timepickerview, type);

        //默认选中当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
        wheelTime.setPick(year, month, day, hours);


    }

    /**
     * 设置可以选择的时间范围
     * 要在setTime之前调用才有效果
     *
     * @param startYear 开始年份
     * @param endYear   结束年份
     */
    public void setRange(int startYear, int endYear) {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);
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
        wheelTime.setPick(year, month, day, hours);
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
        wheelTime.setCyclic(cyclic);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (timeSelectListener != null) {

//                    Date date = WheelTime.dateFormat.parse(wheelTime.getTime());

//                    timeSelectListener.onTimeSelect(date);
                String time = wheelTime.getTime();
                String date = wheelTime.getTime().substring(0, 10);
                Calendar calendar = Calendar.getInstance();
                String[] array1 = time.split(" ");
                String ap = array1[1];
                String[] array2 = array1[0].split("-");
                int year = Integer.parseInt(array2[0]);
                int month = Integer.parseInt(array2[1]);
                int day = Integer.parseInt(array2[2]);


                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH)+1;
                int nowDay = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (year == 2016&&month == 9){
                    if (day < 7){
                        ToastUtil.showTextToast("目前无货，请您选择7号以后！");
                        return;
                    }
                }
                if (year < nowYear) {
                    ToastUtil.showTextToast("不能早于今年");
                    return;
                } else if (year == nowYear) {
                    if (month < nowMonth) {
                        ToastUtil.showTextToast("不能早于当前月份");
                        return;
                    } else if (month == nowMonth) {
                        if (day < nowDay) {
                            ToastUtil.showTextToast("不能早于当前时间");
                            return;
                        } else if (day == nowDay) {

                            if (ap.equals("上午")) {
                                if (hour > 12) {
                                    ToastUtil.showTextToast("请选择明天上午收货");
                                    return;
                                }
                            } else if (ap.equals("下午")) {
                                if (hour > 12) {
                                    ToastUtil.showTextToast("请选择明天上午收货");
                                    return;
                                }
                            }
                        }
                    }
                }
                timeSelectListener.onTimeSelect(time);

            }
            dismiss();
            return;
        }
    }

    public interface OnTimeSelectListener {
        void onTimeSelect(String date);
    }

    public void setOnTimeSelectListener(OnTimeSelectListener timeSelectListener) {
        this.timeSelectListener = timeSelectListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }
}
