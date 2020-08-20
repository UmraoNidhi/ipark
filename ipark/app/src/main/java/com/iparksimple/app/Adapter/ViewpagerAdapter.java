package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.iparksimple.app.R;

import java.util.List;

public class ViewpagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context mcontext;
    List<String> mImages;


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.listview,container,false);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);    }

}
