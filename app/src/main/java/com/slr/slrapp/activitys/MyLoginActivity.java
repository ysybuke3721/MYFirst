package com.slr.slrapp.activitys;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.slr.slrapp.R;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.MatchUtils;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 用户登录
 *
 */
public class MyLoginActivity extends BaseActivity {

    private CircleImageView iv_user_head;
    private EditText et_user_name;
    private ImageView iv_cancel_name;
    private EditText et_user_pwd;
    private ImageView iv_cancel_pwd;
    private TextView tv_forget_pwd;
    private TextView btn_dl;
    private TextView btn_zc;

//    private ImageView iv_weibo;
//    private ImageView iv_weixin;
//    private ImageView iv_qq;

    private Class nextActivity;
    private Boolean SkipState;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_login;
    }

    @Override
    public void initView() {
        iv_user_head = (CircleImageView) findViewById(R.id.iv_user_head);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        iv_cancel_name = (ImageView) findViewById(R.id.iv_cancel_name);
        et_user_pwd = (EditText) findViewById(R.id.et_user_pwd);
        iv_cancel_pwd = (ImageView) findViewById(R.id.iv_cancel_pwd);
        tv_forget_pwd = (TextView) findViewById(R.id.tv_forget_pwd);
        btn_dl = (TextView) findViewById(R.id.btn_finish);
        btn_zc = (TextView) findViewById(R.id.btn_zc);

        //第三方登录按钮
//        iv_weibo = (ImageView) findViewById(R.id.iv_weibo);
//        iv_weixin = (ImageView) findViewById(R.id.iv_weixin);
//        iv_qq = (ImageView) findViewById(R.id.iv_qq);

//        iv_user_head.setOnClickListener(this);
        btn_dl.setOnClickListener(this);
        btn_zc.setOnClickListener(this);
        iv_cancel_name.setOnClickListener(this);
        iv_cancel_pwd.setOnClickListener(this);
        tv_forget_pwd.setOnClickListener(this);

//        iv_weibo.setOnClickListener(this);
//        iv_weixin.setOnClickListener(this);
//        iv_qq.setOnClickListener(this);

        /*使状态栏半透明
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏*/
        StatusBarUtil.setTranslucent(this, 0);

        nextActivity = (Class) getIntent().getExtras().getSerializable("CLASS");
        SkipState = getIntent().getExtras().getBoolean("SkipState");
    }

    @Override
    public void initListener() {

        //账号监听
        et_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    iv_cancel_name.setVisibility(View.INVISIBLE);
                } else {
                    iv_cancel_name.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (TextUtils.isEmpty(editable)) {
                    ToastUtil.showTextToast("请输入手机号");
                } else {
                    String tel = editable.toString();
                    if (tel.length() >= 11) {
                        boolean if_tel_right = MatchUtils.isPhoneNumberValid(tel);
                        if (!if_tel_right) {
                            ToastUtil.showTextToast("请输入正确的手机号码");
                        }
                    }
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
                    ToastUtil.showTextToast("请输入密码");
                } else {
                    String pwd = editable.toString();
//                    if (pwd.length() < 6) {
//                        ToastUtil.showTextToast("密码长度至少六位");
//                    }
                    if (pwd.length() > 12) {
                        ToastUtil.showTextToast("密码过长");
                    }
                }
            }
        });


    }

    @Override
    public void initData() {

        if (this.getSharedPreferences(ContentValues.SP_NAME,0).getBoolean(ContentValues.HAS_FACE,false)){
            Picasso.with(this).load(this.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.FACE, null))
                    .error(R.mipmap.icon_default).into(iv_user_head);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_user_head:   //点击头像
//                ToastUtil.showTextToast("头像");
//
//                break;
            case R.id.iv_cancel_name:  //账号后的叉号
                et_user_name.setText("");
                break;
            case R.id.iv_cancel_pwd:   //密码后的叉号
                et_user_pwd.setText("");
                break;

            case R.id.btn_finish:   //点击登录
                //TODO  先模拟登录

                String username = et_user_name.getText().toString().trim();
                String password = et_user_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    ToastUtil.showTextToast("手机号不能为空");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(username)) {
                    ToastUtil.showTextToast("手机号格式不正确");
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showTextToast("密码不能为空");
                    return;
                } else if (password.length() < 6) {
                    ToastUtil.showTextToast("密码长度至少六位");
                    return;
                }
                ToLogin(username, password);
                break;
            case R.id.btn_zc:   //点击注册
                Intent intent = new Intent(context, MyRegisterActivity.class);
                startActivityForResult(intent, ContentValues.OPEN_REGISTER);

                break;

            case R.id.tv_forget_pwd:  //点击忘记密码
                Intent intent2 = new Intent(context, FindMyPasswordActivity.class);
                startActivity(intent2);
                break;

//            case R.id.iv_weibo:  //点击微博
//                ToastUtil.showTextToast("微博");
//
//
//                break;
//            case R.id.iv_weixin: //点击微信
//                ToastUtil.showTextToast("微信");
//
//                break;
//            case R.id.iv_qq:   //点击qq
//
//                ToastUtil.showTextToast("QQ");
//                break;
        }

    }


    private void ToLogin(String username, String password) {
        String url = ContentValues.LOGIN_URL;
        OkHttpUtils.post().url(url).addParams("username", username).addParams("password", password).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showTextToast("请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    LoginCallBackBean bean = gson.fromJson(response, LoginCallBackBean.class);
                    int code = bean.getCode();
                    switch (code) {
                        case 200:  //登录成功
                            ToastUtil.showTextToast("登录成功！");
                            //登录成功后将记录登录状态
                            sharedPreferences.edit().putBoolean(ContentValues.IF_IS_LOGINED, true).apply();
                            //记录用户id
                            sharedPreferences.edit().putString(ContentValues.USER_ID, String.valueOf(bean.getUserId())).apply();

                            if (SkipState){
                                Intent intent1 = new Intent(context, nextActivity);
                                startActivity(intent1);
                            }

                            finish();
                            break;
                        case 101:
                            ToastUtil.showTextToast("账户名不存在！");
                            break;
                        case 102:
                            ToastUtil.showTextToast("账户名密码不匹配");
                            break;
                        default:
                            ToastUtil.showTextToast("未知错误");
                            break;
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == ContentValues.TO_LOGIN_PAGE) {
            //从注册页面传递回来账号
            String name = data.getStringExtra(ContentValues.USER_TEL);
            et_user_name.setText(name);

        }

    }


    private class LoginCallBackBean {


        /**
         * message : 登录成功
         * token : 184117
         * userId : 5
         * code : 200
         */

        private String message;
        private int token;
        private int userId;
        private int code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getToken() {
            return token;
        }

        public void setToken(int token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

    }
    }
