package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.LogUtils;
import com.slr.slrapp.utils.UiUtils;

/**
 * @author song
 *
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
    public static SharedPreferences sharedPreferences;
    public  static  int screenWidth;
    public static  int screnHeight;
    public static String cityname;
    private WindowManager windowManager;
    public Context context;
    public Bundle savedInstanceState;
    public LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    private boolean isNeedFresh;
    public static double wei;
    public static double jing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        sharedPreferences= UiUtils.getContext().getSharedPreferences(ContentValues.SP_NAME,0);
        windowManager= (WindowManager) UiUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth=windowManager.getDefaultDisplay().getWidth();
        screnHeight=windowManager.getDefaultDisplay().getHeight();
        context=UiUtils.getContext();
        setContentView(getLayoutResId());
        initView();
        initListener();
        initData();


        mLocationClient = new LocationClient(BaseApplication.getInstance());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        //对定位进行初始化
        InitLocation();
        //开始定位
        mLocationClient.start();
    }
   


    /**
     * 获取当前界面的布局
     * @return
     */
    public abstract int getLayoutResId() ;

    /**
     * 查找控件
     */
    public abstract void initView() ;
    
    /**
     * 给控件添加监听
     */
    public abstract  void initListener() ;
    
    /**
     * 初始化数据
     * 给控件填充内容
     */
    public abstract void initData();
    
    /**
     * 子类界面处理按钮的单击事件
     * @param v
     */
    public void onInnerClick(View v) {
        
    }
    /**
     * 弹出吐司
     */
    public void showToast(String msg){
        Toast.makeText(getApplicationContext(), 
                msg, Toast.LENGTH_SHORT).show();
    }
    public void showToast(int msgResId){
        Toast.makeText(getApplicationContext(), 
                msgResId, Toast.LENGTH_SHORT).show();
    }
    /**
     * 打印log
     */
    public void logI(String msg){
        LogUtils.LogI(getClass(), msg);
    }

    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation==null){
                return;
            }
            if (isNeedFresh) {
                isNeedFresh = false;

            }
            wei= bdLocation.getLatitude();  //维度
            jing=bdLocation.getLongitude();  //经度
            cityname=bdLocation.getCity();
//            ToastUtil.showTextToast(cityname);

//            ToastUtil.showTextToast(jing+"+++++++"+wei);

        }
    }
    private void InitLocation() {
        // 设置定位参数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000*30;// 30分钟扫描1次
        // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        option.setProdName("通过GPS定位我当前的位置");
        // 禁用启用缓存定位数据
        option.disableCache(true);
        // 设置定位方式的优先级。
        // 当gps可用，而且获取了定位结果时，不再发起网络请求，直接返回给用户坐标。这个选项适合希望得到准确坐标位置的用户。如果gps不可用，再发起网络请求，进行定位。
        option.setPriority(LocationClientOption.GpsFirst);
        option.setOpenGps(true);
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
//        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
}
