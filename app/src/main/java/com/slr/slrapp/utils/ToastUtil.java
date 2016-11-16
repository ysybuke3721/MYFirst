package com.slr.slrapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	//静态吐司，解决连续toast的问题
	private static Toast toast = null;

	public static void showTextToast(Context context, String msg) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}
	public static void showTextToast( String msg) {
		if (toast == null) {
			toast = Toast.makeText(UiUtils.getContext(), msg, Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}
}
