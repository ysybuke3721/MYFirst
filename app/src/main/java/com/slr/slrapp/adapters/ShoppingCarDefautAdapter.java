package com.slr.slrapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.slr.slrapp.R;
import com.slr.slrapp.activitys.FarmerActivity;
import com.slr.slrapp.utils.ToastUtil;

/**
 * User: Administrator
 * Time: 2016/7/4 0004
 * Description: ${todo}(购物车默认：没有购物列表)
 * Version V1.0
 */
public class ShoppingCarDefautAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ToastUtil toastUtil;
    private String tag;

    public ShoppingCarDefautAdapter(Context context) {
        this.context = context;
    }

    public ShoppingCarDefautAdapter(Context context, String tag) {
        this.context = context;
        this.tag = tag;
    }


    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return i1;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        toastUtil = new ToastUtil();
        // view初始化
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {

            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.layout_shopping_null, null);
            viewHolder.bt = (Button) view.findViewById(R.id.shipping_car_bt);
            viewHolder.iv = (ImageView) view.findViewById(R.id.shopping_car_null_iv);
            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //TODO  跳转到指定页面去
//                if (isWXAppInstalledAndSupported()) {
//                    weixinTest();
//                } else {
//                    ToastUtil.showTextToast("您的手机没有安装微信，请安装后重试");
//                    return;
//
//                }

//                if (tag.equals("activity")) {
//                    Intent ient = new Intent(context, FarmerActivity.class);
//                    context.startActivity(ient);
//                } else if (tag.equals("fragment")) {
//                    MainActivity activity = (MainActivity) context;
//                    FragmentUtils.replaceFragment(activity.getSupportFragmentManager(), R.id.frameLayout_main, FarmerFragment.class, null, false);
//                }


                Intent ient = new Intent(context, FarmerActivity.class);
                context.startActivity(ient);
            }
        });

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    // 优化adapter，避免重复加载，减少耗时
    private class ViewHolder {
        private ImageView iv;
        private Button bt;

    }

//    private void weixinTest() {
//        String url = "http://192.168.0.188:8080/service/weixinPayApi/aa";
//        String order = "11111111";
//        String userIp = "127.0.0.1";
//        Map<String, String> map = new HashMap<>();
//        map.put("order", order);
//        map.put("userIp", userIp);
//        GsonRequest<weixinBean> gs = new GsonRequest<weixinBean>(map, url, weixinBean.class, new Response.Listener<weixinBean>() {
//            @Override
//            public void onResponse(weixinBean response) {
//                if (response != null) {
//                    //商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID，
//                    IWXAPI msgApi = WXAPIFactory.createWXAPI(UiUtils.getContext(), null);
//                    // 将该app注册到微信
//
//
//                    PayReq request = new PayReq();
//                    weixinBean.PayInfoBean info = response.getPayInfo();
//                    request.partnerId = info.getPartnerid();
//                    request.prepayId = info.getPrepay_id();
//                    request.packageValue = info.getPackageX();
//                    ToastUtil.showTextToast("支付");
//                    request.nonceStr = info.getNonce_str();
//                    request.sign = info.getSigns();
//                    request.timeStamp = info.getTimestamp();
//                    msgApi.sendReq(request);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                ToastUtil.showTextToast("请求网络异常");
//            }
//        });
//        BaseApplication.getInstance().getRequestQueue().add(gs);
//
//
//    }
//
//    private boolean isWXAppInstalledAndSupported() {
//        IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
//        msgApi.registerApp(Constants.APP_ID);
//
//        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
//                && msgApi.isWXAppSupportAPI();
//
//        return sIsWXAppInstalledAndSupported;
//    }


}
