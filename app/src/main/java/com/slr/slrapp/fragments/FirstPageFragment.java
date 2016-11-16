package com.slr.slrapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.activitys.BaseActivity;
import com.slr.slrapp.activitys.CityLocationActivity;
import com.slr.slrapp.activitys.GoodsDetailsActivity;
import com.slr.slrapp.activitys.MyMessageActivity;
import com.slr.slrapp.activitys.SearchMoreActivity;
import com.slr.slrapp.activitys.WebViewActivity;
import com.slr.slrapp.beans.FirstPageBean;
import com.slr.slrapp.beans.Firstpage_list_bean;
import com.slr.slrapp.beans.LunBoBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.managers.mUpdateManager;
import com.slr.slrapp.utils.AdViewpagerUtil;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;
import com.slr.slrapp.widget.FirstPageListview;
import com.slr.slrapp.widget.PullToRefreshLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * 这是首页的fragment
 */
public class FirstPageFragment extends BaseFragment {
    //    private static final int REQUEST_CODE = 200;
    private TextView first_title_text_left;
    private ImageView first_title_img_left;
    private LinearLayout first_ll_left;
    private ImageView first_title_img_search;
    private EditText first_title_et;
    private ImageView first_title_img_right;
    private RelativeLayout ll_title_firstpage;
    //自定义Listview
    private FirstPageListview fisrt_listview;
    //包裹listview的父控件
    private PullToRefreshLayout refresh_view;
    private String cityname = BaseActivity.cityname;
    //listview添加的头布局
    private FrameLayout first_fl_pager;
    private ViewPager first_viewPager;
    private TextView tv_belowViewpager;
    private LinearLayout little_points;
    private RelativeLayout rl_wait;

    private AdViewpagerUtil adViewpagerUtil;

    private List<Firstpage_list_bean> lunbodatas = new ArrayList<>();
    //    private List<Firstpage_list_bean> listdatas = new ArrayList<>();
    //分页加载用
//    private List<FirstPageBean.ListBean> tempList;
    private String[] imgIds;
    private List<String> imgs = new ArrayList<String>();
    private FirstPageAdapter adapter;
    //下拉刷新
    private int freshNum = 0;

    private List<String> lunboWords = new ArrayList<>();  //轮播图下面的文字集合


    @Override
    public int getLayoutResID() {
        return R.layout.fragment_first_page;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        int visible = rl_wait.getVisibility();
//        if (visible == View.VISIBLE && NetWorkUtils.checkEnable(UiUtils.getContext())) {
//            freshNum=0;
//            getNetData(String.valueOf(freshNum));
//            getLunBoData();
//        }
//    }

    @Override
    public void initView(View view) {

        // 检查更新
        mUpdateManager updateManager = new mUpdateManager();
        updateManager.checkAppUpdate(getActivity(), false);
        //先初始化数据（假数据）
//        initDataSource();
//        getNetData(String.valueOf(freshNum));

        rl_wait = (RelativeLayout) view.findViewById(R.id.rl_wait);
        first_title_text_left = (TextView) view.findViewById(R.id.first_title_text_left);
        first_title_img_left = (ImageView) view.findViewById(R.id.first_title_img_left);
        first_title_img_search = (ImageView) view.findViewById(R.id.first_title_img_search);
        first_title_img_right = (ImageView) view.findViewById(R.id.first_title_img_right);
        first_ll_left = (LinearLayout) view.findViewById(R.id.first_ll_left);
        first_title_et = (EditText) view.findViewById(R.id.first_title_et);
        ll_title_firstpage = (RelativeLayout) view.findViewById(R.id.rl_title_farmerpage);
        fisrt_listview = (FirstPageListview) view.findViewById(R.id.fisrt_listview);
        refresh_view = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        initHeadView();
        //给listview添加头布局
        fisrt_listview.addHeaderView(first_fl_pager);

        adapter = new FirstPageAdapter(UiUtils.getContext());
        fisrt_listview.setAdapter(adapter);


    }

    //初始化listview的头布局
    public void initHeadView() {

        View headView = UiUtils.inflate(R.layout.firstpage_head_view);
        first_fl_pager = (FrameLayout) headView.findViewById(R.id.first_fl_pager);
        little_points = (LinearLayout) headView.findViewById(R.id.little_points);

        first_viewPager = (ViewPager) headView.findViewById(R.id.first_viewPager);
        tv_belowViewpager = (TextView) headView.findViewById(R.id.tv_belowViewpager);


    }

    @Override
    public void initListener() {

        getNetData(String.valueOf(freshNum));
        getLunBoData();


        first_title_text_left.setOnClickListener(this);
        first_title_img_right.setOnClickListener(this);
        first_title_img_search.setOnClickListener(this);
        first_title_et.setOnClickListener(this);
        //给下拉刷新添加监听
        refresh_view.setOnRefreshListener(new FirstPageFreshListener());


    }


    //填充数据
    @Override
    public void initData() {
        //给左上角城市定位
        if (cityname == null) {
            first_title_text_left.setText(UiUtils.getContext().getResources().getString(R.string.fisrtpage_title_left));
        } else {
            if ("郑州".equals(cityname) || "郑州市".equals(cityname)) {
                first_title_text_left.setText(cityname);
            } else {
                ToastUtil.showTextToast("您当前城市暂不支持购买");
                first_title_text_left.setText(cityname);
            }

        }
    }


    //上拉刷新，下拉加载更多的操作
    private class FirstPageFreshListener implements PullToRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
//            new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    // 千万别忘了告诉控件刷新完毕了哦！
//                    //  pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
//                }
//            }.sendEmptyMessageDelayed(0, 2000);
////            freshNum = listdatas.size() + 1;
            freshNum = 0;
            getNetData(String.valueOf(freshNum));
            getLunBoData();


        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //点击了左上角的“定位”
            case R.id.first_title_text_left:

                Intent intent = new Intent(UiUtils.getContext(), CityLocationActivity.class);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);


