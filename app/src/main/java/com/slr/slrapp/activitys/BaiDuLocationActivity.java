package com.slr.slrapp.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.FarmLocationBean;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.utils.UiUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 百度地图
 * 显示当前城市附近所有养殖场
 */
public class BaiDuLocationActivity extends Activity implements View.OnClickListener {


    MapView mMapView = null;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public BitmapDescriptor mCurrentMarker;
    boolean isFirstLoc = true; // 是否首次定位
    private ImageView back;
//    private ImageView iv_search;
    private TextView tv_area_name;
    private TextView tv_area_position;
    private TextView tv_area_jianjie;
    private LinearLayout ll_farm_info;
    private List<FarmLocationBean.StoreBean> lists;

    private double myWei;
    private double myJing;  //
    private LatLng myLat;

    private int stroeid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(UiUtils.getContext());

        setContentView(R.layout.activity_bai_du_location);
        lists = new ArrayList<>();


        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //初始化页面
        initView();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(UiUtils.getContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        initLocation();


        //开始定位
        mLocationClient.start();
        getNetLocations();


    }


    @Override
    protected void onDestroy() {
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        // 当不需要定位图层时关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);

        super.onDestroy();


    }

    @Override
    protected void onResume() {
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        super.onResume();

    }

    @Override
    protected void onPause() {
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        super.onPause();

    }


    //初始化页面中的控件
    private void initView() {
        back = (ImageView) findViewById(R.id.back);
//        iv_search = (ImageView) findViewById(R.id.iv_search);
        tv_area_name = (TextView) findViewById(R.id.tv_area_name);
        tv_area_position = (TextView) findViewById(R.id.tv_area_position);
        tv_area_jianjie = (TextView) findViewById(R.id.tv_area_jianjie);
        ll_farm_info = (LinearLayout) findViewById(R.id.ll_farm_info);
        back.setOnClickListener(this);
//        iv_search.setOnClickListener(this);
        ll_farm_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                //点击左上角返回按钮
                finish();


                break;
//            case R.id.iv_search:
//                //点击搜索按钮
//                Intent in = new Intent(this, SearchMoreActivity.class);
//                startActivity(in);
//                break;
            case R.id.ll_farm_info:    // 点击底部的布局
                if (ll_farm_info.getTag() != null) {
                    stroeid = (int) ll_farm_info.getTag();
                    if (stroeid != -1) {
                        Intent intent = new Intent();
                        intent.putExtra("map", stroeid);
                        setResult(550, intent);
                        finish();
                    }
                }
                break;
        }
    }


    //定位后的监听
    public class MyLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            // map view 销毁后不在处理新接收的位置
            if (bdLocation == null || mMapView == null) {
                return;
            }

            myJing = bdLocation.getLongitude();
            myWei = bdLocation.getLatitude();
            myLat = new LatLng(myWei, myJing);

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(0)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.mipmap.dingwei);
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
            mBaiduMap.setMyLocationConfigeration(config);


            if (isFirstLoc) {
                isFirstLoc = false;

                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

            }

            //获得县，区信息
            tv_area_name.setText("您当前位置："+bdLocation.getDistrict());
            //获得街道描述信息
            tv_area_position.setText(bdLocation.getLocationDescribe());
            tv_area_jianjie.setText("");


        }

        public void onReceivePoi(BDLocation poiLocation) {

        }

    }

    private void getNetLocations() {
        String url = ContentValues.SEARCH_FARM_LOCATION_URL;
        GsonRequest<FarmLocationBean> location = new GsonRequest<FarmLocationBean>(url, FarmLocationBean.class, new Response.Listener<FarmLocationBean>() {
            @Override
            public void onResponse(FarmLocationBean response) {
                if (response != null) {
                    int code = response.getCode();
                    if (code == 200) {
                        lists = response.getStore();
                        if (lists.size() > 0) {

                            addInfosOverlay(lists);


                            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(final Marker marker) {
                                    //获得marker中的数据
                                    FarmLocationBean.StoreBean info = (FarmLocationBean.StoreBean) marker.getExtraInfo().get("info");
                                    tv_area_name.setText(info.getStoreName());
                                    tv_area_position.setText(info.getAddress());
                                    tv_area_jianjie.setText(info.getBrief());

                                    ll_farm_info.setTag(info.getId());


//                                    InfoWindow mInfoWindow;
//                                    //生成一个TextView用户在地图中显示InfoWindow
//                                    TextView location = new TextView(getApplicationContext());
//                                    location.setBackgroundResource(R.drawable.location_tips);
//                                    location.setPadding(30, 20, 30, 50);
//                                    location.setText(info.getSpaceName());
//                                    //将marker所在的经纬度的信息转化成屏幕上的坐标
//                                    final LatLng ll = marker.getPosition();
//                                    Point p = mBaiduMap.getProjection().toScreenLocation(ll);
//                                    p.y -= 47;
//                                    LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
//                                    //为弹出的InfoWindow添加点击事件
//                                    mInfoWindow = new InfoWindow(location, llInfo,
//                                            new InfoWindow.OnInfoWindowClickListener()
//                                            {
//
//                                                @Override
//                                                public void onInfoWindowClick()
//                                                {
//                                                    //隐藏InfoWindow
//                                                    mBaiduMap.hideInfoWindow();
//                                                }
//                                            });
//                                    //显示InfoWindow
//                                    mBaiduMap.showInfoWindow(mInfoWindow);
//                                    //设置详细信息布局为可见
//                                    mMarkerInfoLy.setVisibility(View.VISIBLE);
//                                    //根据商家信息为详细信息布局设置信息
//                                    popupInfo(mMarkerInfoLy, info);
                                    return true;

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

            }
        });

        BaseApplication.getInstance().getRequestQueue().add(location);

    }


    /**
     * 初始化图层
     */
    public void addInfosOverlay(List<FarmLocationBean.StoreBean> infos) {

        mBaiduMap.clear();
        LatLng latLng = null;
        LatLng lat = null;
        OverlayOptions overlayOptions = null;
        Marker marker = null;

        Map<Double, FarmLocationBean.StoreBean> map = new HashMap<>();
        Map<Double, LatLng> map1 = new HashMap<>();

        for (int i = 0; i < infos.size(); i++) {
            FarmLocationBean.StoreBean info = infos.get(i);
            if (info.getWei() != null && info.getJing() != null) {
                // 位置
                latLng = new LatLng(Double.parseDouble(info.getWei()), Double.parseDouble(info.getJing()));
                // 图标
                overlayOptions = new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.farm)).zIndex(5);
                marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                marker.setExtraInfo(bundle);
                double dis = DistanceUtil.getDistance(myLat, latLng);
                map.put(dis, info);
                map1.put(dis, latLng);
            }
        }
        Set<Double> keys = map.keySet();
        double a = Collections.min(keys);
        FarmLocationBean.StoreBean b = map.get(a);

        tv_area_name.setText(b.getStoreName());
        tv_area_position.setText(b.getAddress());
        tv_area_jianjie.setText(b.getBrief());

        // 将地图移到到最后一个经纬度位置

        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(map1.get(Collections.min(map1.keySet())));
        mBaiduMap.setMapStatus(u);

    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setOpenGps(true);
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
}


