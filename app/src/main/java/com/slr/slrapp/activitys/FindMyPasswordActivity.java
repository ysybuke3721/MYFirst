package com.slr.slrapp.activitys;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.DefaultBean;
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

public class FindMyPasswordActivity extends BaseActivity {

    private ImageView back;
    private EditText et_user_name;
    private ImageView iv_cancel, new_pw_cancel, sure_pw_cancel;
    private EditText et_security_code;
    private TimeButton tv_get_security_code;
    private EditText et_input_pwd;
    private EditText et_rinput_pwd;
    private TextView tv_next_step;



    @Override
    public int getLayoutResId() {
        return R.layout.activity_find_my_password;
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.back);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        iv_cancel = (ImageView) findViewById(R.id.iv_cancel);
        new_pw_cancel = (ImageView) findViewById(R.id.iv_cancel_newpw);
        sure_pw_cancel = (ImageView) findViewById(R.id.iv_cancel_newpw_sure);
        new_pw_cancel.setVisibility(View.INVISIBLE);
        sure_pw_cancel.setVisibility(View.INVISIBLE);
        iv_cancel.setVisibility(View.INVISIBLE);
        et_security_code = (EditText) findViewById(R.id.et_security_code);
        tv_get_security_code = (TimeButton) findViewById(R.id.tv_get_security_code);
        et_input_pwd = (EditText) findViewById(R.id.et_input_pwd);
        et_rinput_pwd = (EditText) findViewById(R.id.et_rinput_pwd);
        tv_next_step = (TextView) findViewById(R.id.tv_next_step);

        tv_next_step.setOnClickListener(this);
        back.setOnClickListener(this);
        iv_cancel.setOnClickListener(this);
        new_pw_cancel.setOnClickListener(this);
        sure_pw_cancel.setOnClickListener(this);
        tv_get_security_code.setOnClickListener(this);

        tv_get_security_code.onCreate(savedInstanceState);
        tv_get_security_code.setEnabled(false);
    }

    @Override
    public void initListener() {
        //输入框判空
        submit();

        //用户名监听
        et_user_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //控制叉号的显示与否
                if (TextUtils.isEmpty(charSequence)) {
                    iv_cancel.setVisibility(View.INVISIBLE);
                } else {
                    iv_cancel.setVisibility(View.VISIBLE);
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
                            tv_get_security_code.setEnabled(false);
                        }else {
                            tv_get_security_code.setEnabled(true);
                        }
                    }
                }

            }
        });

        //密码监听
        et_input_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    new_pw_cancel.setVisibility(View.INVISIBLE);
                } else {
                    new_pw_cancel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    ToastUtil.showTextToast("密码不能为空");
                }
            }
        });

        //确认密码监听
        et_rinput_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    sure_pw_cancel.setVisibility(View.INVISIBLE);
                } else {
                    sure_pw_cancel.setVisibility(View.VISIBLE);
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

        tv_get_security_code.setTextBefore("获取验证码");
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next_step:  //点击下一步

                submit();

                break;
            case R.id.iv_cancel_newpw:   //点击新密码后的取消
                et_input_pwd.setText("");
                break;
            case R.id.iv_cancel_newpw_sure:   //点击确认密码后的取消
                et_rinput_pwd.setText("");
                break;
            case R.id.iv_cancel:   //点击账号后的取消
                et_user_name.setText("");
                break;
            case R.id.tv_get_security_code:   //点击获取验证码
                //TODO
                //TODO  获取验证码
                String tele = et_user_name.getText().toString().trim();
                if (TextUtils.isEmpty(tele)) {
                    ToastUtil.showTextToast("请输入您的手机号码");
                    return;
                } else if (!MatchUtils.isPhoneNumberValid(tele)) {
                    ToastUtil.showTextToast("请输入正确手机号码");
                    return;
                } else {
                    tv_get_security_code.setTextAfter("秒").setLenght(60 * 1000);
                    getEnsureNum(tele);
                }
                break;
            case R.id.back:   //点击左上角返回
                finish();
                break;
        }
    }


    //输入框的判断
    private void submit() {
        // validate
        String name = et_user_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showTextToast("请输入您的手机号码");
            return;
        }

        String code = et_security_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showTextToast("请输入验证码");
            return;
        }

        String pwd = et_input_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showTextToast("请输入新密码");
            return;
        }

        String  pwd1 = et_rinput_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd1)) {
            ToastUtil.showTextToast("请确认新密码");
            return;
        }

        // TODO validate success, do something

        FindPW(name, code, pwd, pwd1 );

    }

    private void getEnsureNum(String tel) {
        System.out.println("找回密码："+ContentValues.ENSURE_NUM_URL_FIND+"?data="+tel);
        OkHttpUtils.get().url(ContentValues.ENSURE_NUM_URL_FIND).addParams("data", tel).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.showTextToast("请求网络失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    EnsureNumBean bean1 = gson.fromJson(response, EnsureNumBean.class);
                    int code = bean1.getCode();
                    switch (code) {
                        case 200:
                            ToastUtil.showTextToast("短信发送成功");
                            //TODO  让验证码倒计时停

                            break;
                        default:
                            ToastUtil.showTextToast(bean1.getMessage());
                            break;
                    }
                }

            }
        });


    }

    private class EnsureNumBean {

        /**
         * code : 200
         * message : 短信发送成功
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

    private void FindPW(String tel, String yzm, String newpw, String surepw){

        System.out.println("找回密码："+ContentValues.FIND_NUM_URL);
        Map<String, String> map = new HashMap<>();
        map.put("data", tel);
        map.put("messageVerify", yzm);
        map.put("newPass", newpw);
        map.put("confirmPass", surepw);
        GsonRequest<DefaultBean> gsonRequest = new GsonRequest<>(map,
                ContentValues.FIND_NUM_URL,
                DefaultBean.class,
                new Response.Listener<DefaultBean>() {
                    @Override
                    public void onResponse(DefaultBean session) {

                        ToastUtil.showTextToast(session.getMessage());

                        if (session.getCode()==200) {
                            finish();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.showTextToast("网络异常！");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);



    }
}
