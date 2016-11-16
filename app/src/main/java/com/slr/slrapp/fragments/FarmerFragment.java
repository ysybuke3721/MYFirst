package com.slr.slrapp.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.activitys.BaiDuLocationActivity;
import com.slr.slrapp.activitys.BaseActivity;
import com.slr.slrapp.activitys.FarmCameraActivity;
import com.slr.slrapp.activitys.SearchMoreActivity;
import com.slr.slrapp.adapters.DefaultAdapter;
import com.slr.slrapp.adapters.FarmListAdapter;
import com.slr.slrapp.adapters.FirstFilterAdapter;
import com.slr.slrapp.adapters.ThirdFilterAdapter;
import com.slr.slrapp.beans.FarmFilterBean;
import com.slr.slrapp.beans.FarmListBeans;
import com.slr.slrapp.beans.FarmerPageLIstBean;
import com.slr.slrapp.beans.Filter13Bean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.holders.BaseHolder;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.LogUtils;
import com.slr.slrapp.utils.NetWorkUtils;
import com.slr.slrapp.utils.PixUtil;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.slr.slrapp.widget.PullableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 这是牧场页面
 */
public class FarmerFragment extends BaseFragment {


    private ImageView farmer_img_location;
    private ImageView farmer_img_right;
    private RelativeLayout rl_title_farmerpage;
    private TextView select_tv_1;
    private RelativeLayout rl_all_kinds;
    private TextView select_tv_2;
    private RelativeLayout rl_smart_sort;
    private TextView select_tv_3;
    private RelativeLayout rl_filter;
    //自定义Listview
    private PullableListView farmer_goods_lv;
    //自定义上拉加载下拉刷新
    private PullToRefreshLayout refresh_view;
    private RelativeLayout rl_wait;

    private String defaultTitle1 = UiUtils.getContext().getResources().getString(R.string.farmer_chose_title_1);
    private String defaultTitle2 = UiUtils.getContext().getResources().getString(R.string.farmer_chose_title_2);
    private String defaultTitle3 = UiUtils.getContext().getResources().getString(R.string.farmer_chose_title_3);

    //listview的适配器
    private FarmListAdapter adapter;
    private FirstFilterAdapter adapter1;
    private ThirdFilterAdapter adapter3;
    //第三个筛选栏数据
    List<FarmFilterBean> list3 = new ArrayList<>();
    //第一个筛选栏数据
    private List<FarmFilterBean> list1 = new ArrayList<>();
    //第二个筛选栏数据
    private List<String> list2 = new ArrayList<>();

    //请求网络的参数
    //刷新页数
    private int freshNum = 0;
    private String typeId = "";
    private String condition = "";   //好评。人气
    private int storeid = -1;   //商家id
    private double weidu = -1;       //纬度
    private double jingdu = -1;      //经度

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    freshNum=0;
                    storeid=msg.getData().getInt("id");
                    getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        int visible=rl_wait.getVisibility();
        if (visible==View.VISIBLE&& NetWorkUtils.checkEnable(UiUtils.getContext())){
            freshNum=0;
            getFilterNetData();
            //联网获取数据的操作放在这里
            //联网刷新

            getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_farmer;
    }

    @Override
    public void initView(View view) {

        rl_wait= (RelativeLayout) view.findViewById(R.id.rl_wait);
        farmer_img_location = (ImageView) view.findViewById(R.id.farmer_img_location);
        farmer_img_right = (ImageView) view.findViewById(R.id.farmer_img_right);
        rl_title_farmerpage = (RelativeLayout) view.findViewById(R.id.rl_title_farmerpage);
        select_tv_1 = (TextView) view.findViewById(R.id.select_tv_1);
        rl_all_kinds = (RelativeLayout) view.findViewById(R.id.rl_all_kinds);
        select_tv_2 = (TextView) view.findViewById(R.id.select_tv_2);
        rl_smart_sort = (RelativeLayout) view.findViewById(R.id.rl_smart_sort);
        select_tv_3 = (TextView) view.findViewById(R.id.select_tv_3);
        rl_filter = (RelativeLayout) view.findViewById(R.id.rl_filter);
        farmer_goods_lv = (PullableListView) view.findViewById(R.id.farmer_goods_lv);
        refresh_view = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);


