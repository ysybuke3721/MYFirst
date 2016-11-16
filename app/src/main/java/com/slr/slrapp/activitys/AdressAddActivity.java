package com.slr.slrapp.activitys;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.China;
import com.slr.slrapp.beans.ProvinceBean;
import com.slr.slrapp.beans.ReturnCodeBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.OptionsPickeView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(新建收货地址)
 * Version V1.0
 */
public class AdressAddActivity extends BaseActivity{

    private LinearLayout back, title_right;
    private Context context;
    private TextView title, title_right_bt;
    private EditText adress_add_name, adress_add_area, adress_add_detail, adress_add_phone;
    private CheckBox adress_add_default_select;
    private ReturnCodeBean returnCodeBean;
    private String ifAddress = "0";
    private OptionsPickeView pvOptions;
    private View vMasker;

    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    // 用户id
    private String userId ;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_adress_add;
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        title_right = (LinearLayout) findViewById(R.id.title_right);
        title_right_bt = (TextView) findViewById(R.id.title_right_bt);
        adress_add_name = (EditText) findViewById(R.id.adress_add_name);
        adress_add_area = (EditText) findViewById(R.id.adress_add_area);
        adress_add_detail = (EditText) findViewById(R.id.adress_add_detail);
        adress_add_phone = (EditText) findViewById(R.id.adress_add_phone);
        adress_add_default_select = (CheckBox) findViewById(R.id.adress_add_default_select);
        adress_add_default_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ifAddress = "1";
                } else {
                    ifAddress = "0";
                }
            }
        });
        vMasker=findViewById(R.id.vMasker);
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        title_right.setOnClickListener(this);
        adress_add_area.setOnClickListener(this);
        adress_add_area.setKeyListener(null);
    }

    @Override
    public void initData() {
        context = this;
        userId=context.getSharedPreferences(ContentValues.SP_NAME,0).getString(ContentValues.USER_ID,null);
        title.setText("新建收货地址");
        title_right.setVisibility(View.VISIBLE);
        title_right_bt.setText("保存");
        loadCityPicker();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                String url = ApiUtils.AddAdrsee();
                save(url);
                break;
            case R.id.adress_add_area:
                pvOptions.show();
                break;
        }
    }

    private void save(String url) {
        String adress_add_name_val = adress_add_name.getText().toString().trim();
        if (TextUtils.isEmpty(adress_add_name_val)) {
            ToastUtil.showTextToast("请输入收货人姓名");
            return;
        }
        String adress_add_area_val = adress_add_area.getText().toString().trim();
        if (TextUtils.isEmpty(adress_add_area_val)) {
            ToastUtil.showTextToast("请选择城市区域");
            return;
        }
        String adress_add_detail_val = adress_add_detail.getText().toString().trim();
        if (TextUtils.isEmpty(adress_add_detail_val)) {
            ToastUtil.showTextToast("请输入详细地址");
            return;
        }
        String adress_add_phone_val = adress_add_phone.getText().toString().trim();
        if (TextUtils.isEmpty(adress_add_phone_val)) {
            ToastUtil.showTextToast("请输入联系电话");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("receiptName", adress_add_name_val);
        map.put("reseiptArea", adress_add_area_val);
        map.put("reseiptAddress", adress_add_detail_val);
        map.put("receiprTel", adress_add_phone_val);
        map.put("ifAddress",ifAddress);
        System.out.println("用户id："+userId+"///"+"用户名："+adress_add_name_val
        +"////"+adress_add_area_val+"////"+adress_add_detail_val
        +"///"+adress_add_phone_val+"///"+ifAddress);
        GsonRequest<ReturnCodeBean>gsonRequest = new GsonRequest<ReturnCodeBean>(map, url, ReturnCodeBean.class,
                new Response.Listener<ReturnCodeBean>() {
                    @Override
                    public void onResponse(ReturnCodeBean response) {
                        ToastUtil.showTextToast(response.getMessage());
                        if (response.getCode()==200){
                            setResult(1001);
                            finish();
                        }
                        ToastUtil.showTextToast(response.getMessage());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求失败");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);
    }

    private void loadCityPicker() {
        loadCityJson();
        //选项选择器
        pvOptions = new OptionsPickeView(this);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(15, 0, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickeView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2)
                        + options3Items.get(options1).get(option2).get(options3);
                adress_add_area.setText(tx);
                vMasker.setVisibility(View.GONE);
            }
        });
    }

    private void loadCityJson(){
        try {
            //解析json数据
            InputStream is = getResources().getAssets().open("city.json");

            int available = is.available();

            byte [] b=new byte[available];
            int read = is.read(b);
            //注意格式，utf-8 或者gbk  否则解析出来可能会出现乱码
            String json=new String(b,"GBK");
            Gson gson= new Gson();
            China china = gson.fromJson(json, China.class);
            ArrayList<China.Province> citylist = china.citylist;
            //======省级
            for (China.Province province: citylist
                    ) {
                String provinceName = province.p;

                ArrayList<China.Province.Area> c = province.c;
                //选项1
                options1Items.add(new ProvinceBean(0,provinceName,"",""));
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
                //区级
                //选项2

                ArrayList<String> options2Items_01=new ArrayList<String>();
                if (c!=null) {
                    for (China.Province.Area area : c) {
                        options2Items_01.add(area.n);
                        ArrayList<China.Province.Area.Street> a = area.a;
                        ArrayList<String> options3Items_01_01=new ArrayList<String>();

                        //县级
                        if (a!=null) {
                            for (China.Province.Area.Street street : a
                                    ) {
                                options3Items_01_01.add(street.s);
                            }
                            options3Items_01.add(options3Items_01_01);
                        }else{
                            //县级为空的时候
                            options3Items_01_01.add("");
                            options3Items_01.add(options3Items_01_01);
                        }
                    }
                    options2Items.add(options2Items_01);
                }else{
                    //区级为空的时候  国外
                    options2Items_01.add("");
                }
                options3Items.add(options3Items_01);
                ArrayList<String> options3Items_01_01=new ArrayList<String>();
                options3Items_01_01.add("");
                options3Items_01.add(options3Items_01_01);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
