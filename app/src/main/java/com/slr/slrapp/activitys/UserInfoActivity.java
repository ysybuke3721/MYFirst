package com.slr.slrapp.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.ReturnCodeBean;
import com.slr.slrapp.beans.UserInfoBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;
import com.slr.slrapp.gson.MultipartRequest;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;
import com.slr.slrapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * UserInfoBean: Administrator
 * Time: 2016/7/5 0005
 * Description: ${todo}(用户详情：更改头像，显示账号，姓名，余额等功能)
 * Version V1.0
 */
public class UserInfoActivity extends BaseActivity {

    private RelativeLayout mUserinfo_face, mUserinfo_username, mUserinfo_name, mUserinfo_balance, mUserinfo_adress, mUserinfo_signature;
    private CircleImageView mUserinfo_face_iv;
    private TextView mUserinfo_username_iv, mUserinfo_balance_iv, mUserinfo_adress_iv, title_right_bt;
    private EditText mUserinfo_signature_iv, mUserinfo_name_et;
    private Context context;
    private LinearLayout back, title_right;
    private String oldSignature;
    private String oldNickName;

    /**
     * 请求码
     */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;
    private File file;
    private File filea;
    private String image, key = "face", imagename;
    private String CachePath = BaseApplication.getCachePath()+"/photos/";

