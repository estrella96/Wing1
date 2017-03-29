package com.example.lyj1996.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lyj1996.wing1.R;

import java.util.List;


public class MainItemAdapter extends ArrayAdapter<MainItem> {

    private int resourceId;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainItem mainItem = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);//布局填充类 视图对象与父视图无关
        ImageView mainItemImage = (ImageView) view.findViewById(R.id.item_image);
        TextView mainItenName = (TextView) view.findViewById(R.id.item_name);
        mainItemImage.setImageResource(mainItem.getImageId());
        mainItenName.setText(mainItem.getName());
        return view;
    }

    public MainItemAdapter(Context context, int resourceId, List<MainItem> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }
}
