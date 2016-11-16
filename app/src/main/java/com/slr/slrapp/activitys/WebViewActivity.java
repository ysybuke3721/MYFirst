package com.slr.slrapp.activitys;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slr.slrapp.BaseApplication;
import com.slr.slrapp.R;

public class WebViewActivity extends Activity implements OnClickListener {
	//设置标题
    private LinearLayout back;
    private TextView title;
	private WebView webview;
	private String titlename,url;
	private BaseApplication baseApplication = new BaseApplication();
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		init();
//	    dialog = baseApplication.getProgressDialog(R.string.loading);
//	    dialog.show();
		ShowWV(webview, url);
	}



	private void init() {
		// TODO Auto-generated method stub

		url =getIntent().getStringExtra("WEBVIEW");
	    titlename = getIntent().getStringExtra("TITLE");
	    
		title = (TextView) findViewById(R.id.title_text_tv);
        back = (LinearLayout) findViewById(R.id.title_left);
        title.setText(titlename);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        
		webview = (WebView) findViewById(R.id.user_webview);
		
		//使WebView能自适应手机屏幕大小
		webview.getSettings().setUseWideViewPort(true);
		// 添加javaScript支持
		webview.getSettings().setJavaScriptEnabled(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_left:
			finish();
			break;

		default:
			break;
		}
	}
	
	private  void ShowWV(WebView webview,String url){
		System.out.println(url);
        //WebView加载web资源
       webview.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
       webview.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
             view.loadUrl(url);
            return true;
        }
       });
	}

		@Override
		public void onDestroy() {
			super.onDestroy();
		}

}
