package com.slr.slrapp.fragments;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.UiUtils;

/**
 *
 *这个是Fragment的基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    public static   SharedPreferences sharedPreferences;
    public  static  int screenWidth;
    public static  int screnHeight;
    private WindowManager windowManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutResID(), null);
        sharedPreferences= UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME,0);

        windowManager= (WindowManager) UiUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth=windowManager.getDefaultDisplay().getWidth();
        screnHeight=windowManager.getDefaultDisplay().getHeight();
        initView(view);
        initListener();
        initData();
        return view;
    }

    /**
     * 获取当前布局id
     * @return
     */
    public abstract int getLayoutResID();

    /**
     * 初始化View
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 初始化监听,为ListView,GridView等设置适配器
     */
    public abstract void initListener();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 为控件设置点击事件
     * @param v
     */
    public void onClick(View v){
    }


}
