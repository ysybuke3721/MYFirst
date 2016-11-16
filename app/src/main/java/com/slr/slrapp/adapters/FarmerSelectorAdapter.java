package com.slr.slrapp.adapters;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.slr.slrapp.R;
import com.slr.slrapp.interfaces.MenuAdapter;
import com.slr.slrapp.interfaces.OnFilterDoneListener;
import com.slr.slrapp.interfaces.OnFilterItemClickListener;
import com.slr.slrapp.utils.FilterUrl;
import com.slr.slrapp.utils.UIUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.DoubleGridView;
import com.slr.slrapp.widget.FilterCheckedTextView;
import com.slr.slrapp.widget.SingleListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class FarmerSelectorAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    public FarmerSelectorAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener){
        this.mContext=context;
        this.titles=titles;
        this.onFilterDoneListener=onFilterDoneListener;
    }
    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 2) {
            return 0;
        }

        return UIUtil.dp(mContext, 90);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createSingleListView();
                break;
            case 1:
                view = createSingleListView_1();
                break;
            case 2:
                view = createDoubleGrid();
                break;
        }

        return view;

    }
    //创建有两个gridview的窗口
    private View createDoubleGrid() {
        DoubleGridView doubleGridView = new DoubleGridView(mContext);
        doubleGridView.setOnFilterDoneListener(onFilterDoneListener);


        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
        }
        doubleGridView.setTopGridData(phases);

        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }
        doubleGridView.setBottomGridList(areas);

        return doubleGridView;


    }
//创建一个有一个listview的窗口
    private View createSingleListView_1() {

        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone_1();
                    }
                });
        //获得string文件中的文字
            String [] strings= UiUtils.getStringArray(R.array.selector_2);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length; ++i) {
            list.add(strings[i]);
        }
        singleListView.setList(list, -1);

        return singleListView;



    }
    //创建一个有一个listview的窗口
    private View createSingleListView() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();
                    }
                });
//获得string文件中的文字
        String [] strings=UiUtils.getStringArray(R.array.selector_1);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add("" + i);
        }
        singleListView.setList(list, -1);

        return singleListView;


    }
    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    } private void onFilterDone_1() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(1, "", "");
        }
    }
}
