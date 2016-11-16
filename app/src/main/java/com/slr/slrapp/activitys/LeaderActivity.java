package com.slr.slrapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.slr.slrapp.R;
import com.slr.slrapp.utils.ContentValues;
import com.slr.slrapp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LeaderActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private int[] leadImgs={R.mipmap.lead1,R.mipmap.lead2,R.mipmap.lead3,R.mipmap.lead4};
    private List<ImageView> imgList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_leader);
        initData();
        initView();
    }

    private void initView() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);

        view_pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
               ImageView iv= imgList.get(position);
                container.addView(iv);

                if (position==imgList.size()-1){
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent into=new Intent(UiUtils.getContext(),MainActivity.class);
                            startActivity(into);
                            finish();
                        }
                    });
                }




                return  iv;

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView(imgList.get(position));
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });


        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if (position==imgList.size()-1){
                        //进入引导页结束后将状态变为已经进过
                        getSharedPreferences(ContentValues.SP_NAME,0).edit().putBoolean(ContentValues.IF_FIRST_USE,false).apply();
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void initData(){
        for (int i = 0; i <leadImgs.length ; i++) {

            ImageView iv=new ImageView(UiUtils.getContext());
            Picasso.with(UiUtils.getContext()).load(leadImgs[i]).into(iv);
            imgList.add(iv);
        }
    }
}