        farmer_img_location.setOnClickListener(this);
        farmer_img_right.setOnClickListener(this);
        rl_all_kinds.setOnClickListener(this);
        rl_smart_sort.setOnClickListener(this);
        rl_filter.setOnClickListener(this);


//        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        adapter = new FarmListAdapter();

        //初始化控件
        initSelectorView();     //第三个过滤器的父控件
        //给Listview填充数据
        farmer_goods_lv.setAdapter(adapter);


//        getListNetData("", "", "", new int[]{}, "", "");

        //初始化选择器的文字
//        String title1 = sharedPreferences.getString(ContentValues.SELECTOR_FIRST_WORD, defaultTitle1);
//        select_tv_1.setText(title1);

//        typeId = sharedPreferences.getString(ContentValues.REMBER_SELECTOR_FIRST_WORD_ID, "");

//        String title2 = sharedPreferences.getString(ContentValues.SELECTOR_SECOND_WORD, defaultTitle2);
//        select_tv_2.setText(title2);
//       int title3 = sharedPreferences.getInt(ContentValues.SELECTOR_THIRD_WORD, -1);
        // select_tv_3.setText(title3);
        // TODO  这里根据选择的情况去回显数据

//        getListNetData(freshNum, typeId, title2, title3, weidu, jingdu);
    }


    @Override
    public void initListener() {
        //给选择器设置适配器


//        //给Listview填充数据
//        farmer_goods_lv.setAdapter(adapter);

        //为下拉加载设置监听
        refresh_view.setOnRefreshListener((PullToRefreshLayout.OnRefreshListener) new FarmerPageFreshListener());
        //Listview条目点击事件
//        farmer_goods_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(UiUtils.getContext(), FarmCameraActivity.class);
//                //TODO　　携带商品信息（商品对象）到监控页面
//
//
//                startActivity(intent);
//
//
//            }
//        });


    }


    //联网获取数据的操作放在这里
    @Override
    public void initData() {
        getFilterNetData();
        //联网获取数据的操作放在这里
        //联网刷新

        getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);

    }

    //TODO 上拉加载下拉刷新的监听放在这里
    private class FarmerPageFreshListener implements PullToRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            freshNum = 0;
            //联网刷新
            getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);

        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            //联网刷新
            getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);

        }
    }
    //得到从地图页传递过来的商户id
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==550){


            int a=data.getIntExtra("map",-1);
            Message message=new Message();
            Bundle bundle=new Bundle();
            bundle.putInt("id",a);
            message.setData(bundle);
            message.what=1;
            handler.sendMessage(message);
        }
    }

    //处理点击事件
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //点击地图图标
            case R.id.farmer_img_location:
                // ToastUtil.showTextToast(UiUtils.getContext(), "点击了地图图标");
                Intent intent = new Intent(UiUtils.getContext(), BaiDuLocationActivity.class);
//                startActivity(intent);
                startActivityForResult(intent,555);


                break;
            //点击“放大镜” 图标
            case R.id.farmer_img_right:
                // ToastUtil.showTextToast(UiUtils.getContext(), "点击“放大镜” 图标");
                //进入搜索页面
                Intent intent1 = new Intent(UiUtils.getContext(), SearchMoreActivity.class);
                startActivity(intent1);
                break;
            //点击“全部分类”
            case R.id.rl_all_kinds:
                // ToastUtil.showTextToast(UiUtils.getContext(), "点击“全部分类”");
                //点击后文字颜色变绿
                select_tv_1.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text_checked));

                showPopWindow1();

                break;
            //点击“智能排序”
            case R.id.rl_smart_sort:
                // ToastUtil.showTextToast(UiUtils.getContext(), "点击“智能排序”");
                select_tv_2.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text_checked));
                showPopWindow2();
                break;
            //点击“筛选”
            case R.id.rl_filter:
                //ToastUtil.showTextToast(UiUtils.getContext(), "点击“筛选”");
                select_tv_3.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text_checked));
                showPopWindow3();
                break;
        }
    }


    //弹出第一个popupwidow
    private PopupWindow popupWindow1 = null;
    private ListView listView1 = new ListView(UiUtils.getContext());
    ;