                break;
            //点击了右上角的“会话”
            case R.id.first_title_img_right:
                //进入我的消息页面
                Intent mesIntent = new Intent(UiUtils.getContext(), MyMessageActivity.class);
                startActivity(mesIntent);
                break;
            //点击了搜索图标
            case R.id.first_title_img_search:

                Intent intent1 = new Intent(UiUtils.getContext(), SearchMoreActivity.class);
                startActivity(intent1);

                break;
            case R.id.first_title_et:

                Intent intent2 = new Intent(UiUtils.getContext(), SearchMoreActivity.class);
                startActivity(intent2);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //修改首页左上角城市名称
        if (resultCode == CityLocationActivity.RESULT_CODE) {
            String name = data.getStringExtra(ContentValues.LOCATED_CITY_NAME);
            if ("郑州".equals(name)) {
                first_title_text_left.setText("郑州市");
            } else {
                first_title_text_left.setText(name);
            }

        }
    }

    //获取网络数据
    private void getNetData(final String num) {
        String url = ContentValues.FIRST_PAGE_REFRESH_URL;
        final Map<String, String> map = new HashMap<>();
        map.put("num", num);
        final List<Firstpage_list_bean> listdatas = new ArrayList<>();
        StringRequest getFirstData = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        ToastUtil.showTextToast(s);
                        if (!TextUtils.isEmpty(response)) {
                            Gson gson = new Gson();
                            FirstPageBean bean = gson.fromJson(response, FirstPageBean.class);
                            if (bean == null) {
                                if (refresh_view != null) {
                                    refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                                }
                                return;
                            }
                            int code = bean.getCode();
                            if (code == 101) {
                                ToastUtil.showTextToast("商品不存在");
                                if (refresh_view != null) {
                                    refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                                    return;
                                }
                            } else if (code == 200) {
                                if (refresh_view != null) {
                                    refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                                }

                                //得到数据后显示数据
                                refresh_view.setVisibility(View.VISIBLE);
                                rl_wait.setVisibility(View.GONE);

                                tv_belowViewpager.setText("为您推荐");

                                if (bean.getList().size() > 0) {
                                    //获取Listview数据
                                    List<FirstPageBean.ListBean> listbeans = bean.getList();
                                    if (listbeans.size() > 0) {
                                        FirstPageBean.ListBean list = null;
                                        Firstpage_list_bean fir = null;
                                        for (int i = 0; i < listbeans.size(); i++) {
                                            fir = new Firstpage_list_bean();
                                            list = listbeans.get(i);
                                            fir.goodid = list.getId();
                                            fir.imgUrl = list.getPhoto();
                                            fir.description = list.getJianJie();
                                            listdatas.add(fir);
                                        }
                                        if (freshNum == 0) {
                                            adapter.refreshData(listdatas);
                                        } else {
                                            adapter.addData(listdatas);
                                            fisrt_listview.setSelection(listdatas.size() - 1);
                                        }
                                        freshNum += 10;
                                        //给listview添加条目点击的监听
                                        fisrt_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                int headCount = fisrt_listview.getHeaderViewsCount();
                                                //  ToastUtil.showTextToast(UiUtils.getContext(), "点击了条目" + (i - headCount));
                                                //将包含数据的对象传递给二级页面
                                                Intent intent = new Intent(UiUtils.getContext(), GoodsDetailsActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable(ContentValues.FIRST_INTENT_BEAN_BUNDLE_NAME, listdatas.get(i - headCount));
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                        });


                                    }
                                }
                            } else {
                                if (refresh_view != null) {
                                    refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                                }
                                return;
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("请求网络失败");
                refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        BaseApplication.getInstance().getRequestQueue().add(getFirstData);
    }

    private void getLunBoData() {
        String url = ContentValues.LUN_BO_URL;
        Map<String, String> map = new HashMap<>();
        map.put("cityId", "");


        GsonRequest<LunBoBean> lunBoBeanGsonRequest = new GsonRequest<LunBoBean>(map, url, LunBoBean.class, new Response.Listener<LunBoBean>() {
            @Override
            public void onResponse(LunBoBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("商品不存在");
//                        refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
                        return;
                    }
                    if (code == 200) {
//                        refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
                        List<LunBoBean.CarBean> datas = response.getCar();
                        if (datas.size() > 0) {
                            Firstpage_list_bean fir = null;
                            LunBoBean.CarBean bea = null;
                            for (int i = 0; i < datas.size(); i++) {
                                fir = new Firstpage_list_bean();
                                bea = datas.get(i);
                                fir.imgUrl = bea.getCPhoto();
                                fir.goodid = bea.getId();
                                fir.lunboUrl = bea.getContent();
                                fir.arrangement = bea.getArrangement();   //轮播顺序
                                if (bea.getValue() == 0) {
                                    fir.ifFirst = false;
                                } else {
                                    fir.ifFirst = true;
                                }


                                lunbodatas.add(fir);
                                if (!imgs.contains(bea.getCPhoto())) {
                                    imgs.add(bea.getCPhoto());
                                }

                            }
                            imgIds = imgs.toArray(new String[imgs.size()]);
                            //轮播图的工具类
                            adViewpagerUtil = new AdViewpagerUtil(UiUtils.getContext(), first_viewPager, little_points, 8, 4, imgIds);
                            //给轮播图添加小点
                            adViewpagerUtil.initVps();

                            //轮播图条目点击监听
                            adViewpagerUtil.setOnAdItemClickListener(new AdViewpagerUtil.OnAdItemClickListener() {
                                @Override
                                public void onItemClick(View v, int flag) {
                                    //  ToastUtil.showTextToast(UiUtils.getContext(), "点击了" + flag);

                                    //将包含数据的对象传递给二级页面
                                    Intent intent = new Intent(UiUtils.getContext(), WebViewActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("WEBVIEW", lunbodatas.get(flag).lunboUrl);
                                    bundle.putString("TITLE", "详情");
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            //轮播图页面改变监听
                            adViewpagerUtil.setOnAdPageChangeListener(new AdViewpagerUtil.OnAdPageChangeListener() {
                                @Override
                                public void onPageScrollStateChanged(int arg0) {
                                }

                                @Override
                                public void onPageScrolled(int arg0, float arg1, int arg2) {
                                }

                                @Override
                                public void onPageSelected(int arg0) {
                                    if (arg0 == 0)
                                        arg0 = 1;
                                    if (arg0 == imgIds.length + 1) {
                                        arg0 = imgIds.length;
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("请求网络失败");
//                refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(lunBoBeanGsonRequest);

    }


    private class FirstPageAdapter extends BaseAdapter {
        private Context context;
        private List<Firstpage_list_bean> datas;

        public FirstPageAdapter(Context context) {
            this.context = context;
            datas = new ArrayList<>();
        }

        public void addData(List<Firstpage_list_bean> datas) {
            this.datas.addAll(datas);
            this.notifyDataSetChanged();
        }

        public void refreshData(List<Firstpage_list_bean> datas) {
            this.datas.clear();
            this.datas.addAll(datas);
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup viewGroup) {
            FirstViewHolder holder = null;
            if (v == null) {
                holder = new FirstViewHolder();
                v = UiUtils.inflate(R.layout.list_item_first_page);
                holder.iv = (ImageView) v.findViewById(R.id.first_item_img);
                holder.tv = (TextView) v.findViewById(R.id.first_item_tv);
                v.setTag(holder);
            } else {
                holder = (FirstViewHolder) v.getTag();
            }
            Firstpage_list_bean bean = datas.get(i);
            holder.tv.setText(bean.description);
            Picasso.with(UiUtils.getContext()).load(bean.imgUrl).into(holder.iv);
            return v;
        }
    }

    private class FirstViewHolder {
        ImageView iv;
        TextView tv;
    }

    @Override
    public void onPause() {

        super.onPause();
    }
}
