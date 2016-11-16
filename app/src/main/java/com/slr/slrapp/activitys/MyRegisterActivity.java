package com.slr.slrapp.activitys;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.DefaultBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.MatchUtils;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.TimeButton;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class MyRegisterActivity extends BaseActivity {

//    private CircleImageView iv_user_head;
//    private TextView tv_change_head;
    private EditText et_user_name;
    private ImageView iv_cancel_name;
    private EditText et_user_mail;
    private ImageView iv_cancel_mail;
    private EditText et_user_pwd;
    private ImageView iv_cancel_pwd;
    // 推荐人
    private EditText et_user_referee;
    private ImageView iv_cancel_referee;
    private RadioButton rb_rember_pwd;
    private ImageView back;
    //验证码  行
    private EditText et_ensuer_num;
    private TimeButton tv_get_ensure_num;

    private TextView tv_item;
    private TextView btn_finish;
    private ImageView iv_rember_name;
    private LinearLayout ll_rember_name;
    //是否记住密码
    private boolean if_rm = sharedPreferences.getBoolean(ContentValues.IF_REMBER_NAME, true);
//private boolean if_rm=false;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_register;
    }

    @Override
    public void initView() {

        //让状态栏背景和整个背景一致（沉浸式状态栏）
        StatusBarUtil.setTranslucent(this, 0);

        et_ensuer_num = (EditText) findViewById(R.id.et_ensuer_num);
        tv_get_ensure_num = (TimeButton) findViewById(R.id.tv_get_ensure_num);

        tv_get_ensure_num.onCreate(savedInstanceState);
        tv_get_ensure_num.setEnabled(false);

//        iv_user_head = (CircleImageView) findViewById(R.id.iv_user_head);
//        tv_change_head = (TextView) findViewById(R.id.tv_change_head);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        iv_cancel_name = (ImageView) findViewById(R.id.iv_cancel_name);
        et_user_mail = (EditText) findViewById(R.id.et_user_mail);
        iv_cancel_mail = (ImageView) findViewById(R.id.iv_cancel_mail);
        et_user_pwd = (EditText) findViewById(R.id.et_user_pwd);
        iv_cancel_pwd = (ImageView) findViewById(R.id.iv_cancel_pwd);
        // 推荐人
        et_user_referee = (EditText) findViewById(R.id.et_user_referee);
        iv_cancel_referee = (ImageView) findViewById(R.id.iv_cancel_referee);
        back = (ImageView) findViewById(R.id.register_back);

        rb_rember_pwd = (RadioButton) findViewById(R.id.rb_rember_pwd);
//        ll_rember_name = (LinearLayout) findViewById(R.id.ll_rember_name);
//        iv_rember_name = (ImageView) findViewById(R.id.iv_rember_name);
        tv_item = (TextView) findViewById(R.id.tv_item);
        btn_finish = (TextView) findViewById(R.id.btn_finish);

        btn_finish.setOnClickListener(this);
//        tv_change_head.setOnClickListener(this);
//        iv_user_head.setOnClickListener(this);
        //添加下划线
        tv_item.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_item.setOnClickListener(this);
        iv_cancel_name.setOnClickListener(this);
        iv_cancel_mail.setOnClickListener(this);
        iv_cancel_pwd.setOnClickListener(this);
        iv_cancel_referee.setOnClickListener(this);
        rb_rember_pwd.setOnClickListener(this);
        tv_get_ensure_num.setOnClickListener(this);
        back.setOnClickListener(this);

        rb_rember_pwd.setChecked(if_rm);

        tv_get_ensure_num.setTextBefore("获取验证码");

        //   ll_rember_name.setOnClickListener(this);

//        if(if_rember_pwd){
//            iv_rember_name.setImageResource(R.mipmap.rm_pwd_1);
//        }else {
//            iv_rember_name.setImageResource(R.mipmap.rm_pwd_0);
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //初始化是否选中记住密码
//        if(if_rember_pwd){
//            iv_rember_name.setImageResource(R.mipmap.rm_pwd_1);
//        }else {
//            iv_rember_name.setImageResource(R.mipmap.rm_pwd_0);
//        }
    }

    @Override
    public void initListener() {


        //用户名监听
        et_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //控制叉号的显示与否
                if (TextUtils.isEmpty(charSequence)) {
                    iv_cancel_name.setVisibility(View.INVISIBLE);
                } else {
                    iv_cancel_name.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(editable)) {
                    ToastUtil.showTextToast("手机号码不能为空");
                    return;
                } else {
                    String pwd = editable.toString();
                    if (pwd.length() >= 11) {
                        boolean if_right = MatchUtils.isPhoneNumberValid(pwd);
                        if (!if_right) {
                            ToastUtil.showTextToast("请输入正确的手机号码");
                            tv_get_ensure_num.setEnabled(false);
                        }else {
                            tv_get_ensure_num.setEnabled(true);
                        }
                    }
                }

            }
        });