//    private DefaultAdapter adapter1 = null;

    private void showPopWindow1() {

        if (popupWindow1 == null) {

            popupWindow1 = new PopupWindow(UiUtils.getContext());
            popupWindow1.setWidth(screenWidth);
            popupWindow1.setHeight(PixUtil.dip2px(UiUtils.getContext(), 200));
            popupWindow1.setContentView(listView1);

            ColorDrawable dw = new ColorDrawable(0x00000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            popupWindow1.setBackgroundDrawable(dw);

            //设置可以获得焦点
            popupWindow1.setFocusable(true);
        }
        popupWindow1.showAsDropDown(rl_all_kinds);

//        if (adapter1 == null) {
//            adapter1 = new DefaultAdapter<FarmFilterBean>(list1) {
//                @Override
//                protected BaseHolder<FarmFilterBean> getHolder() {
//                    return new SimpleImgTvHolder();
//                }
//            };
//        }
//        listView1.setAdapter(adapter1);
        listView1.setBackgroundResource(R.color.color4);
        listView1.setDivider(null);
        listView1.setFastScrollEnabled(false);
        listView1.setCacheColorHint(Color.TRANSPARENT);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tv = list1.get(i).words;
                select_tv_1.setText(tv);
                //点击的条目。用于筛选
                typeId = list1.get(i).strId;


                //将标题保存起来，用于标题内容回显
                sharedPreferences.edit().putString(ContentValues.SELECTOR_FIRST_WORD, tv).apply();
                sharedPreferences.edit().putString(ContentValues.REMBER_SELECTOR_FIRST_WORD_ID, typeId).apply();
                if (popupWindow1 != null && popupWindow1.isShowing()) {
                    popupWindow1.dismiss();
                    popupWindow1 = null;
                }
            }
        });

        //popupwindow消失的监听
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //消失后先让文字变成黑色
                select_tv_1.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text_unchecked));
                freshNum = 0;
                //联网刷新
                getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);

            }
        });
    }


    //弹出第二个popupwindow的方法
    private PopupWindow popupWindow2 = null;
    private ListView listView2 = new ListView(UiUtils.getContext());
    private DefaultAdapter adapter2 = null;

    private void showPopWindow2() {


        if (popupWindow2 == null) {
//            listView2 = new ListView(UiUtils.getContext());
            popupWindow2 = new PopupWindow(UiUtils.getContext());
            popupWindow2.setWidth(screenWidth);
            popupWindow2.setHeight(PixUtil.dip2px(UiUtils.getContext(), 150));
            popupWindow2.setContentView(listView2);

            ColorDrawable dw = new ColorDrawable(0x00000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            popupWindow2.setBackgroundDrawable(dw);

            //设置可以获得焦点
            popupWindow2.setFocusable(true);
        }
        popupWindow2.showAsDropDown(rl_all_kinds);

        listView2.setBackgroundResource(R.color.color4);
        listView2.setDivider(null);
        listView2.setCacheColorHint(Color.TRANSPARENT);
        listView2.setFastScrollEnabled(false);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tv = list2.get(i);
                select_tv_2.setText(tv);
//                ToastUtil.showTextToast(UiUtils.getContext(), "点击了" + tv);
                //点击的条目。用于筛选
                condition = tv;

                //将标题保存起来，用于标题内容回显
                sharedPreferences.edit().putString(ContentValues.SELECTOR_SECOND_WORD, tv).apply();
                if ("距离".equals(tv)) {
                    weidu = BaseActivity.wei;
                    jingdu = BaseActivity.jing;
                    //联网刷新
//                    getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);

                } else {
                    jingdu = -1;
                    weidu = -1;
                    //联网刷新
//                    getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }


                if (popupWindow2 != null && popupWindow2.isShowing()) {
                    popupWindow2.dismiss();
                    popupWindow2 = null;
                }
            }
        });
        //popupwindow消失的监听
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                select_tv_2.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text_unchecked));
                //联网刷新
                freshNum = 0;
                getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);
            }
        });

    }


    //弹出第三个popupwindow的方法


    //是否选择了默认
    //boolean ifSelectDefault = sharedPreferences.getBoolean(ContentValues.IF_SELECT_DEFAULT, false);
    private PopupWindow popupWindow3;
