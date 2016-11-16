package com.slr.slrapp.managers;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;
import com.slr.slrapp.beans.UpdateBean;
import com.slr.slrapp.gson.ApiUtils;
import com.slr.slrapp.gson.GsonRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * 应用程序更新工具包
 * Created by hkf on 2016/1/29 14:39
 */
@SuppressLint("HandlerLeak")
public class mUpdateManager {

    private static final int DOWN_NOSDCARD = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;

    private static final int DIALOG_TYPE_LATEST = 0;
    private static final int DIALOG_TYPE_FAIL = 1;

    private static mUpdateManager updateManager;

    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    private Context mContext;
    //通知对话框
    private Dialog noticeDialog;
    //下载对话框
    private Dialog downloadDialog;
    //'已经是最新' 或者 '无法获取最新版本' 的对话框
    private Dialog latestOrFailDialog;
    //进度条
    private ProgressBar mProgress;
    //显示下载数值
    private TextView mProgressText;
    //查询动画
    private ProgressDialog mProDialog;
    //进度值
    private int progress;
    //下载线程
    private Thread downLoadThread;
    //终止标记
    private boolean interceptFlag;
    //提示语
    private String updateMsg = "";
    //返回的安装包url
    private String apkUrl = "";
    //下载包保存路径
    private String savePath = "";
    //apk保存完整路径
    private String apkFilePath = "";
    //临时下载文件路径
    private String tmpFilePath = "";
    //下载文件大小
    private String apkFileSize;
    //已下载文件大小
    private String tmpFileSize;

    private int curVersionCode;
    private UpdateBean mUpdate;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    mProgressText.setText(tmpFileSize + "/" + apkFileSize);
                    break;
                case DOWN_OVER:
                    downloadDialog.dismiss();
                    installApk();
                    break;
                case DOWN_NOSDCARD:
                    downloadDialog.dismiss();
                    Toast.makeText(mContext, "无法下载安装文件，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        ;
    };

    public static mUpdateManager getUpdateManager() {
        if (updateManager == null) {
            updateManager = new mUpdateManager();
        }
        updateManager.interceptFlag = false;
        return updateManager;
    }

    /**
     * 显示'已经是最新'或者'无法获取版本信息'对话框
     */
    private void showLatestOrFailDialog(int dialogType) {
        if (latestOrFailDialog != null) {
            //关闭并释放之前的对话框
            latestOrFailDialog.dismiss();
            latestOrFailDialog = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("系统提示");
        if (dialogType == DIALOG_TYPE_LATEST) {
            builder.setMessage("您当前已经是最新版本");
        } else if (dialogType == DIALOG_TYPE_FAIL) {
            builder.setMessage("无法获取版本更新信息");
        }
        builder.setPositiveButton("确定", null);
        latestOrFailDialog = builder.create();
        latestOrFailDialog.show();
    }

    /**
     * 获取当前客户端版本信息
     */
    public void getCurrentVersion() {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext
                    .getPackageName(), 0);
            curVersionCode = info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * 检查App更新
     *
     * @param context
     * @param isShowMsg 是否显示提示消息
     */
    public void checkAppUpdate(final Context context, final boolean isShowMsg) {
        this.mContext = context;
//        sharedPreferences = mContext.getSharedPreferences("", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();

        getCurrentVersion();
        final ProgressDialog checkProgressDialog = new ProgressDialog(mContext);
        if(isShowMsg){
            checkProgressDialog.setCancelable(true);
            checkProgressDialog.setCanceledOnTouchOutside(false);
            checkProgressDialog.setMessage("正在检测，请稍候...");
            checkProgressDialog.show();
        }

        Map<String, String> map = new HashMap<>();
        map.put("appType", "android");
        GsonRequest<UpdateBean> gsonRequest = new GsonRequest<>(map, ApiUtils.Update(), UpdateBean.class,
                new Response.Listener<UpdateBean>() {
                    @Override
                    public void onResponse(UpdateBean update) {
                        if (isShowMsg){
                            checkProgressDialog.dismiss();
                        }
                        mUpdate = update;
                        if (curVersionCode < Integer.parseInt(mUpdate.getEx().getEditionModel())) {
                            apkUrl = mUpdate.getEx().getEditionFile();
                            updateMsg = mUpdate.getMessage();
                            //editor.putBoolean(mContext.getString(R.string.check_new_version_key), true).commit();
                            showNoticeDialog();
                        } else {
                            //editor.putBoolean(mContext.getString(R.string.check_new_version_key), false).commit();
                            if (isShowMsg) {
                                showLatestOrFailDialog(DIALOG_TYPE_LATEST);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (isShowMsg){
                    checkProgressDialog.dismiss();
                }
                Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
        BaseApplication.getInstance().getRequestQueue().add(gsonRequest);

    }

    /**
     * 显示版本更新通知对话框
     */
    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("软件版本更新");
        builder.setMessage(updateMsg);
        builder.setPositiveButton("立即更新", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();
            }
        });
        builder.setNegativeButton("以后再说", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示下载对话框
     */
    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在下载新版本");

        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.update_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        mProgressText = (TextView) v.findViewById(R.id.update_progress_text);

        builder.setView(v);
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        builder.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.show();

        downloadApk();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                String apkName = "SLRApp_" + mUpdate.getEx().getEditionName() + ".apk";
                String tmpApk = "SLRApp_" + mUpdate.getEx().getEditionName() + ".tmp";
                //判断是否挂载了SD卡
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    savePath =BaseApplication.getCachePath()+"/APK/";
//                            Environment.getExternalStorageDirectory().getAbsolutePath() + AppContext.saveFolder +
//                            "/mnt/sdcard/slr/cache";
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    apkFilePath = savePath + apkName;
                    tmpFilePath = savePath + tmpApk;
                }

                //没有挂载SD卡，无法下载文件
                if (apkFilePath == null || apkFilePath == "") {
                    mHandler.sendEmptyMessage(DOWN_NOSDCARD);
                    return;
                }

                File ApkFile = new File(apkFilePath);

                //是否已下载更新文件
                if (ApkFile.exists()) {
                    downloadDialog.dismiss();
                    installApk();
                    return;
                }

                //输出临时下载文件
                File tmpFile = new File(tmpFilePath);
                FileOutputStream fos = new FileOutputStream(tmpFile);

                URL url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                //显示文件大小格式：2个小数点显示
                DecimalFormat df = new DecimalFormat("0.00");
                //进度条下面显示的总文件大小
                apkFileSize = df.format((float) length / 1024 / 1024) + "MB";

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    //进度条下面显示的当前下载文件大小
                    tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
                    //当前进度值
                    progress = (int) (((float) count / length) * 100);
                    //更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        //下载完成 - 将临时下载文件转成APK文件
                        if (tmpFile.renameTo(ApkFile)) {
                            //通知安装
                            mHandler.sendEmptyMessage(DOWN_OVER);
                        }
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);//点击取消就停止下载

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * 下载apk
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android" +
                ".package-archive");
        mContext.startActivity(i);
    }
}