//        //邮箱监听
//        et_user_mail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                String mail = editable.toString();
//                boolean if_mail_right=MatchUtils.isEmail(mail);
//                if (mail.length() == 0) {
//                    ToastUtil.showTextToast("邮箱不能为空");
//                    return;
//                }
//                if (!if_mail_right){
//                    ToastUtil.showTextToast("邮箱格式不正确");
//                    return;
//                }
//            }
//        });

        //验证码输入框监听
        et_ensuer_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    ToastUtil.showTextToast("请输入验证码");
                }

            }
        });


        //密码监听
        et_user_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    iv_cancel_pwd.setVisibility(View.INVISIBLE);
                } else {
                    iv_cancel_pwd.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    ToastUtil.showTextToast("密码不能为空");
                }
            }
        });


    }

    @Override
    public void initData() {


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_back:

                finish();

                break;
//            case R.id.iv_user_head:   //点击头像
//                ToastUtil.showTextToast("头像");
//                break;
//            case R.id.tv_change_head:  //点击修改头像
//                //TODO
//                break;

            case R.id.iv_cancel_name:  //账号后的叉号
                et_user_name.setText("");
                break;
//            case R.id.iv_cancel_mail:  //邮箱后的叉号
//                et_user_mail.setText("");
//                break;
            case R.id.iv_cancel_pwd:   //密码后的叉号
                et_user_pwd.setText("");
                break;
            case R.id.iv_cancel_referee:   //推荐人后的叉号
                et_user_referee.setText("");
                break;

            case R.id.tv_get_ensure_num:  //点击获取验证码

                //TODO  获取验证码
                String tele = et_user_name.getText().toString().trim();
                if (TextUtils.isEmpty(tele)) {
                    ToastUtil.showTextToast("请输入您的手机号码");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(tele)) {
                    ToastUtil.showTextToast("请输入正确手机号码");
                    return;
                } else {
                    tv_get_ensure_num.setTextAfter("秒").setLenght(60 * 1000);
                    getEnsureNum(tele);
                }


                break;
            case R.id.btn_finish:   //点击完成
//                ToastUtil.showTextToast("完成");
                //TODO  判断账号密码验证码是否为空，还要判断格式是否正确
                String tel = et_user_name.getText().toString().trim();
                String ensurenum = et_ensuer_num.getText().toString().trim();
//                String mail = et_user_mail.getText().toString().trim();
                String pwd = et_user_pwd.getText().toString().trim();
                String referee = et_user_referee.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    ToastUtil.showTextToast("请输入您的手机号码");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(tel)) {
                    ToastUtil.showTextToast("请输入正确手机号码");
                    return;
                }
//                if (TextUtils.isEmpty(mail)) {
//                    ToastUtil.showTextToast("邮箱不能为空");
//                    return;
//                }
                if (TextUtils.isEmpty(ensurenum)) {
                    ToastUtil.showTextToast("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.showTextToast("密码不能为空");
                    return;
                } else if (pwd.length() < 6) {
                    ToastUtil.showTextToast("密码长度至少为六位");
                    return;
                }

                if (referee.length()!=0||referee!=null){
                    if (!MatchUtils.isPhoneNumberValid(tel)) {
                        ToastUtil.showTextToast("请输入正确手机号码");
                        return;
                    }
                }else{
                    referee = "";
                }


                //TODO  点击完成，请求网络
                ToRegist(tel.trim(), ensurenum, pwd, referee.trim());


                break;


            case R.id.tv_item:    //点击用户协议
                Intent intent = new Intent(this, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("WEBVIEW", ApiUtils.FWXY);
                bundle.putString("TITLE", "服务协议");
                intent.putExtras(bundle);
                startActivity(intent);

                break;

            case R.id.rb_rember_pwd:

                if (if_rm) {
                    //TODO  不记住密码
                    rb_rember_pwd.setChecked(false);
                } else {
                    //TODO　记住密码
                    rb_rember_pwd.setChecked(true);


                }
                if_rm = !if_rm;
                sharedPreferences.edit().putBoolean(ContentValues.IF_REMBER_NAME, if_rm).apply();

                break;
        }

    }


    private void ToRegist(final String tel, String ensuer_num, String pwd, final String referee) {
        String url = ContentValues.REGISTER_URL;
        System.out.println("注册："+url+"////"+tel+"////"+ensuer_num+"/////"+pwd+"////"+referee);
        OkHttpUtils.post().url(url).addParams("telephone", tel).addParams("messageVerify", ensuer_num).addParams("password", pwd).addParams("recommends", referee).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showTextToast("请求网络失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    RegistCallBackBean bean = gson.fromJson(response, RegistCallBackBean.class);
                    int code = bean.getCode();
                    if (code == 200) {
                        ToastUtil.showTextToast("注册成功！");
                        //TODO  注册成功后的操作
                        Intent intent = new Intent();
                        intent.putExtra(ContentValues.USER_TEL, tel);
                        MyRegisterActivity.this.setResult(ContentValues.TO_LOGIN_PAGE, intent);
                        finish();

                    } else {
                        ToastUtil.showTextToast(bean.getMessage());
                    }
                }


            }


        });
    }

    ;


    private void getEnsureNum(String tel) {

        Map<String, String> map = new HashMap<>();
        map.put("telephone", tel);
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ContentValues.ENSURE_NUM_URL,
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常！");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);

    }


    private class RegistCallBackBean {

        /**
         * code : 200
         * message : 注册成功！
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    @Override
    protected void onDestroy() {
        tv_get_ensure_num.onDestroy();
        super.onDestroy();
    }
}