    // 用户id
    private String userId ;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_userinfo;
    }

    @Override
    public void initView() {

        mUserinfo_face = (RelativeLayout) findViewById(R.id.userinfo_face);
        mUserinfo_face_iv = (CircleImageView) findViewById(R.id.userinfo_face_iv);
        mUserinfo_username = (RelativeLayout) findViewById(R.id.userinfo_username);
        mUserinfo_username_iv = (TextView) findViewById(R.id.userinfo_username_iv);
        mUserinfo_name = (RelativeLayout) findViewById(R.id.userinfo_name);
        mUserinfo_name_et = (EditText) findViewById(R.id.userinfo_nickname_et);
        mUserinfo_balance = (RelativeLayout) findViewById(R.id.userinfo_balance);
        mUserinfo_balance_iv = (TextView) findViewById(R.id.userinfo_balance_iv);
        mUserinfo_adress = (RelativeLayout) findViewById(R.id.userinfo_adress);
        mUserinfo_adress_iv = (TextView) findViewById(R.id.userinfo_adress_iv);
        mUserinfo_signature = (RelativeLayout) findViewById(R.id.userinfo_signature);
        mUserinfo_signature_iv = (EditText) findViewById(R.id.userinfo_signature_iv);
        back = (LinearLayout) findViewById(R.id.title_left);
        title_right = (LinearLayout) findViewById(R.id.title_right);
        title_right_bt = (TextView) findViewById(R.id.title_right_bt);
        title_right.setVisibility(View.VISIBLE);
        mUserinfo_signature_iv.setEnabled(false);
        mUserinfo_name_et.setEnabled(false);
    }

    @Override
    public void initListener() {
        mUserinfo_face.setOnClickListener(this);
        mUserinfo_username.setOnClickListener(this);
        mUserinfo_name.setOnClickListener(this);
        mUserinfo_balance.setOnClickListener(this);
        mUserinfo_adress.setOnClickListener(this);
        back.setOnClickListener(this);
        title_right.setOnClickListener(this);
    }

    @Override
    public void initData() {
        context = this;
        userId=context.getSharedPreferences(ContentValues.SP_NAME, 0).getString(ContentValues.USER_ID, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(ApiUtils.GetUser(userId));
    }

    private void loadData(String url) {
        System.out.println("用户详情："+url);
        GsonRequest<UserInfoBean> gsonRequest = new GsonRequest<UserInfoBean>(url, UserInfoBean.class,
                new Response.Listener<UserInfoBean>() {
                    @Override
                    public void onResponse(UserInfoBean response) {
                        if (response.getCode() == 200) {
                            UserInfoBean.UserBean userBean = response.getUser();
                            Picasso.with(context).load(userBean.getHead()).error(R.mipmap.icon_default).into(mUserinfo_face_iv);
                            mUserinfo_username_iv.setText(userBean.getUserName());
                            mUserinfo_name_et.setText(userBean.getNickName());
                            mUserinfo_balance_iv.setText(String.valueOf(userBean.getRemainMoney()));
                            mUserinfo_adress_iv.setText(userBean.getDefaultAddress());
                            mUserinfo_signature_iv.setText(String.valueOf(userBean.getSignature()));
                            oldSignature = String.valueOf(userBean.getSignature());
                            oldNickName = String.valueOf(userBean.getNickName());
                        }else{
                            ToastUtil.showTextToast(response.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showTextToast("网络请求失败");
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.title_left:
                finish();
                break;
            case R.id.title_right:
                if (title_right_bt.getText().toString().equals("保存")) {
                    if (mUserinfo_signature_iv.getText().toString().trim().equals(oldSignature)&&
                            mUserinfo_name_et.getText().toString().trim().equals(oldNickName)) {
                        title_right_bt.setText("编辑");
                        mUserinfo_signature_iv.setEnabled(false);
                        mUserinfo_name_et.setEnabled(false);
                    } else {
                        String url = ApiUtils.UpdataUser();
                        System.out.println("更新用户信息："+url+"/////"+userId+"////"+mUserinfo_signature_iv.getText().toString().trim()
                        +"////"+mUserinfo_name_et.getText().toString().trim());
                        Map<String, String> map = new HashMap<>();
                        map.put("userId", userId);
                        map.put("nickName", mUserinfo_name_et.getText().toString().trim());
                        map.put("signature", mUserinfo_signature_iv.getText().toString().trim());
                        GsonRequest<ReturnCodeBean> gsonRequest = new GsonRequest<ReturnCodeBean>(map, url, ReturnCodeBean.class,
                                new Response.Listener<ReturnCodeBean>() {
                                    @Override
                                    public void onResponse(ReturnCodeBean response) {
                                        ToastUtil.showTextToast(response.getMessage());
                                        if (response.getCode() == 200) {
                                            title_right_bt.setText("编辑");
                                            mUserinfo_signature_iv.setEnabled(false);
                                            mUserinfo_name_et.setEnabled(false);
                                            oldSignature = mUserinfo_signature_iv.getText().toString().trim();
                                        }
                                        ToastUtil.showTextToast(response.getMessage());
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);
                    }
                } else {
                    mUserinfo_signature_iv.setEnabled(true);
                    mUserinfo_name_et.setEnabled(true);
                    title_right_bt.setText("保存");
                }
                break;
            case R.id.userinfo_face:
                showPopwindow();
                break;
            case R.id.userinfo_username:

                break;
            case R.id.userinfo_name:

                break;
            case R.id.userinfo_balance:

                break;
            case R.id.userinfo_adress:
                SkipActivity(MyAdressAddActivity.class);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    /**
     * Time: 2016/7/5 0005 下午 2:44
     * Description: ${todo}(界面跳转)
     * param: ${class}
     * return: ${return_type}
     */
    private void SkipActivity(Class c) {

        Intent intent = new Intent(context, c);
        startActivity(intent);

    }

    /**
     * Time: 2016/7/21 0021 下午 4:33
     * Description: ${todo}(从底部向上滑出PopupWindow)
     * param: ${tags}
     * return: ${return_type}
     */
    private void showPopwindow() {

        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.pop_take_photo, null);

        Button btnCamera = (Button) popView.findViewById(R.id.pop_paizhao);
        Button btnAlbum = (Button) popView.findViewById(R.id.pop_tuku);
        Button btnCancel = (Button) popView.findViewById(R.id.pop_cancel);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView, width, height * 2 / 5);
        popWindow.setAnimationStyle(R.style.take_photo_anim);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置允许在外点击消失

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {

                popWindow.dismiss();
                switch (v.getId()) {
                    case R.id.pop_paizhao:
                        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断存储卡是否可以用，可用进行存储
                        String state = Environment.getExternalStorageState();
                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                            file = new File(CachePath);
                            if (!file.exists())
                            file.mkdirs();
                            filea = new File(CachePath, getPhotoNmae());
                            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filea));
                        } else {
                            ToastUtil.showTextToast(context, "未找到存储卡，无法存储照片");
                        }
                        startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
                        break;
                    case R.id.pop_tuku:

                        Intent intentFromGallery = new Intent();
                        intentFromGallery.setType("image/*"); // 设置文件类型
                        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
                        break;
                    case R.id.pop_cancel:

                        break;
                }
            }
        };

        btnCamera.setOnClickListener(listener);
        btnAlbum.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        // 结果码不等于取消时候
        if (resultCode != this.RESULT_CANCELED) {
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    // 判断存储卡是否可以用，可用进行存储
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        startPhotoZoom(Uri.fromFile(filea));
                    } else {
                        ToastUtil.showTextToast(context, "未找到存储卡，无法存储照片！");
                    }
                    break;
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case RESULT_REQUEST_CODE: // 图片缩放完成后
                    ToastUtil.showTextToast(context, "图片缩放完成后");
                    if (data != null) {
                        imagename = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                        keepImage(data, CachePath);
                        // 开始上传图片
                        String url = ApiUtils.UpdataHead();
                        MultipartRequest request = new MultipartRequest(url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                ReturnCodeBean returnCodeBean = gson.fromJson(response, ReturnCodeBean.class);
                                ToastUtil.showTextToast(returnCodeBean.getMessage());
                                if (returnCodeBean.getCode()==200){
                                    sharedPreferences.edit().putBoolean(ContentValues.HAS_FACE, true).apply();
                                    System.out.println("上传图片路径："+image);
                                    Picasso.with(context).load(new File(image)).error(R.mipmap.icon_default).into(mUserinfo_face_iv);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ToastUtil.showTextToast("网络请求失败");
                            }
                        });
                        request.addParam("userId", userId);
                        request.addFile("head", image);
                        BaseApplication.getInstance().getRequestQueue().add(request);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 将所得图片写入缓存文件中
     **/
    private void keepImage(Intent data, String path) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        FileOutputStream b = null;
        File newFile = new File(path);
        if (!newFile.exists()){
            newFile.mkdirs();// 创建目录
        }
        image = path + imagename;
        System.out.println("上传图片路径："+image);
        try {
            b = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据100%压缩写入文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 170);
        intent.putExtra("outputY", 170);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    /**
     * 设置缓存图片名字
     **/
    private String getPhotoNmae() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

}
