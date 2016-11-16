package com.slr.slrapp.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.UpdateBean;
import com.slr.slrapp.managers.DataCleanManager;
import com.slr.slrapp.managers.mUpdateManager;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.ToastUtil;

import java.io.File;

/**
 * User: Administrator
 * Time: 2016/7/20 0020
 * Description: ${todo}(更多)
 * Version V1.0
 */
public class MyMoreActivity extends BaseActivity {

    private Context context;
    private LinearLayout back;
    private TextView title, dataSize, update;
    private RelativeLayout about, service, check, clear, help, esc;
    private DataCleanManager dataCleanManager;
    private long data;
    private File file;
    private ProgressDialog dialog;
    private UpdateBean updateBean;
    private mUpdateManager updateManager;
    private String mVersion;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_my_more;
    }

    @Override
    public void initView() {
        back = (LinearLayout) findViewById(R.id.title_left);
        title = (TextView) findViewById(R.id.title_text_tv);
        about = (RelativeLayout) findViewById(R.id.more_about);
        service = (RelativeLayout) findViewById(R.id.more_service);
        check = (RelativeLayout) findViewById(R.id.more_check_update);
        clear = (RelativeLayout) findViewById(R.id.more_clear);
        help = (RelativeLayout) findViewById(R.id.more_help);
        esc = (RelativeLayout) findViewById(R.id.more_esc);
        dataSize = (TextView) findViewById(R.id.more_clear_iv);
        update = (TextView) findViewById(R.id.more_check_update_tv);

    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        about.setOnClickListener(this);
        service.setOnClickListener(this);
        check.setOnClickListener(this);
        clear.setOnClickListener(this);
        help.setOnClickListener(this);
        esc.setOnClickListener(this);

    }

    @Override
    public void initData() {
        context = this;
        title.setText(R.string.my_more);
        dataCleanManager = new DataCleanManager();
        String path = BaseApplication.getCachePath();
        file = new File(path);
        data = dataCleanManager.getFolderSize(file);
        dataSize.setText(dataCleanManager.getFormatSize(data));
        dialog = new ProgressDialog(context);
        updateBean = new UpdateBean();
        updateManager = new mUpdateManager();
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this
                    .getPackageName(), 0);
            mVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        update.setText("Version："+mVersion);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.title_left:

                finish();

                break;
            case R.id.more_about:

                Intent intent = new Intent(context, AboutMyselfActivity.class);
                startActivity(intent);

                break;
            case R.id.more_service:

                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_CALL);
                intent1.setData(Uri.parse("tel:" + "0371-5522-7158"));
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);

                break;
            case R.id.more_check_update:


                CheckUpdate();


                break;
            case R.id.more_clear:

                if (dataCleanManager.getFolderSize(file) == 0){
                    ToastUtil.showTextToast("缓存已清空！");
                }else{
                    dataCleanManager.deleteFolderFile(file.getPath(), true);
                }
                dataSize.setText("0.0B");

                break;
           case R.id.more_help:

                Intent intent2 = new Intent(context, FeedBackActivity.class);
                startActivity(intent2);

                break;
           case R.id.more_esc:

               LogOff();


                break;
        }

    }

    // 退出登录
    private void LogOff() {

        Boolean LoginState = context.getSharedPreferences(ContentValues.SP_NAME, 0).getBoolean(ContentValues.IF_IS_LOGINED, false);

        if (LoginState){
            AlertDialog.Builder diaglog = new AlertDialog.Builder(context);
            diaglog.setTitle("确定安全退出？");
            diaglog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //退出成功后将记录退出状态
                    sharedPreferences.edit().putBoolean(ContentValues.IF_IS_LOGINED, false).apply();
                    //关闭用户id
                    sharedPreferences.edit().putString(ContentValues.USER_ID,"").apply();
                    dialog.dismiss();
                    finish();
                }
            });
            diaglog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            });
            diaglog.create().show();
        }else{

            ToastUtil.showTextToast("您已安全退出，请重新登录！");
        }


    }


    // 检查更新
    private void CheckUpdate() {

        updateManager.checkAppUpdate(context, true);

    }


}