//    private DefaultAdapter<FarmFilterBean> adapter3;

    private void showPopWindow3() {

        if (popupWindow3 == null) {
            popupWindow3 = new PopupWindow(UiUtils.getContext());
            popupWindow3.setWidth(screenWidth);
            popupWindow3.setHeight(PixUtil.dip2px(UiUtils.getContext(), 150));
            popupWindow3.setContentView(conventview);
            ColorDrawable dw = new ColorDrawable(0x00000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            popupWindow3.setBackgroundDrawable(dw);
            //设置可以获得焦点
            popupWindow3.setFocusable(true);
        }
        popupWindow3.showAsDropDown(rl_filter);


        //点击选择器后初始化条目内选中信息
//        int str = sharedPreferences.getInt(ContentValues.SELECTOR_THIRD_WORD, -1);

//
//        if (adapter3 != null) {
//            adapter3.notifyDataSetChanged();
//        }

        //gridview的条目点击事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //点击之后让popupwindow消失
                if (popupWindow3 != null && popupWindow3.isShowing()) {
                    popupWindow3.dismiss();
                    //如果变为Null会出现小bug
                    //  popupWindow3 = null;
                }
            }
        });

        //popupwindow消失的监听
        popupWindow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                select_tv_3.setTextColor(UiUtils.getContext().getResources().getColor(R.color.text_unchecked));


                if (list3.size()>0){

                    for (int i = 0; i <list3.size() ; i++)  {
                       FarmFilterBean bean= list3.get(i);
                        if (bean.ifchecked) {
                            storeid = Integer.parseInt(bean.strId);
                            LogUtils.LogI("storeid1",""+storeid);
                            freshNum = 0;
                            //联网进行筛选
                            getListNetData(freshNum, typeId, condition, storeid, weidu, jingdu);
                        }
                    }
                }
            }
        });
    }

    private LinearLayout ll_up_gridview;
    private GridView gridview;
    private ImageView img_selector3;
    private View conventview;

    //初始化第三个选择器的view
    private void initSelectorView() {
        conventview = UiUtils.inflate(R.layout.selector_3_content_view);
        gridview = (GridView) conventview.findViewById(R.id.gridview);
    }

    //给第二个selector提供holder
    private class Selector2Holder extends BaseHolder<String> {
        TextView tv;

        @Override
        protected View initView() {
            View view = UiUtils.inflate(R.layout.simple_text);
            tv = (TextView) view.findViewById(R.id.tv);
            return view;
        }

        @Override
        protected void refreshView(String s) {
            tv.setText(s);
        }
    }


    private void getListNetData(int number, String typeid, String condition, int storeid, double weidu, double jingdu) {
        String url = ContentValues.FARM_PAGE_URL;
        Map<String, String> map = new HashMap<>();
        String num = null;
        String wd = null;
        String jd = null;
        String sid = null;
        if (storeid == -1) {
            sid = "";
        } else {
            sid = String.valueOf(storeid);
        }
        if (number == -1) {
            num = "0";
        } else {
            num = String.valueOf(number);
        }

        if (weidu == -1) {
            wd = "";
        } else {
            wd = String.valueOf(weidu);
        }
        if (jingdu == -1) {
            jd = "";
        } else {
            jd = String.valueOf(jingdu);
        }
        map.put("number", num);
        map.put("typeid", typeid);
        map.put("condition", condition);
        map.put("storeId", sid);
        map.put("wei", wd);
        map.put("jing", jd);
        final GsonRequest<FarmListBeans> request = new GsonRequest<FarmListBeans>(map, url, FarmListBeans.class, new Response.Listener<FarmListBeans>() {
            @Override
            public void onResponse(FarmListBeans response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("暂不存在商品信息！");
                        refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                        return;
                    }
                    if (code == 200) {
                        refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);




                        //数据源
                        final List<FarmerPageLIstBean> datas = new ArrayList<>();
                        //获取listview的数据
                        List<FarmListBeans.ListBean> lists = response.getList();
                        if (lists.size() > 0) {
                            FarmerPageLIstBean farmbean = null;
                            for (int k = 0; k < lists.size(); k++) {
                                FarmListBeans.ListBean bean = lists.get(k);
                                farmbean = new FarmerPageLIstBean();
                                farmbean.farmwhere = bean.getAddress();
                                farmbean.rBarCounts = (float) bean.getStar();
                                farmbean.appraiseCounts = bean.getEvaluateQuantity();
                                farmbean.imgUrl = bean.getSmallPhoto();
                                farmbean.goodsArea = bean.getSpaceName();
                                farmbean.farmWhat = bean.getStoreName();
                                farmbean.id = bean.getId();

                                datas.add(farmbean);
                            }
                            if (freshNum == 0) {
                                adapter.reFreshData(datas);

                            } else {
                                adapter.setDatas(datas);
                                farmer_goods_lv.setSelection(datas.size() - 1);
                            }
                            freshNum += 10;
                            farmer_goods_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    //TODO　　携带商品信息（商品对象）到监控页面
                                    Intent intent = new Intent(UiUtils.getContext(), FarmCameraActivity.class);
                                    intent.putExtra("goodsId", datas.get(i).id);
                                    startActivity(intent);
                                }
                            });

                            //显示数据
                            rl_wait.setVisibility(View.GONE);
                            refresh_view.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求错误！");
                refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(request);
    }
    private void getFilterNetData() {
        String url = ContentValues.FILTER_BLANK_URL;
        GsonRequest<Filter13Bean> filterrequest = new GsonRequest<Filter13Bean>(url, Filter13Bean.class, new Response.Listener<Filter13Bean>() {
            @Override
            public void onResponse(Filter13Bean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast(response.getMessage());
                        return;
                    }
                    if (code == 200) {
                        //得到牧场筛选栏数据
                        List<Filter13Bean.SpaceListBean> list3beans = response.getSpaceList();
                        if (list3beans.size() > 0) {

                            FarmFilterBean lit3bean = null;
                            list3.clear();

                            for (int i = 0; i < list3beans.size(); i++) {
                                lit3bean = new FarmFilterBean();
                                Filter13Bean.SpaceListBean bea = list3beans.get(i);
                                lit3bean.words = bea.getStoreName();
                                if (bea.getIsSelected() == 0) {
                                    lit3bean.ifchecked = false;
                                } else {
                                    lit3bean.ifchecked = true;
                                }
                                lit3bean.strId = String.valueOf(bea.getId());
                                LogUtils.LogI("storeidyuan",""+storeid);
                                list3.add(lit3bean);
                            }
                            adapter3 = new ThirdFilterAdapter(list3);
                            gridview.setAdapter(adapter3);

                        }

                        //制造第二个筛选栏数据

                        String[] strings = UiUtils.getStringArray(R.array.selector_2);
                        list2.clear();
                        for (int i = 0; i < strings.length; i++) {
                            list2.add(strings[i]);
                        }
                        if (adapter2 == null) {
                            adapter2 = new DefaultAdapter<String>(list2) {
                                @Override
                                protected BaseHolder<String> getHolder() {
                                    return new Selector2Holder();
                                }
                            };
                        }

                        listView2.setAdapter(adapter2);


                        //得到第一个筛选栏的列表 list1
                        List<Filter13Bean.TypeListBean> list1beans = response.getTypeList();
                        if (list1beans.size() > 0) {
                            //第一个筛选栏数据

                            FarmFilterBean list1bean = null;
                            list1.clear();
                            for (int j = 0; j < list1beans.size(); j++) {
                                list1bean = new FarmFilterBean();
                                Filter13Bean.TypeListBean bea = list1beans.get(j);
                                list1bean.strId = bea.getId();
                                list1bean.words = bea.getName();
                                list1.add(list1bean);
                            }
//                            adapter1.setData(list1);
                            adapter1 = new FirstFilterAdapter(list1);
                            listView1.setAdapter(adapter1);
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求出现异常");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(filterrequest);


    }


}
