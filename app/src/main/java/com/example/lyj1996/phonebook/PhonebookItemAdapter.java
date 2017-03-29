package com.example.lyj1996.phonebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyj1996.wing1.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by lyj1996 on 2017/3/14.
 */

public class PhonebookItemAdapter extends ArrayAdapter<Linkman>  {
    private  int resourceId;
    private boolean isDel;//显示复选框
    public   ArrayList<Linkman> delList;
    private Context context;

    public PhonebookItemAdapter (Context context, int resource, List<Linkman> objects) {
        super(context, resource, objects);
        resourceId=resource;
        isDel=false;
        delList=new ArrayList<Linkman>();
    }

    @Override
    public  View getView(final  int position,View convertView,final  ViewGroup parent) {
        final Linkman linkman = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.linkmanName = (TextView) view.findViewById(R.id.phonebook_item_name);
            viewHolder.callIocn = (ImageView) view.findViewById(R.id.phonebook_call_icon);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.phonebook_checkbox);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.linkmanName.setText(linkman.getName());
        if (isDel) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checkBox.setChecked(false);
            viewHolder.checkBox.setVisibility(View.GONE);
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
            if (viewHolder.checkBox.isChecked()){
                delList.add(linkman);
            }
            else {
                delList.remove(linkman);
            }


        }
        });
        return view;
    }

    class ViewHolder
    {
        ImageView linkmanPhoto;
        TextView linkmanName;
        ImageView callIocn;
        CheckBox checkBox;
    }
    public void setDel(boolean isDel)
    {
        this.isDel=isDel;
        notifyDataSetChanged();
    }
    public ArrayList<Linkman> getDelList(){
        return delList;
    }



}
