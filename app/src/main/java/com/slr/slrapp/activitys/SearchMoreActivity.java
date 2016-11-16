package com.slr.slrapp.activitys;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jaeger.library.StatusBarUtil;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.adapters.DefaultAdapter;
import com.slr.slrapp.adapters.FarmListAdapter;
import com.slr.slrapp.adapters.FirstFilterAdapter;
import com.slr.slrapp.beans.FarmFilterBean;
import com.slr.slrapp.beans.FarmListBeans;
import com.slr.slrapp.beans.FarmerPageLIstBean;
import com.slr.slrapp.beans.Filter13Bean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.holders.BaseHolder;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchMoreActivity extends BaseActivity {


    private EditText et_search;
    private TextView tv_cancel;
    private GridView gv_hot_search;
    private LinearLayout ll_hot_search;
    private ListView lv_search_result;
    //listview的适配器
    private FarmListAdapter adapter;
    //gridview的适配器
    private FirstFilterAdapter adapter1;

    private RelativeLayout rl_wait;

    //第一个筛选栏数据
    private List<FarmFilterBean> list1 = new ArrayList<>();
    private List<String> datas = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_search_more;
    }

    @Override
    public void initView() {

        getFilterNetData();

        //设置颜色(沉浸式状态栏)
        StatusBarUtil.setColor(this, UiUtils.getContext().getResources().getColor(R.color.title_bg), 0);
        rl_wait= (RelativeLayout) findViewById(R.id.rl_wait);
        ll_hot_search = (LinearLayout) findViewById(R.id.ll_hot_search);
        lv_search_result = (ListView) findViewById(R.id.lv_search_result);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        gv_hot_search = (GridView) findViewById(R.id.gv_hot_search);
        tv_cancel.setOnClickListener(this);
        lv_search_result.setVisibility(View.GONE);
        adapter = new FarmListAdapter();
        lv_search_result.setAdapter(adapter);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                //点击了搜索
                String word = et_search.getText().toString().trim();
                if (!TextUtils.isEmpty(word)) {
                    hiddenInput();
                    searchNetData(word);
                } else {
                    ToastUtil.showTextToast("请输入搜索内容");
                    return;
                }
                break;
        }
    }


    //填充gridview的holder
    private class MyGridViewHolder extends BaseHolder<String> {
        private TextView tv;

        @Override
        protected View initView() {
            View view = UiUtils.inflate(R.layout.gv_item_hot_search);
            tv = (TextView) view.findViewById(R.id.tv_hot_item);
            return view;
        }

        @Override
        protected void refreshView(String s) {
            tv.setText(s);
        }
    }

    @Override
    public void onBackPressed() {
        hiddenInput();
        et_search.setText("");
        rl_wait.setVisibility(View.GONE);
        lv_search_result.setVisibility(View.GONE);
        ll_hot_search.setVisibility(View.VISIBLE);
        FarmListAdapter adapter1 = new FarmListAdapter();
        lv_search_result.setAdapter(adapter1);
        finish();
        super.onBackPressed();
    }


    //点击后调用搜索功能
    private void searchNetData(String goodName) {
        String url = ContentValues.FARM_PAGE_URL;
        Map<String, String> map = new HashMap<>();
        double weidu = BaseActivity.wei;
        double jingdu = BaseActivity.jing;
//        String wd=String.valueOf(weidu);
//        String jd=String.valueOf(jingdu);
        String mess = et_search.getText().toString().trim();
        if (mess == null) {
            ToastUtil.showTextToast("搜索内容不能为空");
            return;
        }
        map.put("name", mess);
        map.put("number", "0");
        map.put("typeid", "");
        map.put("condition", "");
        map.put("storeId", "");
        map.put("wei", "");
        map.put("jing", "");

        final GsonRequest<FarmListBeans> request = new GsonRequest<FarmListBeans>(map, url, FarmListBeans.class, new Response.Listener<FarmListBeans>() {
            @Override
            public void onResponse(FarmListBeans response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 101) {
                        ToastUtil.showTextToast("暂不存在商品信息！");
                        return;
                    }
                    if (code == 200) {
                        et_search.setText("");    //将搜索内容变为空
                        lv_search_result.setVisibility(View.VISIBLE);
                        rl_wait.setVisibility(View.GONE);
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
                            adapter.reFreshData(datas);

                            lv_search_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    //TODO　　携带商品信息（商品对象）到监控页面
                                    Intent intent = new Intent(UiUtils.getContext(), FarmCameraActivity.class);
                                    intent.putExtra("goodsId", datas.get(i).id);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求错误！");

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
                        //得到第热门搜索的数据
                        List<Filter13Bean.TypeListBean> list1beans = response.getTypeList();
                        if (list1beans.size() > 0) {
                            //第一个筛选栏数据
                            FarmFilterBean list1bean = null;
                            for (int j = 0; j < list1beans.size(); j++) {
                                list1bean = new FarmFilterBean();
                                Filter13Bean.TypeListBean bea = list1beans.get(j);
                                list1bean.strId = bea.getId();
                                list1bean.words = bea.getName();
                                list1.add(list1bean);
                                if (j > 0) {
                                    datas.add(bea.getName());
                                }
                            }
//                            adapter1 = new FirstFilterAdapter(list1);
//                            gv_hot_search.setAdapter(adapter1);

                            //给gridview设置适配器
                            gv_hot_search.setAdapter(new DefaultAdapter<String>(datas) {
                                @Override
                                protected BaseHolder<String> getHolder() {
                                    return new MyGridViewHolder();
                                }
                            });


                            //给gridview添加条目监听
                            gv_hot_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String text = datas.get(i);
                                    hiddenInput();  //关闭输入法
                                    rl_wait.setVisibility(View.VISIBLE);
                                    ll_hot_search.setVisibility(View.GONE);

                                    searchNetData(text);

                                }
                            });
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

    private void hiddenInput() {
        //关闭输入法
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SearchMoreActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
