package com.slr.slrapp.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiiu on 15/11/25.
 * 简单的通用util
 */
public class CommonUtil {

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean notEmpty(List list) {
        return !isEmpty(list);
    }

    //全部为空
    public static boolean isAllEmpty(List... lists) {
        List<Boolean> booleans = new ArrayList<>();

        for (List list : lists) {
            if (isEmpty(list)) {
                booleans.add(Boolean.TRUE);
            }
        }

        return notEmpty(booleans) && booleans.contains(Boolean.FALSE);
    }

    //只有一个为空
    public static boolean isOneEmpty(List... lists) {
        for (List list : lists) {
            if (isEmpty(list)) {
                return true;
            }
        }

        return false;
    }


    //====================================================================
    //====================================================================

    public static boolean isEmpty(String s) {
        return s == null || TextUtils.isEmpty(s);
    }

    public static boolean notEmpty(String s) {
        return s != null && !TextUtils.isEmpty(s.trim());
    }

    public static boolean isAllEmpty(String... strings) {
        List<Boolean> booleans = new ArrayList<>();

        for (String s : strings) {
            booleans.add(CommonUtil.isEmpty(s));
        }

        return notEmpty(booleans) && booleans.contains(Boolean.FALSE);
    }

    //只有一个为空
    public static boolean isOneEmpty(String... strings) {
        for (String s : strings) {
            if (isEmpty(s)) {
                return true;
            }
        }

        return false;
    }

    public static String pidReplace(String pid) {
        if (TextUtils.isEmpty(pid)) {
            return "";
        }

        if (pid.contains("/")) {
            pid = pid.replaceFirst("/", "");
        }

        return pid;
    }


}
