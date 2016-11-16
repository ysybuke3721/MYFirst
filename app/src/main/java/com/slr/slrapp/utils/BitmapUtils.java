package com.slr.slrapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * User: Administrator
 * Time: 2016/7/2 0002
 * Description: ${图片处理：高斯模糊、圆形图片}
 * Version V1.0
 */
public class BitmapUtils {

    /**
     *
     * Time: 2016/7/2 0002 上午 9:14
     * Description: ${
     * 高斯模糊：RenderScript
     * RenderScript是Android在API 11之后加入的，用于高效的图片处理，包括模糊、混合、矩阵卷积计算
     * }
     * param: ${Context, Bitmap}
     * return: ${Bitmap}
     */
    public Bitmap blurBitmap(Context context, Bitmap bitmap){

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(25.f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;


    }


//    /**
//     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
//     *
//     * A.网络路径: url="http://blog.foreverlove.us/girl2.png" ;
//     *
//     * B.本地路径:url="file://mnt/sdcard/photo/image.png";
//     *
//     * C.支持的图片格式 ,png, jpg,bmp,gif等等
//     *
//     * @param url
//     * @return bitmap
//     */
//    public static Bitmap GetLocalOrNetBitmap(String url, int i) {
//        Bitmap bitmap = null;
//        InputStream in = null;
//        BufferedOutputStream out = null;
//        try {
//            in = new BufferedInputStream(new URL(url).openStream(), 2 * 1024);
//            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
//            out = new BufferedOutputStream(dataStream);
//            copy(in, out);
//            out.flush();
//            byte[] data = dataStream.toByteArray();
//            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            data = null;
//            // 转换圆角图片
//            if (i == 0) {
//                if (bitmap != null) {
//
//                    bitmap = getBitmap(bitmap, 10000);
//                }
//
//            }
//            return bitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static void copy(InputStream in, OutputStream out) throws IOException {
//        byte[] b = new byte[2 * 1024];
//        int read;
//        while ((read = in.read(b)) != -1) {
//            out.write(b, 0, read);
//        }
//    }
//
//    /**
//     * @param bitmap
//     * @param roundPx
//     *            获取圆角图片
//     */
//    public static Bitmap getBitmap(Bitmap bitmap, float roundPx) {
//        int w = bitmap.getWidth();
//        int h = bitmap.getHeight();
//        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect rect = new Rect(0, 0, w, h);
//        final RectF rectF = new RectF(rect);
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, rect, rect, paint);
//
//        return output;
//    }
//

}
