package com.example.acer.automobilesalesystem;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by acer on 3/3/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images={R.drawable.eon_slider,R.drawable.in_gal_gs_ext_view_03,R.drawable.in_gal_ext_i20activ_03,R.drawable.in_gal_ext_ib_a01,R.drawable.in_gal_ext_i20activ_06,R.drawable.in_gal_ext_rbi_nw_08,R.drawable.in_gal_gs_ext_view_02,R.drawable.in_gal_gs_ext_view_06,R.drawable.xcent_main};



    public ViewPagerAdapter(Context context) {
        this.context = context;


    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

@Override
    public Object instantiateItem(ViewGroup container,int position){
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView4);
        imageView.setImageResource(images[position]);
        ViewPager vp=(ViewPager)container;
        vp.addView(view,0);
        return view;
   }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp=(ViewPager)container;
        View view=(View) object;
        vp.removeView(view);
    }
}
